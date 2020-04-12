package web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class WardType {
    private @Id
    @GeneratedValue
    Long id;
    private String wardCategory;
    private String wardName;
    private String description;
}
