package aoc.day07;

import aoc.resource.ResourceResolver;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

public class BagRuleReader {

    public List<String> readLinesFromFile(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines("aoc/day07/" + inputFile);
        return lines;
    }

    public BagRepository readRulesFromLines(List<String> lines) throws Exception {
        BagRepository repository = BagRepository.builder()
                .entityRepository(new BagEntityRepository())
                .ruleRepository(new BagRuleRepository())
                .build();
        for(String line : lines){
            BagRule rule = readRuleFromLine(line,
                    repository.getEntityRepository());
            repository.getRuleRepository().addBagRule(rule);
        }
        return repository;
    }

    public BagRule readRuleFromLine(String line,
                                    BagEntityRepository repository) throws Exception {
        List<String> tokens = Arrays.asList(line.split(" "));
        if (tokens.size() < 7) {
            throw new RuntimeException("Unable to parse line: " + line);
        }
        return parseBagRule(tokens,0,repository);
    }

    public BagRule parseBagRule(final List<String> tokens,
                                final int start,
                                final BagEntityRepository repository)
            throws NumberFormatException {
        BagEntity entity = findBagEntity(tokens, start, repository);
        BagRuleRepository children = new BagRuleRepository();
        int pos = 4;
        while(pos < tokens.size() &&
                !"no".equals(tokens.get(pos)) &&
                !".".equals(tokens.get(pos))) {

            int quantity = Integer.parseInt(tokens.get(pos));

            pos++;

            BagEntity childEntity = findBagEntity(tokens, pos, repository);

            if(childEntity == null) {
                pos = tokens.size();
            } else {
                pos += 3;
                children.addBagRule(BagRule.builder()
                        .entity(childEntity)
                        .quantity(quantity)
                        .children(ImmutableList.of())
                        .build());
            }
        }
        return BagRule.builder()
                .entity(entity)
                .quantity(0)
                .children(children.getBagRules())
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
                .t1(tokens.get(start))
                .t2(tokens.get(start + 1))
                .build();
        return repository.findEntity(tuple);
    }

}
