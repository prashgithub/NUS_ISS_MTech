package web.dao;

import lombok.Value;

import java.util.LinkedHashMap;

@Value
public class DF2Response {
    String queryText;
    String intentDisplayName;
    float intentDetectionConfidence;
    String fulfillmentText;
    LinkedHashMap<String, Float> knowledgeAnswers;
}
