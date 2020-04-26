package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "policy_features")
public class ISPPolicyFeatures {
    @Id private int policyfeaId;
    private String policyfeaSname;
    private String policyfeaFname;
    private String descr;

    public int getPolicyfeaId() {
        return policyfeaId;
    }

    public void setPolicyfeaId(int policyfeaId) {
        this.policyfeaId = policyfeaId;
    }

    public String getPolicyfeaSname() {
        return policyfeaSname;
    }

    public void setPolicyfeaSname(String policyfeaSname) {
        this.policyfeaSname = policyfeaSname;
    }

    public String getPolicyfeaFname() {
        return policyfeaFname;
    }

    public void setPolicyfeaFname(String policyfeaFname) {
        this.policyfeaFname = policyfeaFname;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}