package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import web.service.CalcService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalcController {
    @Autowired private CalcService calcService;

    @GetMapping(value = "/calcFeature", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> getScore() {
        return new ResponseEntity<>(calcService.getScoreForPolicyFeature(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/calcNormalFeature", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> calcNormalFeature() {
        return new ResponseEntity<>(calcService.getNormalScoreForPolicyFeature(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/calcNormalFeatureUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BigDecimal> getNormalValueWRTFeature(String featureName, String userValue)  {
        BigDecimal normalValueWRTFeature = calcService.getNormalValueWRTFeature(featureName, new BigDecimal(userValue));
        return new ResponseEntity<>(normalValueWRTFeature,new HttpHeaders(), HttpStatus.OK);
    }



    @GetMapping(value = "/calcPremium", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> getPremiumScore() {
        return new ResponseEntity<>(calcService.getScoreForPolicyPremium(),
                new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/calcNormalPremium", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> calcNormalPremium() {
        return new ResponseEntity<>(calcService.getNormalScoreForPolicyPremium(),
                new HttpHeaders(), HttpStatus.OK);
    }
}