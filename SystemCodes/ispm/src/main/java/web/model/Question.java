package web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Entity
@Data
public class Question {
    private @Id @GeneratedValue Long id;
    private String name;
    private String value;
    private int stage;
    private String extraData;
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getExtraData() {
        return extraData;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String[] getExtraDataValuesAsArray() {
        List<String> retVal = new ArrayList<>();
        String extraData = getExtraData();
        try {
            List<Map<String,String>>listOfMap = getExtraDataAsListOfMap();
            for (Map<String, String> map  : listOfMap) map.forEach((k, v) -> retVal.add(v.toString()));
            return retVal.toArray(new String[0]);
        } catch (Exception e) {
            return extraData.split(",");
        }
    }

    public List<Map<String,String>> getExtraDataAsListOfMap(){
        try {
            return objectMapper.readValue(extraData, List.class);
        } catch (IOException e) {
            e.printStackTrace();
            return emptyList();
        }
    }
}
