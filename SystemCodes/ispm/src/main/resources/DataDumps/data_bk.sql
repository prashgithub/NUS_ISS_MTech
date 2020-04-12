
comp_policy --> comppolicy_id;"insrcomp_no";"policy_id"
 --> id, insurer_id, policy_id
 
comp_policy_feature -> WARD_CATEGORY;"WARD_TYPE";"POLICY_FEATURE";"COMPANY";"POLICY_NAME";"BENEFITS";"DESCRIPTION"

comp_policy_prem -- > COMPANY;"WARD_CATEGORY";"WARD_TYPE";"POLICY_NAME";"AGE";"PREMIUM_AMOUNT"
insrcomp  --> insrcomp_no;"comp_name";"claimproc_median"

policy -> policy_id;"policy_name"

policyfea -> id;"policyfea_sname";"policyfea_fname";"descr"

ward_type -> id;"category";"typ";"descr"



create VIEW 
	comp_pol_prem_view 
AS 
select 
	c.comp_name AS COMPANY,
	w.category AS WARD_CATEGORY,
	w.typ AS WARD_TYPE,
	p.policy_name AS POLICY_NAME,
	cpp.age AS AGE,
	cpp.prem_amount AS PREMIUM_AMOUNT 
from 
	((((insr_comp c join ward_type w) join policies p) join comppol_prem cpp) join comp_policies cp) 
where 
	((cpp.ward_id = w.ward_id) 
	and (cpp.comppolicy_id = cp.comppolicy_id) 
	and (cp.policy_id = p.policy_id) 
	and (cp.insrcomp_no = c.insrcomp_no)) 