package aoc.day04;

import aoc.resource.ResourceResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class PassportParser {

    public List<Passport> parseInputFile(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines(
                        "aoc/day04/" + inputFile);
        List<String> chunks = new ArrayList<>();
        StringBuilder buf = new StringBuilder();
        for (String line : lines) {
            if (line == null || line.trim().length() == 0) {
                chunks.add(buf.toString().trim());
                buf.delete(0, buf.length());
            } else {
                buf.append(line);
                buf.append(" ");
            }
        }
        if(buf.length()>0) {
            chunks.add(buf.toString().trim());
        }
        return parseChunks(chunks);
    }

    public List<Passport> parseChunks(List<String> chunks) {
        List<Passport> out = new ArrayList<>();
        for (String chunk : chunks) {
            Passport passport = parseChunk(chunk);
            out.add(passport);
        }
        return out;
    }

    public Passport parseChunk(String chunk) {
        Passport passport = new Passport();
        List<String> pairLines = Arrays.asList(chunk.split(" "));
        for (String pairLine : pairLines) {
            List<String> pairs = Arrays.asList(pairLine.split(":"));
            if (pairs.size() != 2) {
                throw new RuntimeException(format(
                        "Unable to parse: %s (%s)", pairLine, pairs));
            }
            passport.setField(pairs.get(0), pairs.get(1));
        }
        return passport;
    }
}
