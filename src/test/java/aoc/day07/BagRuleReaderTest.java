package aoc.day07;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BagRuleReaderTest {

    private BagRuleReader underTest;

    @Before
    public void setUp() {
        underTest = new BagRuleReader();
    }

    @Test
    public void shouldReadLines() throws Exception {
        List<String> expected = ImmutableList.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags."
                , "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
                , "bright white bags contain 1 shiny gold bag."
                , "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags."
                , "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags."
                , "dark olive bags contain 3 faded blue bags, 4 dotted black bags."
                , "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags."
                , "faded blue bags contain no other bags."
                , "dotted black bags contain no other bags."
        );
        List<String> actual = underTest
                .readLinesFromFile("example-input.txt");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindBagEntity() {
        List<String> tokens = ImmutableList.of(
                "posh", "olive", "bags", "contain",
                "2", "vibrant", "green", "bags", "."
        );
        BagEntityRepository repository = new BagEntityRepository();

        BagEntity actual1 = underTest
                .findBagEntity(tokens, 0, repository);
        BagEntity expected1 = repository.findEntity(TypeTuple.builder()
                .t1(tokens.get(0))
                .t2(tokens.get(1)).build());
        assertThat(actual1).isEqualTo(expected1);

        BagEntity actual2 = underTest
                .findBagEntity(tokens, 5, repository);
        BagEntity expected2 = repository.findEntity(TypeTuple.builder()
                .t1(tokens.get(5))
                .t2(tokens.get(6)).build());
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void shouldParseBagRule0Child() throws Exception {
        String line = "clear bronze bags contain no other bags.";
        List<String> tokens = Arrays.asList(line.split(" "));

        BagEntity entity1 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(0))
                        .t2(tokens.get(1)).build()).build();

        BagRule expected1 = BagRule.builder()
                .entity(entity1)
                .quantity(0)
                .children(ImmutableList.of())
                .build();

        BagEntityRepository repository = new BagEntityRepository();
        BagRule actual1 = underTest.parseBagRule(tokens, 0, repository);
        assertThat(actual1).isEqualTo(expected1);

        BagRule actual2 = underTest.readRuleFromLine(line, repository);
        assertThat(actual2).isEqualTo(expected1);

        BagRepository actual3 = underTest.readRulesFromLines(
                ImmutableList.of(line));
        assertThat(actual3.getEntityRepository()).isEqualTo(repository);

        BagRuleRepository expectedRules = new BagRuleRepository();
        expectedRules.addBagRule(expected1);
        assertThat(actual3.getRuleRepository()).isEqualTo(expectedRules);
    }

    @Test
    public void shouldParseBagRule1Child() throws Exception {
        String line = "clear bronze bags contain " +
                "2 vibrant green bags.";
        List<String> tokens = Arrays.asList(line.split(" "));

        BagEntity entity1 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(0))
                        .t2(tokens.get(1)).build()).build();

        BagEntity entity2 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(5))
                        .t2(tokens.get(6)).build()).build();

        BagRule expected1 = BagRule.builder()
                .entity(entity1)
                .quantity(0)
                .children(ImmutableList.of(BagRule.builder()
                        .entity(entity2)
                        .quantity(2)
                        .children(ImmutableList.of())
                        .build()))
                .build();

        BagEntityRepository repository = new BagEntityRepository();
        BagRule actual1 = underTest.parseBagRule(tokens, 0, repository);
        assertThat(actual1).isEqualTo(expected1);

        repository = new BagEntityRepository();
        BagRule actual2 = underTest.readRuleFromLine(line, repository);
        assertThat(actual2).isEqualTo(expected1);

        BagRepository actual3 = underTest.readRulesFromLines(
                ImmutableList.of(line));
        assertThat(actual3.getEntityRepository()).isEqualTo(repository);

        BagRuleRepository expectedRules = new BagRuleRepository();
        expectedRules.addBagRule(expected1);
        assertThat(actual3.getRuleRepository()).isEqualTo(expectedRules);
    }

    @Test
    public void shouldParseBagRule2Child() throws Exception {
        String line = "clear bronze bags contain " +
                "1 vibrant green bags, " +
                "2 plaid cyan bags.";
        List<String> tokens = Arrays.asList(line.split(" "));

        BagEntity entity1 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(0))
                        .t2(tokens.get(1)).build()).build();

        BagEntity entity2 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(5))
                        .t2(tokens.get(6)).build()).build();

        BagEntity entity3 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(9))
                        .t2(tokens.get(10)).build()).build();

        BagRule expected1 = BagRule.builder()
                .entity(entity1)
                .quantity(0)
                .children(ImmutableList.of(BagRule.builder()
                        .entity(entity2)
                        .quantity(1)
                        .children(ImmutableList.of())
                        .build(), BagRule.builder()
                        .entity(entity3)
                        .quantity(2)
                        .children(ImmutableList.of())
                        .build()))
                .build();

        BagEntityRepository repository = new BagEntityRepository();
        BagRule actual1 = underTest.parseBagRule(tokens, 0, repository);
        assertThat(actual1).isEqualTo(expected1);

        repository = new BagEntityRepository();
        BagRule actual2 = underTest.readRuleFromLine(line, repository);
        assertThat(actual2).isEqualTo(expected1);

        BagRepository actual3 = underTest.readRulesFromLines(
                ImmutableList.of(line));
        assertThat(actual3.getEntityRepository()).isEqualTo(repository);

        BagRuleRepository expectedRules = new BagRuleRepository();
        expectedRules.addBagRule(expected1);
        assertThat(actual3.getRuleRepository()).isEqualTo(expectedRules);
    }

    @Test
    public void shouldParseBagRule3Child() throws Exception {
        String line = "clear bronze bags contain " +
                "1 vibrant green bags, " +
                "2 plaid cyan bags, " +
                "4 dull olive bags.";
        List<String> tokens = Arrays.asList(line.split(" "));

        BagEntity entity1 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(0))
                        .t2(tokens.get(1)).build()).build();

        BagEntity entity2 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(5))
                        .t2(tokens.get(6)).build()).build();

        BagEntity entity3 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(9))
                        .t2(tokens.get(10)).build()).build();

        BagEntity entity4 = BagEntity.builder()
                .typeTuple(TypeTuple.builder()
                        .t1(tokens.get(13))
                        .t2(tokens.get(14)).build()).build();

        BagRule expected1 = BagRule.builder()
                .entity(entity1)
                .quantity(0)
                .children(ImmutableList.of(BagRule.builder()
                        .entity(entity2)
                        .quantity(1)
                        .children(ImmutableList.of())
                        .build(), BagRule.builder()
                        .entity(entity3)
                        .quantity(2)
                        .children(ImmutableList.of())
                        .build(), BagRule.builder()
                        .entity(entity4)
                        .quantity(4)
                        .children(ImmutableList.of())
                        .build()))
                .build();

        BagEntityRepository repository = new BagEntityRepository();
        BagRule actual1 = underTest.parseBagRule(tokens, 0, repository);
        assertThat(actual1).isEqualTo(expected1);

        repository = new BagEntityRepository();
        BagRule actual2 = underTest.readRuleFromLine(line, repository);
        assertThat(actual2).isEqualTo(expected1);

        BagRepository actual3 = underTest.readRulesFromLines(
                ImmutableList.of(line));
        assertThat(actual3.getEntityRepository()).isEqualTo(repository);

        BagRuleRepository expectedRules = new BagRuleRepository();
        expectedRules.addBagRule(expected1);
        assertThat(actual3.getRuleRepository()).isEqualTo(expectedRules);
    }
}