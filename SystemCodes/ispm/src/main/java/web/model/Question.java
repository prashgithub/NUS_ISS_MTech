package web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Question {
    private @Id @GeneratedValue Long id;
    private String name;
    private String value;
    private int stage;
    private String extraData;
}
