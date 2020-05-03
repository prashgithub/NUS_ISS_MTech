package web.dao;

import com.google.common.collect.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Builder
@Data
public class MCDMCalcHolder {
    String featureName;
    List<BigDecimal> list = new ArrayList<>();
    List<BigDecimal> beneficialVal = new ArrayList<>();
    List<BigDecimal> nonBeneficialVal = new ArrayList<>();
    BigDecimal highestVal;
    BigDecimal lowestVal;

    public void add(String benefits) {
        list.add(new BigDecimal(benefits));
    }

    public void compute() {
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = BigDecimal.valueOf(100000);
        for (BigDecimal bd : list) {
            if (bd.compareTo(max) > 0)
                max = bd;
            else if (bd.compareTo(min) < 0)
                min = bd;
        }
        highestVal = max;
        lowestVal = min;

        calcBeneficialValue();
        calcNonBeneficialValue();
    }

    private void calcBeneficialValue() {
        if (highestVal.compareTo(BigDecimal.ZERO) != 0){
            for (BigDecimal bd : list)
                beneficialVal.add(bd.divide(highestVal, 2, RoundingMode.HALF_UP));
        } else
            beneficialVal.add(BigDecimal.ZERO);
    }

    private void calcNonBeneficialValue() {
        for (BigDecimal bd : list)
            if (bd.compareTo(BigDecimal.ZERO) != 0)
                nonBeneficialVal.add(lowestVal.divide(bd, 2, RoundingMode.HALF_UP));
            else
                nonBeneficialVal.add(BigDecimal.ZERO);
    }

}
