package com.iss_mr.optaisp;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.optaplanner.core.api.solver.SolverFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.optaplanner.core.api.solver.Solver;
import org.junit.Test;
public class SolverTest {

        static final long serialVersionUID = 1L;

        public SolverTest() {}

            @Test
            public void solve() {

                KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer(SolverTest.class.getClassLoader());
                SolverFactory<ISPSolution> solverFactory =
                        SolverFactory.createFromKieContainerXmlResource(kieContainer,
                                "com/iss_mr/optaisp/ispSolverConfig.xml");
                Solver<ISPSolution> solver = solverFactory.buildSolver();

                solver.solve(getSolution());
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

                List<Policy> policyList=new ArrayList();
                policyList.add(policy);


                Preference preference = new Preference();
                preference.setRequiredDailyWard(100);
                preference.setRequiredSurgery(100);
                preference.setRequiredPreHospitalisationCoveredDays(75);
                preference.setRequiredPostHospitalisationCoveredDays(75);
                preference.setRequiredMajorOrganTransplant(100);
                preference.setRequiredAge(30);



                return new ISPSolution(policyList,
                        Arrays.asList(preference),
                        null);
            }
        }