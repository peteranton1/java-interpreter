package aoc.day07;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BagQuestioner {

    public List<LevelEntity> howManyBagColorsContainAtLeast(
            String input, TypeTuple bagType) throws Exception {
        BagRuleReader reader = new BagRuleReader();
        List<String> lines = reader.readLinesFromFile(input);
        BagRepository repository = reader.readRulesFromLines(lines);
        List<BagRule> rules = repository.ruleRepository.getBagRules();
        BagEntity entity = repository.entityRepository.findEntity(bagType);
//        int result = findBagRuleCount(bagType, repository);
//        List<LevelEntity> level0 = bagsWithBagsWithMyBag(entity, repository, levels);
        int lev = 0;
        List<LevelEntity> levels0 = ImmutableList.of(levelOf(entity, lev));
        List<LevelEntity> levels1 = dedupeLevels(findAllParentsOf(levels0, rules));
        List<LevelEntity> levels2 = dedupeLevels(findAllParentsOf(levels1, rules));
        List<LevelEntity> levels3 = dedupeLevels(findAllParentsOf(levels2, rules));
        List<LevelEntity> levels4 = dedupeLevels(findAllParentsOf(levels3, rules));
        List<LevelEntity> levels5 = dedupeLevels(findAllParentsOf(levels4, rules));
        List<LevelEntity> levels6 = dedupeLevels(findAllParentsOf(levels5, rules));
        List<LevelEntity> levels7 = dedupeLevels(findAllParentsOf(levels6, rules));
        List<LevelEntity> levels8 = dedupeLevels(findAllParentsOf(levels7, rules));
        List<LevelEntity> levels = new ArrayList<>();
        levels.addAll(levels1);
        levels.addAll(levels2);
        levels.addAll(levels3);
        levels.addAll(levels4);
        levels.addAll(levels5);
        levels.addAll(levels6);
        levels.addAll(levels7);
        levels.addAll(levels8);
        System.out.printf("levels(%d): %s.%n",
                levels.size(), levels);
        return levels;
    }

    List<LevelEntity> dedupeLevels(List<LevelEntity> levelsIn) {
        List<LevelEntity> levelsOut = new LinkedList<>();
        for(LevelEntity levelIn: levelsIn) {
            if(!findInList(levelIn, levelsOut)){
                levelsOut.add(levelIn);
            }
        }
        return levelsOut;
    }

    boolean findInList(LevelEntity levelIn, List<LevelEntity> levels) {
        for(LevelEntity level: levels) {
            if(level.tup.equals(levelIn.tup) && level.lev == levelIn.lev){
                return true;
            }
        }
        return false;
    }

    List<LevelEntity> findAllParentsOf(List<LevelEntity> levels, List<BagRule> rules) {
        List<LevelEntity> output = new LinkedList<>();
        for(LevelEntity level: levels) {
            for(BagRule rule: rules) {
                if(rule.hasChildOf(level)) {
                    output.add(levelOf(rule, level.lev+1));
                }
            }
        }
        return output;
    }

    private LevelEntity levelOf(BagRule rule, int lev) {
        return levelOf(rule.entity, lev);
    }

    private LevelEntity levelOf(BagEntity entity, int lev) {
        return LevelEntity.builder()
                .lev(lev)
                .tup(entity.typeTuple)
                .build();
    }


    public int findBagRuleCount(TypeTuple bagType,
                                BagRepository repository) {
        BagEntity entity = repository.getEntityRepository()
                .findEntity(bagType);
        int result = 0;
        List<BagRule> bagRules = repository.getRuleRepository().getBagRules();
        for (BagRule rule : bagRules) {
            result += rule.isBagPresent(entity);
        }
        System.out.printf("count L1: %s Bag in %s rules of %s.%n",
                bagType, result, bagRules.size());
        return result;
    }

    public List<LevelEntity> bagsWithBagsWithMyBag(BagEntity entity,
                                                 BagRepository repository,
                                                 int levels) {
        List<BagRule> bagRules = repository.getRuleRepository().getBagRules();
        List<LevelEntity> others = new LinkedList<>();
        for (BagRule rule : bagRules) {
            int result = rule.isBagPresent(entity);
            if (result == 1 ) {
                LevelEntity lev = levelEntity(0, rule.getEntity());
                if(!findLevelInList(others,lev)) {
                    others.add(lev);
                }
            }
        }
        List<LevelEntity> others2 = new LinkedList<>();
        for (int level = 0; level < levels; level++) {
            for (LevelEntity lev : others) {
                if(!findLevelInList(others,lev)) {
                    others2.addAll(bagsWithMyBag(others, lev.tup, repository, level + 1));
                }
            }
        }
        others.addAll(others2);
        return others;
    }

    private boolean findLevelInList(List<LevelEntity> others, LevelEntity lev) {
        for(LevelEntity lev1: others){
            if(lev1.equals(lev)){
                return true;
            }
        }
        return false;
    }

    public List<LevelEntity> bagsWithMyBag(
            List<LevelEntity> already,
                    TypeTuple tuple, BagRepository repository, int level) {
        BagEntity entity = repository.entityRepository.findEntity(tuple);
        List<BagRule> bagRules = repository.getRuleRepository().getBagRules();
        List<LevelEntity> others = new LinkedList<>();
        for (BagRule rule : bagRules) {
            int result = rule.isBagPresent(entity);
            if (result == 1) {
                LevelEntity lev = levelEntity(level, rule.getEntity());
                if(!findLevelInList(already, lev)) {
                    others.add(lev);
                }
            }
        }
        return others;
    }

    private LevelEntity levelEntity(int level, BagEntity entity) {
        TypeTuple tuple = entity.typeTuple;
        return LevelEntity.builder()
                .tup(tuple)
                .lev(level)
                .build();
    }
}
