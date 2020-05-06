package web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import web.dao.ApplicationDto;
import web.service.CalcService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Data
public class Question {
    private @Id
    @GeneratedValue
    Long id;
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

    public String[] getExtraDataValuesAsArray(ApplicationDto applicationDto) {
        List<String> retVal = new ArrayList<>();
        String extraData = getExtraData();
        try {
            List<Map<String, String>> listOfMap = getExtraDataAsListOfMap(applicationDto);
            for (Map<String, String> map : listOfMap) map.forEach((k, v) -> retVal.add(v.toString()));
            return retVal.toArray(new String[0]);
        } catch (Exception e) {
            return extraData.split(",");
        }
    }

    public List<Map<String, String>> getExtraDataAsListOfMap(ApplicationDto applicationDto) {
        try {
            return objectMapper.readValue(extraData, List.class);
        } catch (Exception e) {
            //its not JSON then dynamic range
            List<Map<String, String>> listOfMap = new ArrayList<>();
            try {
                String postFix = extraData.startsWith("POST_FIX:") ? extraData.split("POST_FIX:")[1].trim() : "";
                CalcService calcService = applicationDto.getCalcService();
                if (value.equalsIgnoreCase("Premium")) {
                    String userAge = applicationDto.getAge();
                    List<Map<String, String>> collect = calcService.getAvailablePremiumsForAge(userAge)
                            .stream().map(k -> Collections.singletonMap(k.toString(), k + " " + (postFix.isEmpty() ? "SGD" : postFix)))
                            .collect(Collectors.toList());
                    listOfMap.addAll(collect);
                } else {//must be a feature
                    listOfMap.addAll(calcService.getAvailableValuesForFeature(value)
                            .stream()
                            .map(k -> Collections.singletonMap(k.toString(), k + " " + (postFix.isEmpty() ? "" : postFix)))
                            .collect(Collectors.toList())
                    );
                }
            } catch (Exception e2) {
                System.out.println("IGNORING EXCEPTION...");
                e2.printStackTrace();
            }
            return listOfMap;
        }
    }
}
