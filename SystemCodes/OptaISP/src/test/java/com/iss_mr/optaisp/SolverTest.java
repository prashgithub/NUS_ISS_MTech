package com.iss_mr.optaisp;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.optaplanner.core.api.solver.Solver;
import org.junit.Test;
public class SolverTest {

        static final long serialVersionUID = 1L;

        public SolverTest() {}

           //@Test
            public void solve() {
                KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer(SolverTest.class.getClassLoader());
                SolverFactory<ISPSolution> solverFactory =
                        SolverFactory.createFromKieContainerXmlResource(kieContainer,
                                "com/iss_mr/optaisp/ispSolverConfig.solver.xml");
                Solver<ISPSolution> solver = solverFactory.buildSolver();
                ISPSolution solution=getSolution();
                solver.solve(solution);
                System.out.println("explain : "+solver.explainBestScore());
               System.out.println("score : "+solver.getBestSolution().getScore());
                System.out.println("result : "+solver.getBestSolution().getPreferenceList().get(0).getPolicy().getName());
            }

            private ISPSolution getSolution() {
                Policy policy = new Policy();
                policy.setId(1);
                policy.setName("AIA HealthShield Gold Max A");
                policy.setDailyWard(100);
                policy.setMajorOrganTransplant(100);
                policy.setPostHospitalisationCoveredDays(50);
                policy.setPreHospitalisationCoveredDays(50);
                policy.setSurgery(100);
                policy.setLastEntryAge(100);

                Policy policy2 = new Policy();
                policy2.setId(2);
                policy2.setName("Prudential PruShield Premier");
                policy2.setDailyWard(100);
                policy2.setMajorOrganTransplant(100);
                policy2.setPostHospitalisationCoveredDays(100);
                policy2.setPreHospitalisationCoveredDays(100);
                policy2.setSurgery(100);
                policy2.setLastEntryAge(100);

                List<Policy> policyList=new ArrayList();
                policyList.add(policy);
                policyList.add(policy2);


                Preference preference = new Preference();
                preference.setRequiredDailyWard(100);
                preference.setRequiredSurgery(100);
                preference.setRequiredPreHospitalisationCoveredDays(75);
                preference.setRequiredPostHospitalisationCoveredDays(75);
                preference.setRequiredMajorOrganTransplant(100);
                preference.setRequiredAge(30);

                ISPConstraintConfiguration constraintConfiguration=new ISPConstraintConfiguration();
                constraintConfiguration.setHardLastEntryAge(HardSoftScore.ofHard(1));
                constraintConfiguration.setPreHospitalisationCoveredDays(HardSoftScore.ofSoft(1));
                constraintConfiguration.setPostHospitalisationCoveredDays(HardSoftScore.ofSoft(1));

                return new ISPSolution(policyList,
                        Arrays.asList(preference),
                        constraintConfiguration);
            }
        }