{"id":"dbad1c06-21b7-4c9b-b367-3e7420743d72","name":"Application","model":{"source":"INTERNAL","className":"com.iss_mr.integrated_shield_plan_master.Application","name":"Application","properties":[{"name":"id","typeInfo":{"type":"BASE","className":"long","multiple":false},"metaData":{"entries":[]}},{"name":"applicant","typeInfo":{"type":"OBJECT","className":"com.iss_mr.integrated_shield_plan_master.Applicant","multiple":false},"metaData":{"entries":[{"name":"field-label","value":"Applicant"},{"name":"field-placeHolder","value":"Applicant"}]}},{"name":"issuer","typeInfo":{"type":"BASE","className":"java.lang.String","multiple":false},"metaData":{"entries":[{"name":"field-label","value":"Issuer"},{"name":"field-placeHolder","value":"Issuer"}]}},{"name":"matchedPolicy","typeInfo":{"type":"OBJECT","className":"com.iss_mr.integrated_shield_plan_master.Policy","multiple":false},"metaData":{"entries":[{"name":"field-label","value":"Matched Policy"},{"name":"field-placeHolder","value":"Matched Policy"}]}}],"formModelType":"org.kie.workbench.common.forms.data.modeller.model.DataObjectFormModel"},"fields":[{"maxLength":100,"placeHolder":"Issuer","id":"field_9113","name":"issuer","label":"Issuer","required":true,"readOnly":false,"validateOnChange":true,"helpMessage":"","binding":"issuer","standaloneClassName":"java.lang.String","code":"TextBox","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.textBox.definition.TextBoxFieldDefinition"},{"nestedForm":"f959dbf0-6d50-4437-9ff6-4ccb17de2935","container":"FIELD_SET","id":"field_8536","name":"matchedPolicy","label":"Matched Policy","required":false,"readOnly":false,"validateOnChange":true,"helpMessage":"","binding":"matchedPolicy","standaloneClassName":"com.iss_mr.integrated_shield_plan_master.Policy","code":"SubForm","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.relations.subForm.definition.SubFormFieldDefinition"},{"nestedForm":"b6c83a5b-a921-4610-b50c-64efc3b5620f","container":"FIELD_SET","id":"field_50574","name":"applicant","label":"Applicant","required":false,"readOnly":false,"validateOnChange":true,"helpMessage":"","binding":"applicant","standaloneClassName":"com.iss_mr.integrated_shield_plan_master.Applicant","code":"SubForm","serializedFieldClassName":"org.kie.workbench.common.forms.fields.shared.fieldTypes.relations.subForm.definition.SubFormFieldDefinition"}],"layoutTemplate":{"version":2,"style":"FLUID","layoutProperties":{},"rows":[{"height":"12","properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[{"height":"12","properties":{},"layoutColumns":[{"span":"12","height":"6","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_50574","form_id":"dbad1c06-21b7-4c9b-b367-3e7420743d72"}}]},{"span":"12","height":"6","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_9113","form_id":"dbad1c06-21b7-4c9b-b367-3e7420743d72"}}]}]}],"layoutComponents":[]}]},{"height":"12","properties":{},"layoutColumns":[{"span":"12","height":"12","properties":{},"rows":[],"layoutComponents":[{"dragTypeName":"org.kie.workbench.common.forms.editor.client.editor.rendering.EditorFieldLayoutComponent","properties":{"field_id":"field_8536","form_id":"dbad1c06-21b7-4c9b-b367-3e7420743d72"}}]}]}]}}