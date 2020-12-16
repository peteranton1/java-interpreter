package aoc.day08;

import lombok.Getter;

import java.util.List;

import static aoc.day08.Operation.*;
import static java.lang.String.format;

public class Accumulator {

    @Getter
    private int accumulation = 0;
    @Getter
    private int sp = 0;
    @Getter
    private final BootCode bootCode;

    public Accumulator(BootCode bootCode) {
        reset();
        this.bootCode = bootCode;
    }

    public void reset() {
        this.accumulation = 0;
        this.sp = 0;
    }

    public int accumulate() {
        bootCode.resetCounts();
        reset();
        List<Instruction> stack = bootCode.getInstructions();
        while (sp < stack.size()) {
            Instruction instruction = stack.get(sp);
            if (isLooping(instruction)) {
                return accumulation;
            }
            executeInstruction(instruction);
        }
        return accumulation;
    }

    public int patchAndAccumulate(int loopNo) {
        bootCode.resetCounts();
        reset();
        int stepNo = 0;
        List<Instruction> stack = bootCode.getInstructions();
        while (sp < stack.size()) {
            stepNo++;
            if(stepNo <= loopNo){
                bootCode.patch(sp);
            }
            Instruction instruction = stack.get(sp);
            if (isLooping(instruction)) {
                return accumulation;
            }
            executeInstruction(instruction);
        }
        return accumulation;
    }

    public String spMessage() {
        return format("sp: %s, stackSize: %s, at end: %s",
                getSp(), stackSize(), hasRunFully());
    }

    public int stackSize() {
        return getBootCode().getInstructions().size();
    }

    public boolean hasRunFully() {
        return getSp() == stackSize();
    }

    private boolean isLooping(Instruction instruction) {
        if (instruction.incrementCount() > 1) {
            System.out.print("Loop Detected. ");
            return true;
        }
        return false;
    }

    private void executeInstruction(Instruction instruction) {
        Operation operation = instruction.getOperation();
        if (operation.equals(NOP)) {
            if (instruction.isFlipped()) {
                sp += instruction.getOffset();
            } else {
                sp++;
            }
        } else if (operation.equals(ACC)) {
            accumulation += instruction.getOffset();
            sp++;
        } else if (operation.equals(JMP)) {
            if (!instruction.isFlipped()) {
                sp += instruction.getOffset();
            } else {
                sp++;
            }
        }
    }
}
