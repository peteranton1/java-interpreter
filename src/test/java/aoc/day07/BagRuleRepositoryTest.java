package aoc.day07;

import com.google.common.collect.ImmutableSet;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class BagRuleRepositoryTest {

    private BagRuleRepository underTest;

    @Before
    public void setUp() {
        underTest = new BagRuleRepository();
    }

    @Test
    public void shouldAddAndGetBagRules() {
        TypeTuple typeTuple = TypeTuple.builder()
                .tuple1("A")
                .tuple2("B").build();
        BagEntity bagEntity = BagEntity.builder()
                .typeTuple(typeTuple)
                .build();
        BagRule bagRule = BagRule.builder()
                .entity(bagEntity)
                .build();

        underTest.addBagRule(bagRule);
        underTest.addBagRule(bagRule);
        Set<BagRule> expected = ImmutableSet.of(bagRule);
        Set<BagRule> actual = underTest.getBagRules();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}