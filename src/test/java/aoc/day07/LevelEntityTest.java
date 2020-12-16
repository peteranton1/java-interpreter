package aoc.day07;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LevelEntityTest {

    @Test
    public void shouldLevelEntityEquals() {
        TypeTuple tup1 = TypeTuple.builder()
                .t1("A")
                .t2("B")
                .build();
        TypeTuple tup2 = TypeTuple.builder()
                .t1("A")
                .t2("B")
                .build();
        LevelEntity ent1 = LevelEntity.builder()
                .tup(tup1)
                .lev(0)
                .build();
        LevelEntity ent2 = LevelEntity.builder()
                .tup(tup1)
                .lev(1)
                .build();
        LevelEntity ent3 = LevelEntity.builder()
                .tup(tup2)
                .lev(2)
                .build();
        assertThat(ent1).isEqualTo(ent2);
        assertThat(ent1).isEqualTo(ent3);
    }

}