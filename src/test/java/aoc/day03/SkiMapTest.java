package aoc.day03;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SkiMapTest {

    private SkiMap underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new SkiMap("example-input.txt");
    }

    @Test
    public void shouldMove3aright1downTime() {
        final int[] expecteds = {0,1,0,1,1,0,1,1,1,1,0,0,0,0};
        final int rowStep = 3;
        final int colStep = 1;
        for(int expected: expecteds) {
            int actual = underTest.move(rowStep, colStep);
            assertThat(actual).isEqualTo(expected);
        }
        assertThat(underTest.isEof()).isTrue();
    }

}