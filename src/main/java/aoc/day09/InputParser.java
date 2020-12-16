package aoc.day09;

import aoc.resource.ResourceResolver;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InputParser {

    public List<BigDecimal> parse(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines("aoc/day09/" + inputFile);
        return parseLines(lines);
    }

    public List<BigDecimal> parseLines(List<String> lines) {
        return lines.stream()
                .map(this::parseBigDecimal)
                .collect(toList());
    }

    public BigDecimal parseBigDecimal(String s) throws NumberFormatException {
        return toBigDecimal(s);
    }

    public BigDecimal toBigDecimal(String frag) throws NumberFormatException {
        return new BigDecimal(frag);
    }
}
