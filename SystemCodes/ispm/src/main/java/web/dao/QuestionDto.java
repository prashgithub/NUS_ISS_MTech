package web.dao;

import lombok.Data;

@Data
public class QuestionDto {
    private long qid = 0;
    private String answer = "0";
    private String preference = "0";
}
