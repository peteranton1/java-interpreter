package aoc.day04;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PassportTest {

    public static final String[] FIELD_NAMES = {
            "byr", "iyr", "eyr", "hgt",
            "hcl", "ecl", "pid", "cid"
    };

    private Passport underTest;

    @Before
    public void setUp() {
        underTest = new Passport();
    }

    @Test
    public void shouldSetAndGetAllFieldsOk() {
        for (String key : FIELD_NAMES) {
            underTest.setField(key, key);
            String actual = underTest.getField(key);
            assertThat(actual).isEqualTo(key);
        }
    }

    @Test
    public void validWhenAllFieldsPresent() {
        Passport expected = new Passport()
                .withField("byr", "byr")
                .withField("iyr", "iyr")
                .withField("eyr", "eyr")
                .withField("hgt", "hgt")
                .withField("hcl", "hcl")
                .withField("ecl", "ecl")
                .withField("pid", "pid")
                .withField("cid", "cid");
        for (String key : FIELD_NAMES) {
            underTest.setField(key, key);
        }
        assertThat(underTest).isEqualTo(expected);
        assertThat(underTest.mandatoryFieldsPresent())
                .isEqualTo(1);
    }

    @Test
    public void validWhenMandatoryOnlyFieldsPresent() {
        Passport expected = new Passport()
                .withField("byr", "byr")
                .withField("iyr", "iyr")
                .withField("eyr", "eyr")
                .withField("hgt", "hgt")
                .withField("hcl", "hcl")
                .withField("ecl", "ecl")
                .withField("pid", "pid")
                .withField("cid", "cid");
        for (String key : FIELD_NAMES) {
            if(Field.toField(key).isMandatory()) {
                underTest.setField(key, key);
            }
        }
        assertThat(underTest.mandatoryFieldsPresent())
                .isEqualTo(1);
    }

    @Test
    public void invalidWhenNotAllMandatoryFieldsPresent() {
        Passport expected = new Passport()
                .withField("byr", "byr")
                .withField("iyr", "iyr")
                .withField("eyr", "eyr")
                .withField("hgt", "hgt")
                .withField("cid", "cid");
        for (String key : FIELD_NAMES) {
            if(expected.getField(key) != null) {
                underTest.setField(key, key);
            }
        }
        assertThat(underTest).isEqualTo(expected);
        assertThat(underTest.mandatoryFieldsPresent())
                .isEqualTo(0);
    }

}