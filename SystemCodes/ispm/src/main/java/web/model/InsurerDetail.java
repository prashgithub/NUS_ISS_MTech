package web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class InsurerDetail {
    private @Id
    @GeneratedValue
    Long id;
    private String providerName;
    private int startYear;
    private int currentRating;
    private int turnoverMillion;
    private int customerSize;
    private int claimDays;
}
