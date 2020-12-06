package aoc.day04;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class PassportValidator {

    public List<Integer> validFields(Passport passport) {
        return ImmutableList.of(
                validBirthYear(passport)
                , validIssueYear(passport)
                , validExpirationYear(passport)
                , validHeight(passport)
                , validHairColor(passport)
                , validEyeColor(passport)
                , validPassportId(passport)
                , validCountryId(passport)
        );
    }

    // byr (Birth Year) - four digits; at least 1920 and at most 2002.
    public int validBirthYear(Passport passport) {
        return validYear(passport, Field.BYR, 1920, 2002);
    }

    // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    public int validIssueYear(Passport passport) {
        return validYear(passport, Field.IYR, 2010, 2020);
    }

    // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    public int validExpirationYear(Passport passport) {
        return validYear(passport, Field.EYR, 2020, 2030);
    }

    // hgt (Height) - a number followed by either cm or in:
    //    If cm, the number must be at least 150 and at most 193.
    //    If in, the number must be at least 59 and at most 76.
    public int validHeight(Passport passport) {
        int[] lowers = {150,59};
        int[] uppers = {193,76};
        String valueWithScale = passport.getField(Field.HGT.getCode());
        return validHeightValue(valueWithScale,lowers,uppers);
    }

    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    public int validHairColor(Passport passport) {
        String validChars = "0123456789abcdefABCDEF";
        String valueWithHash = passport.getField(Field.HCL.getCode());
        return validHexColorValue(valueWithHash,validChars);
    }

    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    public int validEyeColor(Passport passport) {
        String validValues = "amb blu brn gry grn hzl oth";
        String value = passport.getField(Field.ECL.getCode());
        return validEyeColorValue(value,validValues);
    }

    // pid (Passport ID) - a nine-digit number, including leading zeroes.
    public int validPassportId(Passport passport) {
        String validChars = "0123456789";
        String value = passport.getField(Field.PID.getCode());
        return validPidValue(value,validChars);
    }

    // cid (Country ID) - ignored, missing or not.
    public int validCountryId(Passport passport) {
        return 1;
    }

    private int validEyeColorValue(String value, String validValues) {
        if (value != null && value.length() == 3) {
            for(String ref: validValues.split(" ")) {
                if(value.equals(ref)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private int validHexColorValue(String valueWithHash, String validChars) {
        if (valueWithHash != null && valueWithHash.startsWith("#") && valueWithHash.length() == 7) {
            String value = valueWithHash.substring(1);
            return validCharacters(value, validChars);
        }
        return 0;
    }

    private int validPidValue(String value, String validChars) {
        if (value != null && value.length() == 9) {
            return validCharacters(value, validChars);
        }
        return 0;
    }

    private int validCharacters(String value, String validChars) {
        for(char ch: value.toCharArray()) {
            if(-1 == validChars.indexOf(ch)) {
                return 0;
            }
        }
        return 1;
    }

    private int validHeightValue(String valueWithScale, int[] lowers, int[] uppers) {
        if (valueWithScale != null && valueWithScale.length() > "cm".length()) {
            String value = valueWithScale.substring(0, valueWithScale.length() - 2);
            String cmOrIn = valueWithScale.substring(valueWithScale.length() - 2);
            int lower = 0;
            int upper = 0;
            if (cmOrIn.equals("cm")) {
                lower = lowers[0];
                upper = uppers[0];
            } else if (cmOrIn.equals("in")) {
                lower = lowers[1];
                upper = uppers[1];
            }
            if (lower > 0) {
                return validNumericalString(value, lower, upper);
            }
        }
        return 0;
    }

    private int validYear(Passport passport, Field yr, int lower, int upper) {
        String value = passport.getField(yr.getCode());
        return validNumericalString(value, lower, upper);
    }

    private int validNumericalString(String value, int lower, int upper) {
        try {
            int valueInt = Integer.parseInt(value);
            if (valueInt >= lower && valueInt <= upper) {
                return 1;
            }
        } catch (Exception e) {
            System.out.printf("Invalid value: %s: %s%n",
                    value, e.getMessage());
        }
        return 0;
    }
}
