package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "comppol_features")
public class ISPCompPoliciesFeature {
    @Id
    private int comppolicyId;
    private int policyfeaId;
    private int benefitValue;
    private String descr;
    private int wardId;

    public int getComppolicyId(){
        return comppolicyId;
    }

    public void setComppolicyId(int comppolicyId){
        this.comppolicyId = comppolicyId;
    }

    public int getPolicyfeaId(){
        return policyfeaId;
    }

    public void setPolicyfeaId(int policyfeaId){
        this.policyfeaId = policyfeaId;
    }

    public int getBenefitValue(){
        return benefitValue;
    }

    public void setBenefitValue(int benefitValue){
        this.benefitValue = benefitValue;
    }

    public String getDescr(){
        return descr;
    }

    public void setDescr(String descr){
        this.descr=descr;
    }

    public int getWardId(){
        return wardId;
    }

    public void setWardId(int wardId){
        this.wardId = wardId;
    }
}