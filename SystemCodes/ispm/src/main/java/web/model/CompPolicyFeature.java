package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CompPolicyFeature {
    private @Id
    @GeneratedValue
    Long id;
    private Long policyId;
    private Long policyFeatureId;
    private Long benefitValue;
    private String benefitDesc;
}
