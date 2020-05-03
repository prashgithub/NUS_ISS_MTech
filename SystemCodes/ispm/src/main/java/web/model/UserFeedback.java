package web.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_feedback")
public class UserFeedback {
    @Id
    private long id;
    private String name;
    private int age;
    private String gender;
    private String status;
    private int rating;
    private String comments;
}