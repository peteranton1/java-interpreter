package aoc.day07;

import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BagRuleRepositoryTest {

    private BagRuleRepository underTest;

    @Before
    public void setUp() {
        underTest = new BagRuleRepository();
    }

    @Test
    public void shouldAddAndGetBagRules() {
        TypeTuple typeTuple = TypeTuple.builder()
                .t1("A")
                .t2("B").build();
        BagEntity bagEntity = BagEntity.builder()
                .typeTuple(typeTuple)
                .build();
        BagRule bagRule = BagRule.builder()
                .entity(bagEntity)
                .build();

        underTest.addBagRule(bagRule);
        List<BagRule> expected = ImmutableList.of(bagRule);
        List<BagRule> actual = underTest.getBagRules();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}