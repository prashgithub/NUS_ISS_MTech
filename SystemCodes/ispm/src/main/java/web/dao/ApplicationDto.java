package web.dao;

import web.model.Question;

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

    private Map<String, String> userDetails=new LinkedHashMap<>();
    private Map<String, BigDecimal> userFeatureValues=new LinkedHashMap<>();

    public Map<String, String> getUserDetails() {
        return userDetails;
    }

    public Map<String, BigDecimal> getUserFeatureValues() {
        return userFeatureValues;
    }

    public void setAnswer(Question answeredQuestion, Long qid, String ans, String pre) {
        int stage = answeredQuestion.getStage();
        String key = answeredQuestion.getValue();
        List<Map<String, String>> listOfMap = answeredQuestion.getExtraDataAsListOfMap();
        String value = "-1";
        try {
            value = listOfMap.get(Integer.parseInt(ans)-1).keySet().stream().findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
            value = listOfMap.get(0).keySet().stream().findFirst().get();
        }

        if(stage == 1){
            userDetails.put(key, value);
        } else if(stage == 2){
            userFeatureValues.put(key, new BigDecimal(value));
        }
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

}
