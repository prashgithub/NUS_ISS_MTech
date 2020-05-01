package com.iss_mr.optaisp;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@ConstraintConfiguration(constraintPackage = "com.iss_mr.optaisp")
public class ISPConstraintConfiguration {
    public static final String HARD_LAST_ENTRY_AGE = "Hard last entry age";
    public static final String PRE_HOSPITALISATION_COVERED_DAYS = "Pre Hospitalisation covered days";
    public static final String POST_HOSPITALISATION_COVERED_DAYS = "Post Hospitalisation covered days";

    @ConstraintWeight(HARD_LAST_ENTRY_AGE)
    private HardSoftScore hardLastEntryAge = HardSoftScore.ofHard(1);
    @ConstraintWeight(PRE_HOSPITALISATION_COVERED_DAYS)
    private HardSoftScore preHospitalisationCoveredDays = HardSoftScore.ofSoft(10);

    @ConstraintWeight(POST_HOSPITALISATION_COVERED_DAYS)
    private HardSoftScore postHospitalisationCoveredDays = HardSoftScore.ofSoft(10);


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

}
