package aoc.day04;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Field {
    BYR("byr","Birth Year", true),
    IYR("iyr", "Issue Year", true),
    EYR("eyr", "Expiration Year", true),
    HGT("hgt","Height", true),
    HCL("hcl", "Hair Color", true),
    ECL("ecl", "Eye Color", true),
    PID("pid", "Passport ID", true),
    CID("cid", "Country ID", false);

    private String code;
    private String desc;
    private boolean mandatory;

    public static Field toField(String key) {
        return Field.valueOf(uppercaseOf(key));
    }

    private static String uppercaseOf(String key) {
        if (key == null) {
            return key;
        }
        return key.toUpperCase();
    }
}
