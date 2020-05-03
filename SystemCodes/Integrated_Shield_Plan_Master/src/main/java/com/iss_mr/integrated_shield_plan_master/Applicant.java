package com.iss_mr.integrated_shield_plan_master;

/**
 * This class was automatically generated by the data modeler tool.
 */

@javax.persistence.Entity
public class Applicant implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label("NRIC")
	private String id;

	@org.kie.api.definition.type.Label("Name")
	private java.lang.String name;

	@org.kie.api.definition.type.Label("Age")
	private java.lang.Integer age;

	@org.kie.api.definition.type.Label("Gender")
	private java.lang.String gender;

	@org.kie.api.definition.type.Label("Nationality")
	private java.lang.String nationality;

	@org.kie.api.definition.type.Label("Singapore Status")
	private java.lang.String spstatus;

	@org.kie.api.definition.type.Label(value = "Family Size")
	private java.lang.Integer familySize;

	@org.kie.api.definition.type.Label(value = "Income")
	private java.lang.Integer income;

	@org.kie.api.definition.type.Label(value = "Expenditure")
	private java.lang.Integer expenditure;

	@org.kie.api.definition.type.Label(value = "Loan Amount")
	private java.lang.Integer loanAmount;

	public Applicant() {
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.String getGender() {
		return this.gender;
	}

	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	public java.lang.String getNationality() {
		return this.nationality;
	}

	public void setNationality(java.lang.String nationality) {
		this.nationality = nationality;
	}

	public java.lang.String getSpstatus() {
		return this.spstatus;
	}

	public void setSpstatus(java.lang.String spstatus) {
		this.spstatus = spstatus;
	}

	public java.lang.Integer getFamilySize() {
		return this.familySize;
	}

	public void setFamilySize(java.lang.Integer familySize) {
		this.familySize = familySize;
	}

	public java.lang.Integer getIncome() {
		return this.income;
	}

	public void setIncome(java.lang.Integer income) {
		this.income = income;
	}

	public java.lang.Integer getExpenditure() {
		return this.expenditure;
	}

	public void setExpenditure(java.lang.Integer expenditure) {
		this.expenditure = expenditure;
	}

	public java.lang.Integer getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(java.lang.Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Applicant(java.lang.String id, java.lang.String name,
			java.lang.Integer age, java.lang.String gender,
			java.lang.String nationality, java.lang.String spstatus,
			java.lang.Integer familySize, java.lang.Integer income,
			java.lang.Integer expenditure, java.lang.Integer loanAmount) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
		this.spstatus = spstatus;
		this.familySize = familySize;
		this.income = income;
		this.expenditure = expenditure;
		this.loanAmount = loanAmount;
	}

}