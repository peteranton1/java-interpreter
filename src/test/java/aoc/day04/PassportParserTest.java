package aoc.day04;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PassportParserTest {

    private static final List<String> CHUNKS = ImmutableList.of(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd" +
                    " byr:1937 iyr:2017 cid:147 hgt:183cm"
            , "iyr:2013 ecl:amb cid:350 eyr:2023" +
                    " pid:028048884 hcl:#cfa07d byr:1929"
            , "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn" +
                    " pid:760753108 byr:1931 hgt:179cm"
            , "hcl:#cfa07d eyr:2025 pid:166559648" +
                    " iyr:2011 ecl:brn hgt:59in"
    );

    private PassportParser underTest;

    @Before
    public void setUp() {
        underTest = new PassportParser();
    }

    @Test
    public void shouldFindPassportsFromFile() throws Exception {
        List<Passport> expected = underTest.parseChunks(CHUNKS);
        List<Passport> actual = underTest
                .parseInputFile("example-input.txt");
        System.out.printf("Passports: %n%s%n", actual);
        assertThat(actual.size()).isEqualTo(CHUNKS.size());
        assertThat(actual.size()).isEqualTo(expected.size());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindPassportsWithChunks() {
        System.out.printf("Chunks: %n%s%n", CHUNKS);
        List<Passport> actual = underTest.parseChunks(CHUNKS);
        System.out.printf("Passports: %n%s%n", actual);
        assertThat(actual.size()).isEqualTo(CHUNKS.size());
    }

    @Test
    public void shouldFindPassportsWithEachChunk() {
        CHUNKS.forEach(this::testChunk);
    }

    private void testChunk(String chunk) {
        System.out.printf("Chunk: %s%n", chunk);
        Passport actual = underTest.parseChunk(chunk);
        System.out.printf("Passport: %s%n", actual);
        assertThat(actual.getField("pid").length()).isEqualTo(9);
    }
}