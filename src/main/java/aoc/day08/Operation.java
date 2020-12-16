package aoc.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operation {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    private String code;
    public static Operation operation(String s) {
        for(Operation op: values()){
            if(op.code.equals(s)){
                return op;
            }
        }
        return null;
    }
}
