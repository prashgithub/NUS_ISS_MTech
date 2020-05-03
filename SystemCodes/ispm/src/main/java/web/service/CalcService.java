package web.service;

import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.GRACalcHolder;
import web.jpa.jparepository.ISPCompPoliciesFeatureViewRepository;
import web.jpa.model.ISPCompPolFeatureView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CalcService {
    private static final Logger logger = LoggerFactory.getLogger(CalcService.class);

    private List<ISPCompPolFeatureView> list = new ArrayList<>();

    @Autowired
    ISPCompPoliciesFeatureViewRepository repository;
    private GRACalcHolder graCalcHolder;

    private void loadCache() {
        if (list.isEmpty()) repository.findAll().forEach(list::add);
        if (graCalcHolder == null) graCalcHolder = new GRACalcHolder(list);
    }

    private Table<String, String, BigDecimal> getDefaultScore() {
        return graCalcHolder.getDefaultScore();
    }

    public BigDecimal getNormalValueWRTFeature(String featureName, BigDecimal userValue) {
        loadCache();
        return  graCalcHolder.getUsrValNormalizedWRTFeature(featureName, userValue);
    }

    public BigDecimal getScoreForPolicyFeature(String policyName, String featureName) {
        loadCache();
        return  getDefaultScore().get(policyName, featureName);
    }
    public Map<String, BigDecimal> getScoreForPolicyFeature(String policyName) {
        loadCache();
        return  getDefaultScore().rowMap().get(policyName);
    }
    public Map<String, Map<String, BigDecimal>> getScoreForPolicyFeature() {
        loadCache();
        return getDefaultScore().rowMap();
    }

    public Table<String, String, BigDecimal> getScoreForPolicyFeatureAsTable() {
        loadCache();
        //        Map<String, BigDecimal> policyScore = graCalcHolder.getPolicyScore();
        return getDefaultScore();
    }
}
