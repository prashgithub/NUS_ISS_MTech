package com.iss_mr.optaisp;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@ConstraintConfiguration(constraintPackage = "com.iss_mr.optaisp")
public class ISPConstraintConfiguration {
    public static final String HARD_LAST_ENTRY_AGE = "Hard last entry age";
    public static final String PRE_HOSPITALISATION_COVERED_DAYS = "Pre Hospitalisation Covered Days";
    public static final String POST_HOSPITALISATION_COVERED_DAYS = "Post Hospitalisation Covered Days";
    public static final String POLICY_YEAR_LIMIIT = "Policy Year Limit";
    public static final String COMMUNITY_HOSPITAL = "Community Hospital";
    public static final String MAJOR_ORGAN_TRANSPLANT = "Major Organ Transplant";
    public static final String CRITICAL_ILLNESS = "Critical Illnesses";
    public static final String PROSTHESIS = "Prosthesis";
    public static final String EMERGENCY_OVERSEAS_TREATMENT = "Emergency Overseas Treatment";
    public static final String CLAIMS_PROCESSING_DURATION = "Claims Processing Duration";
    public static final String POST_HOSPITALISATION_COVERAGE = "Post Hospitalisation Coverage";
    public static final String PRE_HOSPITALISATION_COVERAGE = "Pre Hospitalisation Coverage";
    public static final String COINSURANCE = "Co-insurance";
    public static final String DEDUCTIBLE = "Deductible";
    public static final String NON_PANEL_SURCHARGE = "Non-Panel Surcharge";
    public static final String COPAY_CAPPED_AT = "Co-Pay Capped At";
    public static final String SURGERY = "Surgery";
    public static final String PREMIUM_AMOUT = "Premium Amount";




    @ConstraintWeight(HARD_LAST_ENTRY_AGE)
    private HardSoftScore hardLastEntryAge = HardSoftScore.ofHard(1);
    @ConstraintWeight(PRE_HOSPITALISATION_COVERED_DAYS)
    private HardSoftScore preHospitalisationCoveredDays = HardSoftScore.ofSoft(10);
    @ConstraintWeight(POST_HOSPITALISATION_COVERED_DAYS)
    private HardSoftScore postHospitalisationCoveredDays = HardSoftScore.ofSoft(10);
    @ConstraintWeight(POLICY_YEAR_LIMIIT)
    private HardSoftScore policyYearLimit = HardSoftScore.ofSoft(10);
    @ConstraintWeight(COMMUNITY_HOSPITAL)
    private HardSoftScore communityHospital = HardSoftScore.ofSoft(10);
    @ConstraintWeight(MAJOR_ORGAN_TRANSPLANT)
    private HardSoftScore majorOrganTransplant = HardSoftScore.ofSoft(10);
    @ConstraintWeight(CRITICAL_ILLNESS)
    private HardSoftScore criticalIllness = HardSoftScore.ofSoft(10);
    @ConstraintWeight(PROSTHESIS)
    private HardSoftScore prosthsis = HardSoftScore.ofSoft(10);
    @ConstraintWeight(EMERGENCY_OVERSEAS_TREATMENT)
    private HardSoftScore emergencyOverseasTreatment = HardSoftScore.ofSoft(10);
    @ConstraintWeight(CLAIMS_PROCESSING_DURATION)
    private HardSoftScore claimProcessingDuration = HardSoftScore.ofSoft(10);
    @ConstraintWeight(POST_HOSPITALISATION_COVERAGE)
    private HardSoftScore postHopitalisationCoverage = HardSoftScore.ofSoft(10);
    @ConstraintWeight(PRE_HOSPITALISATION_COVERAGE)
    private HardSoftScore preHopitalisationCoverage = HardSoftScore.ofSoft(10);
    @ConstraintWeight(COINSURANCE)
    private HardSoftScore coinsurance = HardSoftScore.ofSoft(10);
    @ConstraintWeight(DEDUCTIBLE)
    private HardSoftScore deductible = HardSoftScore.ofSoft(10);
    @ConstraintWeight(NON_PANEL_SURCHARGE)
    private HardSoftScore nonPanelsurcharge = HardSoftScore.ofSoft(10);
    @ConstraintWeight(COPAY_CAPPED_AT)
    private HardSoftScore coPayCappedAt = HardSoftScore.ofSoft(10);
    @ConstraintWeight(SURGERY)
    private HardSoftScore surgery = HardSoftScore.ofSoft(10);
    @ConstraintWeight(PREMIUM_AMOUT)
    private HardSoftScore premiumAmount = HardSoftScore.ofSoft(10);


