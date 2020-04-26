package web.jpa.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Immutable
@Table(name = "comp_pol_prem_view")
public class ISPCompPoliciesPremiumView {
    @Id private String policyIdAge;
    private String policyName;
    private String wardType;
    private String wardCategory;
    private String company;
    private int age;
    private int premiumAmount;
}
