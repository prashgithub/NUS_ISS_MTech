package web.dao;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.stream.Stream.of;
import static org.apache.commons.lang3.ObjectUtils.max;
import static org.apache.commons.lang3.ObjectUtils.min;
import static web.dao.GRACalcHolder.Stage.*;

public class GRACalcHolder {
    public static final String DEFAULT_WEIGHT = "0.5";
    private final boolean init;
    private final EnumMap<Stage, Table<String, String, BigDecimal>> holder = new EnumMap<>(Stage.class);

    public static class Row {
        public Row(String rowKey, String colKey, BigDecimal value) {
            this.rowKey = rowKey;
            this.colKey = colKey;
            this.value = value;
        }

        String rowKey;
        String colKey;
        BigDecimal value;
    }

    public GRACalcHolder(List<Row> entries) {
        for(Row r : entries) I.getOrInit(holder).put(r.rowKey, r.colKey, r.value);
        this.init = init();
    }

    private boolean init() {
        try {
            of(I, N, D, G).forEach(stage -> stage.calc(holder));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void checkInit() {
        if (!init) throw new IllegalStateException("Init errors. Please check logs");
    }

    public Map<String, BigDecimal> getDefaultWeights() {
        checkInit();
        return F.getOrInit(holder).row(Formulae.THETA.name());
    }

    public Table<String, String, BigDecimal> getDefaultScore() {
        checkInit();
        return G.getOrInit(holder);
    }

    public Table<String, String, BigDecimal> getUserScore(Map<String, BigDecimal> weights) {
        checkInit();
        return Formulae.calcUser(holder, weights);
    }

    public BigDecimal getUsrValNormalizedWRTFeature(String featureName, BigDecimal userInput) {
        return getDeltaRatio(userInput, F.getOrInit(holder).row(Formulae.I_MAX.name()).get(featureName),
                F.getOrInit(holder).row(Formulae.I_MIN.name()).get(featureName));
    }

    private static BigDecimal getDeltaRatio(BigDecimal currVal, BigDecimal maxVal, BigDecimal minVal) {
        BigDecimal maxMinDiff = maxVal.subtract(minVal);
        return ZERO.compareTo(maxMinDiff) == 0 ?
                ZERO.compareTo(maxVal) == 0 ? ZERO :
                        currVal.divide(maxVal, 3, HALF_UP)
                : currVal.subtract(minVal).divide(maxMinDiff, 3, HALF_UP);
    }

    protected enum Stage {
        I("PolicyInputs") {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                Formulae.I_MIN.calc(holder);
                Formulae.I_MAX.calc(holder);
            }
        },
        N("Normalization") {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                Formulae.N_MAIN.calc(holder);
                Formulae.N_MIN.calc(holder);
                Formulae.N_MAX.calc(holder);
            }
        },
        D("Deviation Coeff") {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                Formulae.D_MAIN.calc(holder);
                Formulae.D_MIN.calc(holder);
                Formulae.D_MAX.calc(holder);
                Formulae.THETA.calc(holder);
            }
        },
        G("Gray Relational Default Weights") {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                Formulae.G_MAIN.calc(holder);
                Formulae.G_MIN.calc(holder);
                Formulae.G_MAX.calc(holder);
            }
        }, U("Gray Relational User Weights"), F("Aggregate Func"),
        ;

        private String name;

        Stage(String name) {
            this.name = name;
        }

        public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
            getOrInit(holder);
        }

        public Table<String, String, BigDecimal> getOrInit(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
            return holder.computeIfAbsent(this, k -> HashBasedTable.create());
        }
    }

    private enum Formulae {
        I_MIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(I, holder, rowToVal -> min(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        I_MAX {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(I, holder, rowToVal -> max(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        N_MAIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                transform(I, N, holder, (colName, curr) -> {
                    Table<String, String, BigDecimal> af = Stage.F.getOrInit(holder);
                    return getDeltaRatio(curr, af.get(I_MAX.name(), colName), af.get(I_MIN.name(), colName));
                });
            }
        },
        N_MIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(N, holder, rowToVal -> min(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        N_MAX {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(N, holder, rowToVal -> max(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        D_MAIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                transform(N, D, holder, (colName, curr) -> {
                    Table<String, String, BigDecimal> af = Stage.F.getOrInit(holder);
                    BigDecimal max = af.get(N_MAX.name(), colName);
                    return max.subtract(curr);
                });
            }
        },
        D_MIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(D, holder, rowToVal -> min(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        D_MAX {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(D, holder, rowToVal -> max(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        THETA {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(D, holder, rowToVal -> new BigDecimal(DEFAULT_WEIGHT));
            }
        },
        G_MAIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                transform(D, G, holder, (colName, curr) -> {
                    Table<String, String, BigDecimal> af = Stage.F.getOrInit(holder);
                    BigDecimal max = af.get(D_MAX.name(), colName);
                    BigDecimal min = af.get(D_MIN.name(), colName);
                    BigDecimal theta = af.get(THETA.name(), colName);
                    BigDecimal maxTheta = max.multiply(theta);
                    BigDecimal maxThetaMin = maxTheta.add(min);
                    BigDecimal maxThetaCurr = maxTheta.add(curr);
                    return ZERO.compareTo(maxThetaCurr) == 0 ? ZERO : maxThetaMin.divide(maxThetaCurr, 3, HALF_UP);
                });
            }
        },
        G_MIN {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(G, holder, rowToVal -> min(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        G_MAX {
            @Override
            public void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder) {
                aggregate(G, holder, rowToVal -> max(rowToVal.values().toArray(new BigDecimal[0])));
            }
        },
        ;

        protected void transform(Stage srcStage,
                                 Stage dstStage,
                                 EnumMap<Stage, Table<String, String, BigDecimal>> holder,
                                 BiFunction<String, BigDecimal, BigDecimal> func) {
            Table<String, String, BigDecimal> src = srcStage.getOrInit(holder);
            Table<String, String, BigDecimal> dst = dstStage.getOrInit(holder);
            src.columnMap().forEach((colName, rowToVal) ->
                    rowToVal.forEach((rowName, inputVal) -> dst.put(rowName, colName, func.apply(colName, inputVal))));
        }

        protected void aggregate(Stage srcStage,
                                 EnumMap<Stage, Table<String, String, BigDecimal>> holder,
                                 Function<Map<String, BigDecimal>, BigDecimal> func) {
            String fName = this.name();
            Table<String, String, BigDecimal> src = srcStage.getOrInit(holder);
            Table<String, String, BigDecimal> dst = Stage.F.getOrInit(holder);
            src.columnMap().forEach((colName, rowToVal) -> dst.put(fName, colName, func.apply(rowToVal)));
        }

        public abstract void calc(EnumMap<Stage, Table<String, String, BigDecimal>> holder);

        private synchronized static Table<String, String, BigDecimal> calcUser(EnumMap<Stage, Table<String, String, BigDecimal>> holder,
                                                                               Map<String, BigDecimal> featureToUserWeight) {
            G_MAIN.transform(G, U, holder, (colName, curr) -> {
                Table<String, String, BigDecimal> af = Stage.F.getOrInit(holder);
                BigDecimal max = af.get(D_MAX.name(), colName);
                BigDecimal min = af.get(D_MIN.name(), colName);
                BigDecimal theta = featureToUserWeight.get(colName);
                BigDecimal maxTheta = max.multiply(theta);
                BigDecimal maxThetaMin = maxTheta.add(min);
                BigDecimal maxThetaCurr = maxTheta.add(curr);
                return ZERO.compareTo(maxThetaCurr) == 0 ? ZERO : maxThetaMin.divide(maxThetaCurr, 3, HALF_UP);
            });
            return U.getOrInit(holder);
        }
    }

}
