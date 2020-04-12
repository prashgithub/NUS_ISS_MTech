package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CompPolicyPremium {
    private @Id @GeneratedValue Long id;
    private String providerName;
    private String wardCategory;
    private String wardType;
    private String policyName;
    private int age;
    private int premiumAmount;
}
