package aoc.day08;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BootCodeTest {

    @Test
    public void shouldIgnoreWhenACC() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.ACC)
                                .build()
                ))
                .build();
        bootCode.patch(0);
        Assertions.assertThat(bootCode.getInstructions().stream().findAny().orElse(null))
                .isEqualTo(Instruction.builder()
                        .offset(0)
                        .count(0)
                        .flipper(0)
                        .operation(Operation.ACC)
                        .build());
    }

    @Test
    public void shouldPatchWhenNOP() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.NOP)
                                .build()
                ))
                .build();
        bootCode.patch(0);
        Assertions.assertThat(bootCode.getInstructions().stream().findAny().orElse(null))
                .isEqualTo(Instruction.builder()
                        .offset(0)
                        .count(0)
                        .flipper(1)
                        .operation(Operation.NOP)
                        .build());
    }

    @Test
    public void shouldPatchWhenJMP() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.JMP)
                                .build()
                ))
                .build();
        bootCode.patch(0);
        Assertions.assertThat(bootCode.getInstructions().stream().findAny().orElse(null))
                .isEqualTo(Instruction.builder()
                        .offset(0)
                        .count(0)
                        .flipper(1)
                        .operation(Operation.JMP)
                        .build());
    }

    @Test
    public void shouldPatchAgainWhenNOP() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.NOP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.NOP)
                                .build()
                ))
                .build();
        for (int i = 0; i < bootCode.getInstructions().size(); i++) {
            bootCode.patch(i);
        }
        Assertions.assertThat(bootCode.getInstructions())
                .isEqualTo(ImmutableList.of(Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(2)
                                .operation(Operation.NOP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.NOP)
                                .build()));
    }

    @Test
    public void shouldPatchAgainWhenJMP() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.JMP)
                                .build()
                ))
                .build();
        for (int i = 0; i < bootCode.getInstructions().size(); i++) {
            bootCode.patch(i);
        }
        Assertions.assertThat(bootCode.getInstructions())
                .isEqualTo(ImmutableList.of(Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(2)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.JMP)
                                .build()));
    }

    @Test
    public void shouldPatchAgainAgainWhenJMP() {
        BootCode bootCode = BootCode.builder()
                .instructions(ImmutableList.of(
                        Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(2)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(0)
                                .operation(Operation.JMP)
                                .build()
                ))
                .build();
        for (int i = 0; i < bootCode.getInstructions().size(); i++) {
            bootCode.patch(i);
        }
        Assertions.assertThat(bootCode.getInstructions())
                .isEqualTo(ImmutableList.of(Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(2)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(2)
                                .operation(Operation.JMP)
                                .build()
                        , Instruction.builder()
                                .offset(0)
                                .count(0)
                                .flipper(1)
                                .operation(Operation.JMP)
                                .build()
                ));
    }
}
