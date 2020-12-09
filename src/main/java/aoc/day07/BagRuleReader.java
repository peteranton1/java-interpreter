package aoc.day07;

import aoc.resource.ResourceResolver;
import com.google.common.collect.ImmutableSet;

import java.util.*;

public class BagRuleReader {

    public List<String> readLinesFromFile(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines("aoc/day07" + inputFile);
        return lines;
    }

    public BagEntityRepository readRulesFromLines(List<String> lines) throws Exception {
        BagEntityRepository repository = new BagEntityRepository();
        BagRuleRepository rulesRepository = new BagRuleRepository();
        for(String line : lines){
            BagRule rule = readRuleFromLine(line, repository);
            rulesRepository.addBagRule(rule);
        }
        return repository;
    }

    public BagRule readRuleFromLine(String line,
                                    BagEntityRepository repository) throws Exception {
        List<String> tokens = Arrays.asList(line.split(" "));
        if (tokens.size() < 8) {
            throw new RuntimeException("Unable to parse line: " + line);
        }
        return parseBagRule(tokens,0,repository);
    }

    public BagRule parseBagRule(final List<String> tokens,
                                final int start,
                                final BagEntityRepository repository)
            throws NumberFormatException {
        BagEntity entity = findBagEntity(tokens, start, repository);
        Set<BagRule> children = new LinkedHashSet<>();
        int pos = 3;
        while(pos < tokens.size()) {
            int quantity = Integer.parseInt(tokens.get(pos));
            pos++;
            BagEntity childEntity = findBagEntity(tokens, pos, repository);
            if(childEntity == null) {
                pos = tokens.size();
            } else {
                pos += 2;
                children.add(BagRule.builder()
                        .entity(childEntity)
                        .quantity(quantity)
                        .children(ImmutableSet.of())
                        .build());
            }
        }
        return BagRule.builder()
                .entity(entity)
                .quantity(0)
                .children(children)
                .build();
    }

    public BagEntity findBagEntity(final List<String> tokens,
                                   final int start,
                                   final BagEntityRepository repository) {
        final String DOT = ".";
        if(DOT.equals(tokens.get(start))){
            return null;
        }
        TypeTuple tuple = TypeTuple.builder()
                .tuple1(tokens.get(start))
                .tuple2(tokens.get(start + 1))
                .build();
        return repository.findEntity(tuple);
    }

}
