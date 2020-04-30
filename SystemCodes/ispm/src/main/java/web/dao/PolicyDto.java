package web.dao;

import com.iss_mr.optaisp.Premium;
import org.kie.api.definition.type.Label;

import java.util.List;

public class PolicyDto {
    private String issuer="";
    private String name="";
    private String benefit="";

    private Integer dailyWard;
    private Integer majorOrganTransplant;
    private Integer postHospitalisationCoveredDays;
    private Integer preHospitalisationCoveredDays;
    private Integer surgery;
    private Integer lastEntryAge;
    private List<Premium> premium;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public Integer getDailyWard() {
        return dailyWard;
    }

    public void setDailyWard(Integer dailyWard) {
        this.dailyWard = dailyWard;
    }

    public Integer getMajorOrganTransplant() {
        return majorOrganTransplant;
    }

    public void setMajorOrganTransplant(Integer majorOrganTransplant) {
        this.majorOrganTransplant = majorOrganTransplant;
    }

    public Integer getPostHospitalisationCoveredDays() {
        return postHospitalisationCoveredDays;
    }

    public void setPostHospitalisationCoveredDays(Integer postHospitalisationCoveredDays) {
        this.postHospitalisationCoveredDays = postHospitalisationCoveredDays;
    }

    public Integer getPreHospitalisationCoveredDays() {
        return preHospitalisationCoveredDays;
    }

    public void setPreHospitalisationCoveredDays(Integer preHospitalisationCoveredDays) {
        this.preHospitalisationCoveredDays = preHospitalisationCoveredDays;
    }

    public Integer getSurgery() {
        return surgery;
    }

    public void setSurgery(Integer surgery) {
        this.surgery = surgery;
    }

    public Integer getLastEntryAge() {
        return lastEntryAge;
    }

    public void setLastEntryAge(Integer lastEntryAge) {
        this.lastEntryAge = lastEntryAge;
    }

    public List<Premium> getPremium() {
        return premium;
    }

    public void setPremium(List<Premium> premium) {
        this.premium = premium;
    }
}
