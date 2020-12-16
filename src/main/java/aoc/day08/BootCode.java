package aoc.day08;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
@Builder
@Value
public class BootCode {
    List<Instruction> instructions;

    public void resetCounts() {
        instructions.forEach((Instruction::resetCount));
    }

    public void patch(int i) {
        Instruction instruction = instructions.get(i);
        if (instruction.getOperation().equals(Operation.JMP) ||
                instruction.getOperation().equals(Operation.NOP)) {
            if(instruction.isUnFlipped()) {
                instruction.incrementFlipper();
            } else if(instruction.isFlipped()){
                instruction.incrementFlipper();
            }
        }
    }
}


