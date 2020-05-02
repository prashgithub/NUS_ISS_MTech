package web.controller;

import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dao.GRACalcHolder;
import web.jpa.jparepository.ISPCompPoliciesFeatureViewRepository;
import web.jpa.model.ISPCompPolFeatureView;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CalcController {
    private static final Logger logger = LoggerFactory.getLogger(CalcController.class);
    private List<ISPCompPolFeatureView> list = new ArrayList<>();

    @Autowired
    ISPCompPoliciesFeatureViewRepository repository;
    private GRACalcHolder graCalcHolder;

    @GetMapping(value = "/calc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> getScore() {
        logger.info("calc");
        if(list.isEmpty()) repository.findAll().forEach(list::add);
        if(graCalcHolder == null) graCalcHolder = new GRACalcHolder(list);
        Map<String, Map<String, BigDecimal>> rowMap = graCalcHolder.getDefaultScore().rowMap();
        return new ResponseEntity<>(rowMap, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/calcUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map> getScore(@RequestParam Map<String,String> weights) {
        logger.info("calc");
        if(list.isEmpty()) repository.findAll().forEach(list::add);
        if(graCalcHolder == null) graCalcHolder = new GRACalcHolder(list);
        Map<String,BigDecimal> def = graCalcHolder.getDefaultWeights();
        Map<String,BigDecimal> inp = new HashMap<>();
        def.forEach((k, v) -> {
            String val = weights.get(k);
            if(val != null)
                inp.put(k, new BigDecimal(val));
            else
                inp.put(k, v);
        });
        Map<String, Map<String, BigDecimal>> rowMap = graCalcHolder.getUserScore(inp).rowMap();

        return new ResponseEntity<>(rowMap, new HttpHeaders(), HttpStatus.OK);
    }
}