package aoc.day08;

import aoc.resource.ResourceResolver;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BootCodeParser {
    public BootCode parse(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines("aoc/day08/" + inputFile);
        return parseLines(lines);
    }

    public BootCode parseLines(List<String> lines) {
        return BootCode.builder().instructions(
                lines.stream()
                        .map(this::parseInstruction)
                        .collect(toList())
        ).build();
    }

    public Instruction parseInstruction(String s) throws NumberFormatException {
        return Instruction.builder()
                .operation(Operation.operation(s.substring(0,3)))
                .offset(toInt(s.substring(4)))
                .build();
    }

    public int toInt(String frag) throws NumberFormatException {
        return Integer.parseInt(frag);
    }
}
