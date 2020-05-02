package integration;
import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class DMIntegration {
   public String  predict(Application application){

       try {
           Applicant applicant=application.getApplicant();
           File testFile=new ClassPathResource("dm/PolicyPredict.csv").getFile();
           FileWriter tfw = new FileWriter(testFile, false);
           String testData="Gender,Age"+System.lineSeparator()+applicant.getGender()+","+applicant.getAge();
           System.out.println("predict on : "+testData);
           tfw.write(testData);
           tfw.close();
           String script =new ClassPathResource("dm/InsuranceRecomOrange.py").getFile().getPath();
           String training_data=new ClassPathResource("dm/PolicyHistoricalData.csv").getFile().getPath();
           String test_data=new ClassPathResource("dm/PolicyPredict.csv").getFile().getPath();
           System.out.println("dm file path"+script);
           Process p = Runtime.getRuntime().exec("python "+script + " "+training_data+" "+test_data );
           BufferedReader stdInput = new BufferedReader(new
                   InputStreamReader(p.getInputStream()));
           BufferedReader stdError = new BufferedReader(new
                   InputStreamReader(p.getErrorStream()));

           // read the output from the command
           System.out.println("Here is the standard output of the command:\n");
           String s;
           while ((s = stdInput.readLine()) != null) {
               System.out.println(s);
               if(s.startsWith("KNNLearner")){
                  return s.split(":")[1];
               }
           }

           while ((s = stdError.readLine()) != null) {
               System.err.println("Exception:"+s);
           }
       }
       catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
