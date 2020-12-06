package aoc.day04;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

import static aoc.day04.Field.toField;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Passport {
    private final Map<Field, String> fields = new HashMap<>();

    public void setField(String key, String value) {
        fields.put(toField(key), value);
    }

    public Passport withField(String key, String value) {
        fields.put(toField(key), value);
        return this;
    }

    public String getField(String key) {
        return fields.get(toField(key));
    }

    public int mandatoryFieldsPresent() {
        int missing = 0;
        for (Field key : Field.values()) {
            if (fields.get(key) == null &&
                    key.isMandatory()) {
                missing += 1;
            }
        }
        return missing == 0 ? 1 : 0;
    }
}
