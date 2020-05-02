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

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalcController {
    @Autowired
    private CalcService calcService;

    @GetMapping(value = "/calc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> getScore() {
        return new ResponseEntity<>(calcService.getScoreForPolicyFeatureAsTable().rowMap(),
                new HttpHeaders(), HttpStatus.OK);
    }
}