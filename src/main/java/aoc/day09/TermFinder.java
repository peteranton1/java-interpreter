package aoc.day09;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TermFinder {

    public BigDecimal findMinMaxSum(List<BigDecimal> inputList) {
        BigDecimal min = BigDecimal.valueOf(9999999999999999L);
        BigDecimal max = BigDecimal.valueOf(0L);
        for (int i = 0; i < inputList.size(); i++) {
            BigDecimal temp = inputList.get(i);
            min = temp.min(min);
            max = temp.max(max);
        }
        return min.add(max);
    }

    public List<BigDecimal> findSubListSummingTo(
            List<BigDecimal> inputList,
            BigDecimal goal) {
        for (int i = 0; i < inputList.size(); i++) {
            List<BigDecimal> items = sumsToList(inputList, i, goal);
            if (items.size() > 1) {
                return items;
            }
        }
        return ImmutableList.of();
    }

    public List<BigDecimal> sumsToList(List<BigDecimal> inputList, int sp, BigDecimal goal) {
        List<BigDecimal> contiguous = new ArrayList<>();
        long goalLong = goal.longValue();
        long sum = 0;
        int i = sp;
        while (sum < goalLong) {
            BigDecimal value = inputList.get(i);
            sum += value.longValue();
            i++;
            if (sum <= goalLong) {
                contiguous.add(value);
                if (sum == goalLong) {
                    return contiguous;
                }
            }
        }
        return ImmutableList.of();
    }

    public List<List<BigDecimal>> findAllWithoutTerms(List<BigDecimal> inputList, int priors) {
        List<List<BigDecimal>> terms = findTerms(inputList, priors);
        List<List<BigDecimal>> output = new ArrayList<>();
        for (int i = 0; i < terms.size(); i++) {
            BigDecimal inputValue = inputList.get(priors + i);
            if (terms.get(i).isEmpty()) {
                output.add(ImmutableList.of(
                        BigDecimal.valueOf(i + priors)
                        , inputValue
                ));
            }
        }
        return output;
    }

    public List<List<BigDecimal>> findTerms(List<BigDecimal> inputList, int priors) {
        if (priors >= inputList.size()) {
            throw new RuntimeException(format("Error with inputList: \n" +
                    "priors: %s", priors));
        }
        List<List<BigDecimal>> output = new ArrayList<>();
        for (int sp = priors; sp < inputList.size(); sp++) {
            List<BigDecimal> term = findTerm(inputList, priors, sp);
            output.add(term);
        }
        return output;
    }

    public List<BigDecimal> findTerm(List<BigDecimal> input, int priors, int sp) {
        if (sp > input.size() || priors >= input.size()) {
            throw new RuntimeException(format("Error with input: \n" +
                            "priors: %s, sp:%s, \ninput: %s",
                    priors, sp, input));
        }
        BigDecimal goal = input.get(sp);
        return findTermsFromPriors(input, priors, sp, goal);
    }

    public List<BigDecimal> findTermsFromPriors(
            List<BigDecimal> input, int priors, int sp, BigDecimal goal) {
        for (int outer = sp - priors; outer < sp - 1; outer++) {
            for (int inner = sp - priors + 1; inner < sp; inner++) {
                BigDecimal v1 = input.get(outer);
                BigDecimal v2 = input.get(inner);
                if (outer != inner && goal.equals(v1.add(v2))) {
                    return ImmutableList.of(v1, v2);
                }
            }
        }
        return ImmutableList.of();
    }
}
