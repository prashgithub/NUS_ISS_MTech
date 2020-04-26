package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "insr_comp")
public class ISPInsrComp {
    @Id private int insrcompNo;
    private String compName;
    private int claimprocMedian;

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public int getClaimprocMedian() {
        return claimprocMedian;
    }

    public void setClaimprocMedian(int claimprocMedian) {
        this.claimprocMedian = claimprocMedian;
    }

    public int getInsrcompNo() {
        return insrcompNo;
    }

    public void setInsrcompNo(int insrcompNo) {
        this.insrcompNo = insrcompNo;
    }
}
