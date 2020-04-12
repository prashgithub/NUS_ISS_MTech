package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PolicyFeature {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
}
