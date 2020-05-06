package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.Question;
import web.service.CalcService;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicationDto {
    private String id = "0";
    private String name = "";
    private String age = "0";
    private String issuer="";
    private String status = "";
    private String gender="";
    private String familySize="";
    private String income="";
    private String expenditure="";
    private String loanAmount="";

    private Map<String, String> userDetails=new LinkedHashMap<>();
    private Map<String, BigDecimal> userFeatureValues=new LinkedHashMap<>();

    private Map<String, UserAnswer> userAnswers =new LinkedHashMap<>();
    @Autowired private CalcService calcService;

    public Map<String, UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    @Deprecated
    public Map<String, String> getUserDetails() {
        return userDetails;
    }

    @Deprecated
    public Map<String, BigDecimal> getUserFeatureValues() {
        return userFeatureValues;
    }

    public void setAnswer(Question answeredQuestion, Long qid, String ans, String pre) {
        UserAnswer userAnswer = new UserAnswer();
        int stage = answeredQuestion.getStage();
        String key = answeredQuestion.getValue();
        int prefVal = 3;
        try {
            prefVal = Integer.parseInt(pre);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Map<String, String>> listOfMap = answeredQuestion.getExtraDataAsListOfMap(this);
        String value = null;
        try {
            int userSelectedIndex = Integer.parseInt(ans);
            // if user doesn't select then it 0 else it is 1 or 2 or
            if(userSelectedIndex > 0)
                userSelectedIndex = userSelectedIndex - 1;
            value = listOfMap.get(userSelectedIndex).keySet().stream().findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
            value = listOfMap.get(0).keySet().stream().findFirst().get();
        }

        userAnswer.setStage(stage);
        userAnswer.setKey(key);
        userAnswer.setPrefVal(prefVal);
        if(stage == 1){
            userDetails.put(key, value);
            userAnswer.setAnsValue(value);
        } else if(stage == 2){
            BigDecimal featureVal = new BigDecimal(value);
            userFeatureValues.put(key, featureVal);
            userAnswer.setFeatureValue(featureVal);
        }
        userAnswers.put(key, userAnswer);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFamilySize() {
        return familySize;
    }

    public void setFamilySize(String familySize) {
        this.familySize = familySize;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setCalcService(CalcService calcService) {
        this.calcService = calcService;
    }
    public CalcService getCalcService() {
        return calcService;
    }
}
