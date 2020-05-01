package com.iss_mr.integrated_shield_plan_master;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class PreferenceMatrix implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label(value = "Preference Matrix ID")
	private java.lang.Long id;
	@org.kie.api.definition.type.Label(value = "Importance level")
	private java.lang.Integer importance;
	@org.kie.api.definition.type.Label(value = "Expected Value")
	private java.lang.Integer expectedValue;

	public PreferenceMatrix() {
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Integer getImportance() {
		return this.importance;
	}

	public void setImportance(java.lang.Integer importance) {
		this.importance = importance;
	}

	public java.lang.Integer getExpectedValue() {
		return this.expectedValue;
	}

	public void setExpectedValue(java.lang.Integer expectedValue) {
		this.expectedValue = expectedValue;
	}

	public PreferenceMatrix(java.lang.Long id, java.lang.Integer importance,
			java.lang.Integer expectedValue) {
		this.id = id;
		this.importance = importance;
		this.expectedValue = expectedValue;
	}

}