package aoc.day07;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class BagQuestionerTest {

    @Test
    public void shouldAnswerQuestionExample() throws Exception {
        BagQuestioner questioner = new BagQuestioner();
        TypeTuple bagType = TypeTuple.builder()
                .t1("shiny")
                .t2("gold")
                .build();
        List<LevelEntity> actual = questioner.howManyBagColorsContainAtLeast(
                "example-input.txt", bagType
        );
        actual.sort(Comparator.comparing(a -> a.tup.t1 + ":" + a.tup.t2));
        System.out.printf("Shiny Gold Bag EXAMPLE in %n%s rules.%n", actual);
        int expected = 4;
        Assertions.assertThat(actual.size()).isEqualTo(expected);
    }

    @Test
    public void shouldAnswerQuestionMain() throws Exception {
        BagQuestioner questioner = new BagQuestioner();
        TypeTuple bagType = TypeTuple.builder()
                .t1("shiny")
                .t2("gold")
                .build();
        List<LevelEntity> actual = questioner.howManyBagColorsContainAtLeast(
                "input.txt", bagType
        );
        actual.sort(Comparator.comparing(a -> a.tup.t1 + ":" + a.tup.t2));
        System.out.printf("Shiny Gold Bag MAIN in %n%s rules.%n", actual);
        int expected = 24;
        Assertions.assertThat(actual.size()).isEqualTo(expected);
    }
}