package aoc.day04;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

public class PassportValidatorTest {

    private PassportValidator underTest;

    @Before
    public void setUp() {
        underTest = new PassportValidator();
    }

    @Test
    public void shouldValidate() {
        List<Passport> inputs = ImmutableList.of(
                new Passport()
                        .withField(Field.PID.getCode(), "087499704")
                        .withField(Field.HGT.getCode(), "74in")
                        .withField(Field.ECL.getCode(), "grn")
                        .withField(Field.IYR.getCode(), "2012")
                        .withField(Field.EYR.getCode(), "2030")
                        .withField(Field.BYR.getCode(), "1980")
                        .withField(Field.HCL.getCode(), "#623a2f"),
                new Passport()
                        .withField(Field.EYR.getCode(), "1972")
                        .withField(Field.CID.getCode(), "100")
                        .withField(Field.HCL.getCode(), "#18171d")
                        .withField(Field.ECL.getCode(), "amb")
                        .withField(Field.HGT.getCode(), "170")
                        .withField(Field.PID.getCode(), "186cm")
                        .withField(Field.IYR.getCode(), "2018")
                        .withField(Field.BYR.getCode(), "1926")
        );
        List<List<Integer>> expecteds = ImmutableList.of(
                ImmutableList.of(1, 1, 1, 1, 1, 1, 1, 1)
                ,ImmutableList.of(1, 1, 0, 0, 1, 1, 0, 1)
        );
        for (int i = 0; i < expecteds.size(); i++) {
            Passport input = inputs.get(i);
            List<Integer> expected = expecteds.get(i);
            List<Integer> actual = underTest.validFields(input);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    public void shouldValidateBirthYears() {
        List<String> inputs = ImmutableList.of(
                "2002",
                "1980",
                "2003"
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                1,
                0
        );
        testValidField(Field.BYR,
                p -> underTest.validBirthYear(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateIssueYears() {
        List<String> inputs = ImmutableList.of(
                "2010",
                "2021"
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                0
        );
        testValidField(Field.IYR,
                p -> underTest.validIssueYear(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateExpirationYears() {
        List<String> inputs = ImmutableList.of(
                "2020",
                "2031"
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                0
        );
        testValidField(Field.EYR,
                p -> underTest.validExpirationYear(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateHeights() {
        List<String> inputs = ImmutableList.of(
                "60in",
                "190cm",
                "190in",
                "190"
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                1,
                0,
                0
        );
        testValidField(Field.HGT,
                p -> underTest.validHeight(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateHairColor() {
        List<String> inputs = ImmutableList.of(
                "#123abc",
                "#623a2f",
                "#123abz",
                "123abc"
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                1,
                0,
                0
        );
        testValidField(Field.HCL,
                p -> underTest.validHairColor(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateEyeColor() {
        List<String> inputs = ImmutableList.of(
                "brn",
                "grn",
                "wat",
                "brn oth",
                ""
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                1,
                0,
                0,
                0
        );
        testValidField(Field.ECL,
                p -> underTest.validEyeColor(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidatePassportIdValue() {
        List<String> inputs = ImmutableList.of(
                "000000001",
                "0123456789",
                ""
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                0,
                0
        );
        testValidField(Field.PID,
                p -> underTest.validPassportId(p),
                inputs, expecteds);
    }

    @Test
    public void shouldValidateCountryIdValue() {
        List<String> inputs = ImmutableList.of(
                "bananas",
                "123",
                ""
        );
        List<Integer> expecteds = ImmutableList.of(
                1,
                1,
                1
        );
        testValidField(Field.CID,
                p -> underTest.validCountryId(p),
                inputs, expecteds);
    }

    private void testValidField(Field yr,
                                Function<Passport, Integer> fn,
                                List<String> inputs,
                                List<Integer> expecteds) {
        Passport passport = new Passport();
        for (int i = 0; i < expecteds.size(); i++) {
            String input = inputs.get(i);
            int expected = expecteds.get(i);
            passport.setField(yr.getCode(), input);
            int actual = fn.apply(passport);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

}