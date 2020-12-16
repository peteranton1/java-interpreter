package aoc.day09;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class TermFinderTest {

    private static final List<BigDecimal> INPUT_LIST = ImmutableList.of(
            valueOf(35), valueOf(20), valueOf(15), valueOf(25), valueOf(47),
            valueOf(40), valueOf(62), valueOf(55), valueOf(65), valueOf(95),
            valueOf(102), valueOf(117), valueOf(150), valueOf(182), valueOf(127),
            valueOf(219), valueOf(299), valueOf(277), valueOf(309), valueOf(576)
    );

    private static final List<List<BigDecimal>> OUTPUT_LIST = ImmutableList.of(
            ImmutableList.of(valueOf(15), valueOf(25))
            , ImmutableList.of(valueOf(15), valueOf(47))
            , ImmutableList.of(valueOf(15), valueOf(40))
            , ImmutableList.of(valueOf(25), valueOf(40))
            , ImmutableList.of(valueOf(40), valueOf(55))
            , ImmutableList.of(valueOf(40), valueOf(62))
            , ImmutableList.of(valueOf(62), valueOf(55))
            , ImmutableList.of(valueOf(55), valueOf(95))
            , ImmutableList.of(valueOf(65), valueOf(117))
            , ImmutableList.of()
            , ImmutableList.of(valueOf(102), valueOf(117))
            , ImmutableList.of(valueOf(117), valueOf(182))
            , ImmutableList.of(valueOf(150), valueOf(127))
            , ImmutableList.of(valueOf(182), valueOf(127))
            , ImmutableList.of(valueOf(299), valueOf(277))
    );

    private TermFinder underTest;

    @Before
    public void setUp() {
        underTest = new TermFinder();
    }

    @Test
    public void shouldFindMinMaxSumWithMaxiFile() throws Exception {
        BigDecimal goal = valueOf(138879426);
        List<BigDecimal> input = new InputParser().parse("input.txt");
        List<BigDecimal> input2 = underTest.findSubListSummingTo(input, goal);
        BigDecimal actual = underTest.findMinMaxSum(input2);
        BigDecimal expected = valueOf(23761694);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindMinMaxSumWithMiniFile() throws Exception {
        BigDecimal goal = valueOf(127);
        List<BigDecimal> input = new InputParser().parse("example-input.txt");
        List<BigDecimal> input2 = underTest.findSubListSummingTo(input, goal);
        BigDecimal actual = underTest.findMinMaxSum(input2);
        BigDecimal expected = valueOf(15 + 47);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindListSummingToWithMaxiFile() throws Exception {
        BigDecimal goal = valueOf(138879426);
        List<BigDecimal> input = new InputParser().parse("input.txt");
        List<BigDecimal> actual = underTest.findSubListSummingTo(input, goal);
        List<BigDecimal> expected = ImmutableList.of(
                valueOf(6291980),
                valueOf(6237364),
                valueOf(5984187),
                valueOf(6615272),
                valueOf(6156670),
                valueOf(6315473),
                valueOf(6409847),
                valueOf(6542572),
                valueOf(7144670),
                valueOf(11945621),
                valueOf(8593950),
                valueOf(9606073),
                valueOf(7454040),
                valueOf(7950161),
                valueOf(8471423),
                valueOf(9382616),
                valueOf(17777507)
                );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindListSummingToWithMiniFile() throws Exception {
        BigDecimal goal = valueOf(127);
        List<BigDecimal> input = new InputParser().parse("example-input.txt");
        List<BigDecimal> actual = underTest.findSubListSummingTo(input, goal);
        List<BigDecimal> expected = ImmutableList.of(
                valueOf(15),valueOf(25),valueOf(47),valueOf(40));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindFirstWithoutTermsWithMaxiFile() throws Exception {
        int priors = 25;
        List<BigDecimal> input = new InputParser().parse("input.txt");
        List<List<BigDecimal>> actual = underTest.findAllWithoutTerms(input, priors);
        List<List<BigDecimal>> expected = ImmutableList.of(
                ImmutableList.of(valueOf(572),valueOf(138879426))
        );
        final int expectedSize = expected.size();
        assertThat(actual.size()).isEqualTo(expectedSize);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindFirstWithoutTermsWithMiniFile() throws Exception {
        int priors = 5;
        List<BigDecimal> input = new InputParser().parse("example-input.txt");
        List<List<BigDecimal>> actual = underTest.findAllWithoutTerms(input, priors);
        List<List<BigDecimal>> expected = ImmutableList.of(
                ImmutableList.of(valueOf(14),valueOf(127)));
        final int expectedSize = expected.size();
        assertThat(actual.size()).isEqualTo(expectedSize);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindTermsWithMaxiFile() throws Exception {
        int priors = 25;
        List<BigDecimal> input = new InputParser().parse("input.txt");
        List<List<BigDecimal>> actual = underTest.findTerms(input, priors);
        assertThat(actual.size()).isEqualTo(input.size()-priors);
    }

    @Test
    public void shouldFindTermsWithMiniFile() throws Exception {
        int priors = 5;
        List<BigDecimal> input = new InputParser().parse("example-input.txt");
        List<List<BigDecimal>> actual = underTest.findTerms(input, priors);
        assertThat(actual).isEqualTo(OUTPUT_LIST);
    }

    @Test
    public void shouldFindTermsMini() {
        int priors = 5;
        List<List<BigDecimal>> actual = underTest.findTerms(INPUT_LIST, priors);
        assertThat(actual).isEqualTo(OUTPUT_LIST);
    }

    @Test
    public void shouldFindTermWithOnePair() {
        int priors = 5;
        int sp = 5;
        List<BigDecimal> expected = ImmutableList.of(valueOf(15), valueOf(25));
        List<BigDecimal> actual = underTest.findTerm(INPUT_LIST, priors, sp);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindTermWithMultiple() {
        int priors = 5;
        int expectedNo = 0;
        for (int sp = priors; sp < INPUT_LIST.size(); sp++) {
            List<BigDecimal> expected = OUTPUT_LIST.get(expectedNo);
            List<BigDecimal> actual = underTest.findTerm(INPUT_LIST, priors, sp);
            assertThat(actual).isEqualTo(expected);
            expectedNo++;
        }
    }

}