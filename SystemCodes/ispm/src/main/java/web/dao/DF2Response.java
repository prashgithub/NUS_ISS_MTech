package web.dao;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class DF2Response {
    String queryText;
    String intentDisplayName;
    float intentDetectionConfidence;
    String fulfillmentText;
    LinkedHashMap<String, Float> knowledgeAnswers;

    public DF2Response(String queryText, String intentDisplayName, float intentDetectionConfidence, String fulfillmentText, LinkedHashMap<String, Float> knowledgeAnswers) {
        this.queryText = queryText;
        this.intentDisplayName = intentDisplayName;
        this.intentDetectionConfidence = intentDetectionConfidence;
        this.fulfillmentText = fulfillmentText;
        this.knowledgeAnswers = knowledgeAnswers;
    }
}
