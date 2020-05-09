package integration;
import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class DMIntegration {
   public String  predict(Application application){

       try {
           Applicant applicant=application.getApplicant();
           File testFile=createTempFile("PolicyPredict.csv");
           FileWriter tfw = new FileWriter(testFile, false);
           tfw.write(getTestData(applicant));
           tfw.close();
           System.out.println("test file path"+testFile.getPath());

           File script = createTempFile("InsuranceRecomOrange.py");
           FileUtils.copyInputStreamToFile(new ClassPathResource("dm/InsuranceRecomOrange.py").getInputStream(), script);
           System.out.println("dm file path"+script.getPath());

           File training_data = createTempFile("PolicyHistoricalData3.csv");
           FileUtils.copyInputStreamToFile(new ClassPathResource("dm/PolicyHistoricalData3.csv").getInputStream(), training_data);
           System.out.println("training data file path"+training_data.getPath());

           Process p = Runtime.getRuntime().exec("python "+script.getPath() + " "+training_data.getPath()+" "+testFile.getPath() );
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

   private File createTempFile(String fileName) throws IOException {
       File tempFolder=new File(System.getProperty("user.dir")+"/temp/");
       if(!tempFolder.exists()){
           tempFolder.mkdirs();
           tempFolder.setReadable(true,false);
           tempFolder.setWritable(true,false);
           tempFolder.setExecutable(true,false);
       }
       File tempFile = new File(System.getProperty("user.dir")+"/temp/"+fileName);
       if(tempFile.exists()){
           tempFile.delete();
       }
       tempFile.createNewFile();
       tempFile.setWritable(true,false);
       tempFile.setExecutable(true,false);
       tempFile.setReadable(true,false);
       return tempFile;
   }

    private String getTestData(Applicant applicant) {
       StringBuilder sb=new StringBuilder();
       sb.append("Gender,Age,Family Size,Income,Expenditure,Loan Amount").append(System.lineSeparator())
               .append(applicant.getGender()).append(",")
               .append(applicant.getAge()).append(",")
               .append(applicant.getFamilySize()).append(",")
               .append(applicant.getIncome()).append(",")
               .append(applicant.getExpenditure()).append(",")
               .append(applicant.getLoanAmount());
        System.out.println("predict on : "+sb.toString());
        return sb.toString();
    }
}
