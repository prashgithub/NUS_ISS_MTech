package web.dao;

import lombok.Builder;
import lombok.Data;
import web.jpa.model.ISPCompPolFeatureView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class PolicyFeatureCalc {
    List<ISPCompPolFeatureView> inputPolicyFeatures;

    public Map<String, MCDMCalcHolder> calc(){
        Map<String, MCDMCalcHolder> outputMap = new LinkedHashMap<>();
        for (ISPCompPolFeatureView v: inputPolicyFeatures){
            String pf = v.getPolicyFeature();
//            MCDMCalcHolder calcHolder = outputMap.computeIfAbsent(pf, s -> MCDMCalcHolder.builder().featureName(s).build());
//            calcHolder.add(v.getBenefits());
        }
        outputMap.values().forEach(MCDMCalcHolder::compute);
        return outputMap;
    }
}
