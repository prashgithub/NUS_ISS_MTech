package com.iss_mr.optaisp;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfigurationProvider;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;

/**
 * This class was automatically generated by the data modeler tool.
 */

@org.optaplanner.core.api.domain.solution.PlanningSolution
@javax.xml.bind.annotation.XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class ISPSolution implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label("Policy List")
	@org.optaplanner.core.api.domain.valuerange.ValueRangeProvider(id = "policyRange")
	@ProblemFactCollectionProperty
	private java.util.List<com.iss_mr.optaisp.Policy> policyList;

	@org.kie.api.definition.type.Label("Preference List")
	@org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
	private java.util.List<com.iss_mr.optaisp.Preference> preferenceList;

	@javax.annotation.Generated({"org.optaplanner.workbench.screens.domaineditor.client.widgets.planner.PlannerDataObjectEditor"})
	@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(org.optaplanner.persistence.jaxb.api.score.buildin.hardsoft.HardSoftScoreJaxbXmlAdapter.class)
	@org.kie.api.definition.type.Label("Generated Planner score field")
	@org.optaplanner.core.api.domain.solution.PlanningScore
	private org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore score;

	@ConstraintConfigurationProvider
	private ISPConstraintConfiguration constraintConfiguration;

	public ISPConstraintConfiguration getConstraintConfiguration() {
		return constraintConfiguration;
	}

	public void setConstraintConfiguration(ISPConstraintConfiguration constraintConfiguration) {
		this.constraintConfiguration = constraintConfiguration;
	}

	public ISPSolution() {
	}

	public java.util.List<com.iss_mr.optaisp.Policy> getPolicyList() {
		return this.policyList;
	}

	public void setPolicyList(
			java.util.List<com.iss_mr.optaisp.Policy> policyList) {
		this.policyList = policyList;
	}

	public java.util.List<com.iss_mr.optaisp.Preference> getPreferenceList() {
		return this.preferenceList;
	}

	public void setPreferenceList(
			java.util.List<com.iss_mr.optaisp.Preference> preferenceList) {
		this.preferenceList = preferenceList;
	}

	public org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore getScore() {
		return this.score;
	}

	public void setScore(
			org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore score) {
		this.score = score;
	}

	public ISPSolution(java.util.List<com.iss_mr.optaisp.Policy> policyList,
			java.util.List<com.iss_mr.optaisp.Preference> preferenceList, ISPConstraintConfiguration constraintConfiguration) {
		this.policyList = policyList;
		this.preferenceList = preferenceList;
		this.constraintConfiguration=constraintConfiguration;
	}

}