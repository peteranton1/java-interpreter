package aoc.day05;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class SeatNoTest {

    @Test
    public void shouldReturnValidSeatId() {
        List<SeatNo> inputs = ImmutableList.of(
                new SeatNo(127, 7),
                new SeatNo(0, 0),
                new SeatNo(70, 7),
                new SeatNo(14, 7),
                new SeatNo(102, 4)
        );
        List<Integer> expecteds = ImmutableList.of(
                1023, //"BBBBBBBRRR: row 127, column 7, seat ID ?.",
                0, //"FFFFFFFLLL: row 0, column 0, seat ID ?.",
                567, //"BFFFBBFRRR: row 70, column 7, seat ID 567.",
                119, //"FFFBBBFRRR: row 14, column 7, seat ID 119.",
                820 //"BBFFBBFRLL: row 102, column 4, seat ID 820."
        );
        for (int testNo = 0; testNo < inputs.size(); testNo++) {
            SeatNo underTest = inputs.get(testNo);
            int expected = expecteds.get(testNo);
            int actual = underTest.getSeatId();
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

}