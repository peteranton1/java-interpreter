package aoc.day04;

import java.util.ArrayList;
import java.util.List;

public class ParsePassportsMain {
    public static void main(String[] args) throws Exception {
        ParsePassportsMain parsePassportsMain = new ParsePassportsMain();
        parsePassportsMain.display("example-input.txt");
        parsePassportsMain.display("input.txt");
    }

    public void display(String inputFilename) throws Exception {
        int valids1 = parseInputFile(inputFilename);
        int valids2 = validatePassports(inputFilename);
        System.out.printf("%nvalids1: %d, valids2: %d, " +
                "file: %s%n", valids1, valids2, inputFilename);
    }

    public int parseInputFile(String inputFilename) throws Exception {
        PassportParser parser = new PassportParser();
        List<Passport> passports = parser.parseInputFile(inputFilename);
        return passports.stream()
                .map(Passport::mandatoryFieldsPresent)
                .reduce(0, Integer::sum);
    }

    public int validatePassports(String inputFilename) throws Exception {
        PassportParser parser = new PassportParser();
        List<Passport> passports = parser.parseInputFile(inputFilename);
        PassportValidator validator = new PassportValidator();
        List<Integer> validPassports = new ArrayList<>();
        for(Passport passport: passports) {
            List<Integer> results = validator.validFields(passport);
            int count = results.stream().reduce(0, Integer::sum);
            if(count == results.size()) {
                validPassports.add(1);
            }
        }
        return validPassports.stream()
                .reduce(0, Integer::sum);
    }
}
