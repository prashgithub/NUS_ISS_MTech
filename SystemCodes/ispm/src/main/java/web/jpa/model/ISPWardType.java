package web.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ward_type")
public class ISPWardType {
    @Id private int wardId;
    private String category;
    private String typ;
    private String descr;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}