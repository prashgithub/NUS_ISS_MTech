package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "policies")
public class ISPPolicies {
    @Id private int policyId;
    private String policyName;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }
}