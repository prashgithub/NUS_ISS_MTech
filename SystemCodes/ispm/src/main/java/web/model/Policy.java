package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Policy {
    private @Id
    @GeneratedValue
    Long id;
    private String insurer;
    private String policyName;
}
