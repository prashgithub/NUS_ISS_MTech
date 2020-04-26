package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "comppol_prem")
public class ISPCompPolicyPremium {
    @Id private int indexid;
    private int comppolicyId;
    private int age;
    private int premAmount;
    private int wardId;

    public int getIndexid() {
        return indexid;
    }

    public void setIndexid(int indexid) {
        this.indexid = indexid;
    }

    public int getComppolicyId() {
        return comppolicyId;
    }

    public void setComppolicyId(int comppolicyId) {
        this.comppolicyId = comppolicyId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPremAmount() {
        return premAmount;
    }

    public void setPremAmount(int premAmount) {
        this.premAmount = premAmount;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
}
