package aoc.day06;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

import static aoc.day06.Question.toField;

@Getter
@ToString
@EqualsAndHashCode
public class IndForm {
    private final int groupId;
    private final int indId;
    private int respondents;
    private final Map<Question, Integer> fields;

    public IndForm(int groupId, int indId) {
        this.groupId = groupId;
        this.indId = indId;
        this.respondents = 0;
        this.fields = new HashMap<>();
    }

    public void incrementRespondents() {
        this.respondents += 1;
    }

    private Map<Question, Integer> initialiseFields() {
        Map<Question, Integer> output = new HashMap<>();
        for (Question qn : Question.values()) {
            output.put(qn, 0);
        }
        return output;
    }

    public void setField(String key, Integer value) {
        fields.put(toField(key), value);
    }

    public IndForm withField(String key, Integer value) {
        fields.put(toField(key), value);
        return this;
    }

    public IndForm withRespondents(int respondents) {
        this.respondents = respondents;
        return this;
    }

    public int total1() {
        return fields.size();
    }

    public int total2() {
        int output = 0;
        for (Integer value : fields.values()) {
            if (value == respondents) {
                output++;
            }
        }
        return output;
    }

    public Integer getField(String key) {
        return fields.get(toField(key));
    }
}
