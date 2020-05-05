package web.dao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserAnswer {
    int stage;
    String key;
    String ansValue;
    BigDecimal featureValue;
    int prefVal;

    boolean isFeature(){
        return featureValue != null;
    }
}
