package web.controller;

import com.google.cloud.dialogflow.v2beta1.DetectIntentRequest;
import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.KnowledgeAnswers;
import com.google.cloud.dialogflow.v2beta1.KnowledgeBaseName;
import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.cloud.dialogflow.v2beta1.QueryParameters;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.cloud.dialogflow.v2beta1.SessionName;
import com.google.cloud.dialogflow.v2beta1.SessionsClient;
import com.google.cloud.dialogflow.v2beta1.TextInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dao.DF2Response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DialogFlowRestController {
    private static final Logger logger = LoggerFactory.getLogger(DialogFlowRestController.class);
    private static final boolean printEnabled = false;
    private String sessionId = UUID.randomUUID().toString();
    private static final String PROJECT_ID = "prashdfagentx-qtlybd";
    private static final String DEFAULT_KB_ID = "MTU5MTUwMTA3OTg2MTU3ODk1Njg";
//    private final String GOOGLE_APPLICATION_CREDENTIALS = "dialogue_flow_agent_config.json";

    @GetMapping(value = "/df2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DF2Response> df2(@RequestParam(name = "userText", defaultValue = "hello") String userText,
                                           @RequestParam(name = "kb", defaultValue = DEFAULT_KB_ID) String kbId)
            throws Exception {
        logger.info("DF2: received userText=" + userText);
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(PROJECT_ID, sessionId);
            KnowledgeBaseName knowledgeBaseName = KnowledgeBaseName.of(PROJECT_ID, kbId);
            logger.info("Session Path: " + session.toString());
            String LANG_CODE = "en-US";
            TextInput.Builder textInput = TextInput.newBuilder()
                    .setText(userText)
                    .setLanguageCode(LANG_CODE);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            QueryParameters queryParameters = QueryParameters.newBuilder()
                    .addKnowledgeBaseNames(knowledgeBaseName.toString())
                    .build();

            DetectIntentRequest detectIntentRequest =  DetectIntentRequest.newBuilder()
                                                        .setSession(session.toString())
                                                        .setQueryInput(queryInput)
                                                        .setQueryParams(queryParameters)
                                                        .build();
            DetectIntentResponse response = sessionsClient.detectIntent(detectIntentRequest);
            QueryResult queryResult = response.getQueryResult();
            KnowledgeAnswers knowledgeAnswers = getFromQueryResults(queryResult);

            DF2Response entity = new DF2Response(
                    queryResult.getQueryText()
                    , queryResult.getIntent().getDisplayName()
                    , queryResult.getIntentDetectionConfidence()
                    , queryResult.getFulfillmentText()
                    , toMap(knowledgeAnswers.getAnswersList())
            );
            return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
        }
    }

    private static KnowledgeAnswers getFromQueryResults(QueryResult queryResult) {
        KnowledgeAnswers knowledgeAnswers = queryResult.getKnowledgeAnswers();
        if(printEnabled){
            System.out.format("Knowledge results:\n");
            System.out.format("====================\n");
            System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
            System.out.format("Detected Intent: %s (confidence: %f)\n",
                    queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
            System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
            for (KnowledgeAnswers.Answer answer : knowledgeAnswers.getAnswersList()) {
                System.out.format(" - Answer: '%s'\n", answer.getAnswer());
                System.out.format(" - Confidence: '%s'\n", answer.getMatchConfidence());
            }
        }
        return knowledgeAnswers;
    }

    private static LinkedHashMap<String, Float> toMap(List<KnowledgeAnswers.Answer> answersList) {
        LinkedHashMap<String, Float> map = new LinkedHashMap<>(answersList.size(), 1);
        for (KnowledgeAnswers.Answer answer : answersList)
            map.put(answer.getAnswer(), answer.getMatchConfidence());
        return map;
    }
}