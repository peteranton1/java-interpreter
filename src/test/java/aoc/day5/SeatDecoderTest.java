package aoc.day5;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SeatDecoderTest {

    private SeatDecoder underTest;

    @Before
    public void setUp() {
        underTest = new SeatDecoder();
    }

    @Test
    public void shouldReturnSeatNosGap() {
        List<SeatNo> input = Arrays.asList(
                new SeatNo(10, 1),
                new SeatNo(10, 2),
                new SeatNo(10, 3),
                new SeatNo(10, 4),
                new SeatNo(10, 5),
                new SeatNo(10, 6),
                new SeatNo(11, 1),
                new SeatNo(11, 2)
        );
        SeatNo expected = new SeatNo(11, 0);
        SeatNo actual = underTest.findSeatNoGap(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnMaxSeatNo() {
        SeatNo expected = new SeatNo(127, 7);
        List<SeatNo> input = ImmutableList.of(
                expected,
                new SeatNo(0, 0),
                new SeatNo(70, 7),
                new SeatNo(14, 7),
                new SeatNo(102, 4)
        );
        SeatNo actual = underTest.maxSeatNo(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnValidSeatsFromFile() throws Exception {
        List<String> inputs = underTest.getLines(
                "example-input.txt"
        );
        List<SeatNo> expecteds = ImmutableList.of(
                new SeatNo(127, 7),
                new SeatNo(0, 0),
                new SeatNo(70, 7),
                new SeatNo(14, 7),
                new SeatNo(102, 4)
        );
        for (int testNo = 0; testNo < inputs.size(); testNo++) {
            String input = inputs.get(testNo);
            SeatNo expected = expecteds.get(testNo);
            SeatNo actual = underTest.decode(input);
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    public void shouldReturnValidSeats() {
        List<String> inputs = ImmutableList.of(
                "BBBBBBBRRR: row 127, column 7, seat ID ?.",
                "FFFFFFFLLL: row 0, column 0, seat ID ?.",
                "BFFFBBFRRR: row 70, column 7, seat ID 567.",
                "FFFBBBFRRR: row 14, column 7, seat ID 119.",
                "BBFFBBFRLL: row 102, column 4, seat ID 820."
        );
        List<SeatNo> expecteds = ImmutableList.of(
                new SeatNo(127, 7),
                new SeatNo(0, 0),
                new SeatNo(70, 7),
                new SeatNo(14, 7),
                new SeatNo(102, 4)
        );
        for (int testNo = 0; testNo < inputs.size(); testNo++) {
            String input = inputs.get(testNo);
            SeatNo expected = expecteds.get(testNo);
            SeatNo actual = underTest.decode(input);
            assertThat(actual).isEqualTo(expected);
        }
    }

}