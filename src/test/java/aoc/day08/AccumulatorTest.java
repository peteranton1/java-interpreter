package aoc.day08;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static aoc.day08.Operation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AccumulatorTest {

    @Test
    public void shouldAccumulateInput() throws Exception {
        String input = "input.txt";
        final int expected = 1797;
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        int loop = 0;
        bootCode.resetCounts();
        Accumulator accumulator = new Accumulator(bootCode);
        int actual = accumulator.accumulate();
        System.out.printf("loop[%s]: result: %s, %s%n",
                loop, actual, accumulator.spMessage());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldAccumulateExample() throws Exception {
        String input = "example-input.txt";
        final int expected = 5;
        BootCodeParser parser = new BootCodeParser();
        BootCode bootCode = parser.parse(input);
        int loop = 0;
        bootCode.resetCounts();
        Accumulator accumulator = new Accumulator(bootCode);
        int actual = accumulator.accumulate();
        System.out.printf("loop[%s]: result: %s, %s%n",
                loop, actual, accumulator.spMessage());
        assertThat(actual).isEqualTo(expected);
    }

    private BootCode testBootCode() {
        return BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder().operation(NOP).offset(0).build()
                        , Instruction.builder().operation(ACC).offset(1).build()
                        , Instruction.builder().operation(JMP).offset(4).build()
                        , Instruction.builder().operation(ACC).offset(3).build()
                        , Instruction.builder().operation(JMP).offset(-3).build()
                        , Instruction.builder().operation(ACC).offset(-99).build()
                        , Instruction.builder().operation(ACC).offset(1).build()
                        , Instruction.builder().operation(JMP).offset(-4).build()
                        , Instruction.builder().operation(ACC).offset(6).build()
                ))
                .build();
    }
}