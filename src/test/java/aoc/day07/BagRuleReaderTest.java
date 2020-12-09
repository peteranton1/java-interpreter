package aoc.day07;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BagRuleReaderTest {

    private BagRuleReader underTest;

    @Before
    public void setUp() {
        underTest = new BagRuleReader();
    }

    @Test
    public void shouldFindBagEntity() {
        List<String> tokens = ImmutableList.of(
                "posh", "olive", "bags", "contain",
                "2", "vibrant", "green", "bags", "."
        );
        BagEntityRepository repository = new BagEntityRepository();

        BagEntity actual1 = underTest
                    .findBagEntity(tokens,0, repository);
        BagEntity expected1 = repository.findEntity(TypeTuple.builder()
                .tuple1(tokens.get(0))
                .tuple2(tokens.get(1)).build());
        assertThat(actual1).isEqualTo(expected1);

        BagEntity actual2 = underTest
                .findBagEntity(tokens,5, repository);
        BagEntity expected2 = repository.findEntity(TypeTuple.builder()
                .tuple1(tokens.get(5))
                .tuple2(tokens.get(6)).build());
        assertThat(actual2).isEqualTo(expected2);
    }
}