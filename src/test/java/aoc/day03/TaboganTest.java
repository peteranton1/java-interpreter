package aoc.day03;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaboganTest {

    private Tabogan underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new Tabogan("example-input.txt");
    }

    @Test
    public void shouldCalculateCorrectTrees1x1() {
        final int expected = 2;
        int actual = underTest.run(1,1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCalculateCorrectTrees3x1() {
        final int expected = 7;
        int actual = underTest.run(3,1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCalculateCorrectTrees5x1() {
        final int expected = 3;
        int actual = underTest.run(5,1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCalculateCorrectTrees7x1() {
        final int expected = 4;
        int actual = underTest.run(7,1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCalculateCorrectTrees1x2() {
        final int expected = 2;
        int actual = underTest.run(1,2);
        assertThat(actual).isEqualTo(expected);
    }
}