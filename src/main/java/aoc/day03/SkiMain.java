package aoc.day03;

import java.util.ArrayList;
import java.util.List;

public class SkiMain {
    public static void main(String[] args) throws Exception {
        Tabogan tabogan = new Tabogan("input.txt");
        List<Long> totals = new ArrayList<>();
        totals.add((long) tabogan.run(1, 1));
        totals.add((long) tabogan.run(3, 1));
        totals.add((long) tabogan.run(5, 1));
        totals.add((long) tabogan.run(7, 1));
        totals.add((long) tabogan.run(1, 2));
        System.out.printf("Totals %s%n", totals);
        long total = totals.stream()
                .reduce(1L, (a, b) -> a * b);
        System.out.printf("Total %d%n", total);
    }
}
