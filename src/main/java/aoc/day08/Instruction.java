package aoc.day08;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Instruction {
    private Operation operation;
    private int offset;
    private int count;
    @Builder.Default
    private int flipper = 0;

    public int incrementCount() {
        return ++count;
    }

    public int incrementFlipper() {
        return ++flipper;
    }

    public void resetCount() {
        count = 0;
    }

    public boolean isUnFlipped() {
        return flipper == 0;
    }

    public boolean isFlipped() {
        return flipper == 1;
    }

    public boolean isPostFlipped() {
        return flipper > 1;
    }
}
