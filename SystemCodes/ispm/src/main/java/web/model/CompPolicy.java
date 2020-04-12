package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class CompPolicy {
    private @Id
    @GeneratedValue
    Long id;
    private Long insurerId;
    private Long policyId;
}
