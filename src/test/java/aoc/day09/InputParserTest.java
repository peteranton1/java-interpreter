package aoc.day09;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;

public class InputParserTest {

    private InputParser underTest;

    @Before
    public void setUp() {
        underTest = new InputParser();
    }

    @Test
    public void shouldParseExampleInput() throws Exception {
        final List<BigDecimal> INPUT_LIST = ImmutableList.of(
                valueOf(35), valueOf(20), valueOf(15), valueOf(25), valueOf(47),
                valueOf(40), valueOf(62), valueOf(55), valueOf(65), valueOf(95),
                valueOf(102), valueOf(117), valueOf(150), valueOf(182), valueOf(127),
                valueOf(219), valueOf(299), valueOf(277), valueOf(309), valueOf(576)
        );
        List<BigDecimal> actual = underTest.parse("example-input.txt");
        Assertions.assertThat(actual).isEqualTo(INPUT_LIST);
    }
}