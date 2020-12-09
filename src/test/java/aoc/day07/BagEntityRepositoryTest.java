package aoc.day07;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BagEntityRepositoryTest {

    private BagEntityRepository underTest;

    @Before
    public void setUp() {
        underTest = new BagEntityRepository();
    }

    @Test
    public void shouldPutAndFindBagEntity() {
        TypeTuple typeTuple = TypeTuple.builder()
                .tuple1("A")
                .tuple2("B").build();
        BagEntity expected = underTest.findEntity(typeTuple);
        BagEntity actual = underTest.findEntity(typeTuple);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}