    public ISPConstraintConfiguration() {
    }

    public HardSoftScore getHardLastEntryAge() {
        return hardLastEntryAge;
    }

    public void setHardLastEntryAge(HardSoftScore hardLastEntryAge) {
        this.hardLastEntryAge = hardLastEntryAge;
    }

    public HardSoftScore getPreHospitalisationCoveredDays() {
        return preHospitalisationCoveredDays;
    }

    public void setPreHospitalisationCoveredDays(HardSoftScore preHospitalisationCoveredDays) {
        this.preHospitalisationCoveredDays = preHospitalisationCoveredDays;
    }

    public HardSoftScore getPostHospitalisationCoveredDays() {
        return postHospitalisationCoveredDays;
    }

    public void setPostHospitalisationCoveredDays(HardSoftScore postHospitalisationCoveredDays) {
        this.postHospitalisationCoveredDays = postHospitalisationCoveredDays;
    }

    public HardSoftScore getPolicyYearLimit() {
        return policyYearLimit;
    }

    public void setPolicyYearLimit(HardSoftScore policyYearLimit) {
        this.policyYearLimit = policyYearLimit;
    }

    public HardSoftScore getCommunityHospital() {
        return communityHospital;
    }

    public void setCommunityHospital(HardSoftScore communityHospital) {
        this.communityHospital = communityHospital;
    }

    public HardSoftScore getMajorOrganTransplant() {
        return majorOrganTransplant;
    }

    public void setMajorOrganTransplant(HardSoftScore majorOrganTransplant) {
        this.majorOrganTransplant = majorOrganTransplant;
    }

    public HardSoftScore getCriticalIllness() {
        return criticalIllness;
    }

    public void setCriticalIllness(HardSoftScore criticalIllness) {
        this.criticalIllness = criticalIllness;
    }

    public HardSoftScore getProsthsis() {
        return prosthsis;
    }

    public void setProsthsis(HardSoftScore prosthsis) {
        this.prosthsis = prosthsis;
    }

    public HardSoftScore getEmergencyOverseasTreatment() {
        return emergencyOverseasTreatment;
    }

    public void setEmergencyOverseasTreatment(HardSoftScore emergencyOverseasTreatment) {
        this.emergencyOverseasTreatment = emergencyOverseasTreatment;
    }

    public HardSoftScore getClaimProcessingDuration() {
        return claimProcessingDuration;
    }

    public void setClaimProcessingDuration(HardSoftScore claimProcessingDuration) {
        this.claimProcessingDuration = claimProcessingDuration;
    }

    public HardSoftScore getPostHopitalisationCoverage() {
        return postHopitalisationCoverage;
    }

    public void setPostHopitalisationCoverage(HardSoftScore postHopitalisationCoverage) {
        this.postHopitalisationCoverage = postHopitalisationCoverage;
    }

    public HardSoftScore getPreHopitalisationCoverage() {
        return preHopitalisationCoverage;
    }

    public void setPreHopitalisationCoverage(HardSoftScore preHopitalisationCoverage) {
        this.preHopitalisationCoverage = preHopitalisationCoverage;
    }

    public HardSoftScore getCoinsurance() {
        return coinsurance;
    }

    public void setCoinsurance(HardSoftScore coinsurance) {
        this.coinsurance = coinsurance;
    }

    public HardSoftScore getDeductible() {
        return deductible;
    }

    public void setDeductible(HardSoftScore deductible) {
        this.deductible = deductible;
    }

    public HardSoftScore getNonPanelsurcharge() {
        return nonPanelsurcharge;
    }

    public void setNonPanelsurcharge(HardSoftScore nonPanelsurcharge) {
        this.nonPanelsurcharge = nonPanelsurcharge;
    }

    public HardSoftScore getCoPayCappedAt() {
        return coPayCappedAt;
    }

    public void setCoPayCappedAt(HardSoftScore coPayCappedAt) {
        this.coPayCappedAt = coPayCappedAt;
    }

    public HardSoftScore getSurgery() {
        return surgery;
    }

    public void setSurgery(HardSoftScore surgery) {
        this.surgery = surgery;
    }

    public HardSoftScore getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(HardSoftScore premiumAmount) {
        this.premiumAmount = premiumAmount;
    }
}
