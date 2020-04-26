package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "comp_policies")
public class ISPCompPolicies {
    @Id private int policy_id;
    private int insrcomp_no;

    public int getInsrcomp_no() {
        return insrcomp_no;
    }

    public void setInsrcomp_no(int insrcomp_no) {
        this.insrcomp_no = insrcomp_no;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }
}