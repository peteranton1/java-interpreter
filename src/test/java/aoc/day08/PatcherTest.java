package aoc.day08;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatcherTest {

    @Test
    public void shouldRunNoPatchExample() throws Exception {
        String input = "example-input.txt";
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        Accumulator accumulator = new Accumulator(bootCode);
        final int expected = 5;
        int actual = 0;
        bootCode.resetCounts();
        actual = accumulator.accumulate();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldRunAllPatchExample() throws Exception {
        String input = "example-input.txt";
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        Accumulator accumulator = new Accumulator(bootCode);
        final int expected = 8;
        int actual = 0;
        for (int loopNo = 0; loopNo < bootCode.getInstructions().size() &&
                !accumulator.hasRunFully(); loopNo++) {
            accumulator.reset();
            actual = accumulator.patchAndAccumulate(loopNo);
            System.out.printf("Loop %s: %s, Fully: %s%n", loopNo,
                    actual, accumulator.hasRunFully());
        }
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldRunAllPatchFull() throws Exception {
        String input = "input.txt";
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        Accumulator accumulator = new Accumulator(bootCode);
        final int expected = 1036;
        int actual = 0;
        for (int loopNo = 0; loopNo < bootCode.getInstructions().size() &&
                !accumulator.hasRunFully(); loopNo++) {
            accumulator.reset();
            actual = accumulator.patchAndAccumulate(loopNo);
            System.out.printf("Loop %s: %s, Fully: %s%n", loopNo,
                    actual, accumulator.hasRunFully());
        }
        assertThat(actual).isEqualTo(expected);
    }
}