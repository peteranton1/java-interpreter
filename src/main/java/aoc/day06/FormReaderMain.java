package aoc.day06;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FormReaderMain {

    private FormReader formReader;

    public static void main(String[] args) throws Exception {
        FormReaderMain main = new FormReaderMain(new FormReader());
        main.display();
    }

    public void display() throws Exception {
        List<String> lines = formReader.readInputFile("input.txt");
        List<IndForm> indForms = formReader.parseInput(lines);
        List<IndForm> grpForms = formReader.groupForms(indForms);
        int totalCounts1 = formReader.totalCounts1(grpForms);
        int totalCounts2 = formReader.totalCounts2(grpForms);
        System.out.printf("%nLines: %d%n" +
                "IndForms: %d%n" +
                "GrpForms: %d%n" +
                "Totals1: %d%n" +
                        "Totals2: %d%n",
                lines.size(),
                indForms.size(),
                grpForms.size(),
                totalCounts1,
                totalCounts2);
    }
}
