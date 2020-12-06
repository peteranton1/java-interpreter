package aoc.day06;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FormReaderTest {

    private FormReader underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new FormReader();
    }

    @Test
    public void shouldReadInputFile() throws Exception {
        String input = "example-input.txt";
        List<String> expecteds = ImmutableList.of(
                "abc", "", "a", "b", "c", "",
                "ab", "ac", "", "a", "a",
                "a", "a", "", "b"
        );
        List<String> actual = underTest.readInputFile(input);
        Assertions.assertThat(actual).isEqualTo(expecteds);
    }

    @Test
    public void shouldGroupTotals1() {
        List<IndForm> input = ImmutableList.of(
                new IndForm(0, 0)
                        .withField("j", 1)
                        .withField("k", 1)
                        .withField("c", 3)
                        .withRespondents(3),
                new IndForm(1, 0)
                        .withField("c", 2)
                        .withField("u", 1)
                        .withField("e", 1)
                        .withRespondents(2)
        );
        int expected = 6;
        int actual = underTest.totalCounts1(input);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldGroupTotals2() {
        List<IndForm> input = ImmutableList.of(
                new IndForm(0, 0)
                        .withField("j", 1)
                        .withField("k", 1)
                        .withField("c", 3)
                        .withRespondents(3),
                new IndForm(1, 0)
                        .withField("c", 2)
                        .withField("u", 1)
                        .withField("e", 1)
                        .withRespondents(2)
        );
        int expected = 2;
        int actual = underTest.totalCounts2(input);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldGroupList() {
        List<IndForm> input = ImmutableList.of(
                new IndForm(0, 1)
                        .withField("c", 1),
                new IndForm(0, 2)
                        .withField("j", 1)
                        .withField("c", 1),
                new IndForm(0, 3)
                        .withField("k", 1)
                        .withField("c", 1),
                new IndForm(1, 5)
                        .withField("c", 1),
                new IndForm(1, 6)
                        .withField("c", 1)
                        .withField("u", 1)
                        .withField("e", 1)
        );
        List<IndForm> expecteds = ImmutableList.of(
                new IndForm(0, 0)
                        .withField("j", 1)
                        .withField("k", 1)
                        .withField("c", 3)
                        .withRespondents(3),
                new IndForm(1, 0)
                        .withField("c", 2)
                        .withField("u", 1)
                        .withField("e", 1)
                        .withRespondents(2)
        );
        List<IndForm> actual = underTest.groupForms(input);
        Assertions.assertThat(actual).isEqualTo(expecteds);
    }

    @Test
    public void shouldParseInput() {
        List<String> input = ImmutableList.of(
                "c", "jc", "", "c", "cue"
        );
        List<IndForm> expecteds = ImmutableList.of(
                new IndForm(0, 1)
                        .withField("c", 1),
                new IndForm(0, 2)
                        .withField("j", 1)
                        .withField("c", 1),
                new IndForm(1, 4)
                        .withField("c", 1),
                new IndForm(1, 5)
                        .withField("c", 1)
                        .withField("u", 1)
                        .withField("e", 1)
        );
        List<IndForm> actual = underTest.parseInput(input);
        Assertions.assertThat(actual).isEqualTo(expecteds);
    }

    @Test
    public void shouldCreateForm() {
        List<String> input = ImmutableList.of(
                "c", "jc", "cue"
        );
        List<IndForm> expecteds = ImmutableList.of(
                new IndForm(1, 10)
                        .withField("c", 1),
                new IndForm(2, 20)
                        .withField("j", 1)
                        .withField("c", 1),
                new IndForm(3, 30)
                        .withField("c", 1)
                        .withField("u", 1)
                        .withField("e", 1)
        );
        for (int i = 0; i < input.size(); i++) {
            int groupId = i + 1;
            int indId = groupId * 10;
            String line = input.get(i);
            IndForm expected = expecteds.get(i);
            IndForm actual = underTest.createForm(groupId, indId, line);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }
}