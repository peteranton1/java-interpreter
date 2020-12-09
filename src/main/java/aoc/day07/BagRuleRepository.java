package aoc.day07;

import java.util.LinkedHashSet;
import java.util.Set;

public class BagRuleRepository {
    private final Set<BagRule> bagRules;

    public BagRuleRepository() {
        this.bagRules = new LinkedHashSet<>();
    }

    public void addBagRule(BagRule bagRule) {
        bagRules.add(bagRule);
    }

    public Set<BagRule> getBagRules() {
        return bagRules;
    }
}
