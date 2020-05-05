package web.service;

import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.GRACalcHolder;
import web.jpa.jparepository.ISPCompPoliciesFeatureViewRepository;
import web.jpa.jparepository.ISPCompPoliciesPremiumViewRepository;
import web.jpa.model.ISPCompPolFeatureView;
import web.jpa.model.ISPCompPoliciesPremiumView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class CalcService {
    private static final Logger logger = LoggerFactory.getLogger(CalcService.class);

    @Autowired
    ISPCompPoliciesFeatureViewRepository featureRepo;
    @Autowired
    ISPCompPoliciesPremiumViewRepository premiumRepo;

    private List<ISPCompPolFeatureView> featureValues = new ArrayList<>();
    private List<ISPCompPoliciesPremiumView> agePremiumValues = new ArrayList<>();

    private GRACalcHolder featureGRACalcHolder;
    private GRACalcHolder premiumGRACalcHolder;

    private void loadCache() {
        if (featureValues.isEmpty()) featureRepo.findAll().forEach(featureValues::add);
        if (featureGRACalcHolder == null) featureGRACalcHolder =
                new GRACalcHolder(featureValues.stream().map(
                        v -> new GRACalcHolder.Row(v.getPolicyName(), v.getPolicyFeature(), new BigDecimal(v.getBenefits()))
                ).collect(Collectors.toList()));

        if (agePremiumValues.isEmpty()) premiumRepo.findAll().forEach(agePremiumValues::add);
        if (premiumGRACalcHolder == null) premiumGRACalcHolder =
                new GRACalcHolder(agePremiumValues.stream().map(
                        v -> new GRACalcHolder.Row(v.getPolicyName(), String.valueOf(v.getAge()), new BigDecimal(v.getPremiumAmount()))
                ).collect(Collectors.toList()));
    }

    private Table<String, String, BigDecimal> getDefaultScoreForFeature() {
        return featureGRACalcHolder.getDefaultScore();
    }

    private Table<String, String, BigDecimal> getDefaultScoreForPremium() {
        return premiumGRACalcHolder.getDefaultScore();
    }

    public BigDecimal getNormalValueWRTFeature(String featureName, BigDecimal userValue) {
        loadCache();
        return featureGRACalcHolder.getUsrValNormalizedWRTFeature(featureName, userValue);
    }

    public BigDecimal getNormalValueWRTPremiumAge(Integer age, BigDecimal userPremium) {
        return getNormalValueWRTPremiumAge(age.toString(), userPremium);
    }

    public BigDecimal getNormalValueWRTPremiumAge(String ageValue, BigDecimal userPremium) {
        loadCache();
        try {
            int gap = Integer.MAX_VALUE;
            String nearestAge = ageValue;
            for (String age : getAvailableAgesForPremium()) {
                int currGap = Math.abs(parseInt(ageValue) - parseInt(age));
                if (gap > currGap){
                    gap = currGap;
                    nearestAge = age;
                }
            }
            ageValue = nearestAge;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return premiumGRACalcHolder.getUsrValNormalizedWRTFeature(ageValue, userPremium);
    }

    public List<String> getAvailableAgesForPremium() {
        loadCache();
        return new ArrayList<>(getDefaultScoreForPremium().columnMap().keySet());
    }

    /*feature related*/

    public BigDecimal getScoreForPolicyFeature(String policyName, String featureName) {
        loadCache();
        return getDefaultScoreForFeature().get(policyName, featureName);
    }

    public Map<String, Map<String, BigDecimal>> getScoreForPolicyFeature() {
        loadCache();
        return getDefaultScoreForFeature().rowMap();
    }

    public Map<String, Map<String, BigDecimal>> getNormalScoreForPolicyFeature() {
        loadCache();
        return featureGRACalcHolder.getNormalScore().rowMap();
    }

    public BigDecimal getNormalScoreForPolicyFeature(String policyName, String ageInMap) {
        loadCache();
        return featureGRACalcHolder.getNormalScore().get(policyName, ageInMap);
    }

    /*premium related*/

    public Map<String, Map<String, BigDecimal>> getNormalScoreForPolicyPremium() {
        loadCache();
        return premiumGRACalcHolder.getNormalScore().rowMap();
    }

    public BigDecimal getNormalScoreForPolicyPremium(String policyName, String ageInMap) {
        loadCache();
        return premiumGRACalcHolder.getNormalScore().get(policyName, ageInMap);
    }

    //    GRA
    public Map<String, Map<String, BigDecimal>> getScoreForPolicyPremium() {
        loadCache();
        return getDefaultScoreForPremium().rowMap();
    }

    public BigDecimal getScoreForPolicyPremium(String policyName, String ageInMap) {
        loadCache();
        return getDefaultScoreForPremium().get(policyName, ageInMap);
    }
}
