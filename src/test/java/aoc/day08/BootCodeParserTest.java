package aoc.day08;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static aoc.day08.Operation.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BootCodeParserTest {

    private BootCodeParser underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new BootCodeParser();
    }

    @Test
    public void shouldParseFromFile() throws Exception {
        final String input = "example-input.txt";
        final BootCode expected = testBootCode();
        BootCode actual = underTest.parse(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldParseLines() {
        final List<String> lines = ImmutableList.of(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6"
        );
        final BootCode expected = testBootCode();
        BootCode actual = underTest.parseLines(lines);
        assertThat(actual).isEqualTo(expected);
    }

    private BootCode testBootCode() {
        return BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder().operation(NOP).offset(0).build()
                        ,Instruction.builder().operation(ACC).offset(1).build()
                        ,Instruction.builder().operation(JMP).offset(4).build()
                        ,Instruction.builder().operation(ACC).offset(3).build()
                        ,Instruction.builder().operation(JMP).offset(-3).build()
                        ,Instruction.builder().operation(ACC).offset(-99).build()
                        ,Instruction.builder().operation(ACC).offset(1).build()
                        ,Instruction.builder().operation(JMP).offset(-4).build()
                        ,Instruction.builder().operation(ACC).offset(6).build()
                ))
                .build();
    }
}