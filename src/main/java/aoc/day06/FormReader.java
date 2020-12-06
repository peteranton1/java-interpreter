package aoc.day06;

import aoc.resource.ResourceResolver;

import java.util.ArrayList;
import java.util.List;

public class FormReader {
    public List<String> readInputFile(String inputFile) throws Exception {
        return new ResourceResolver()
                .getResourceAsLines("aoc/day06/" + inputFile);
    }

    public int totalCounts1(List<IndForm> input) {
        int total = 0;
        for (IndForm form : input) {
            total += form.total1();
        }
        return total;
    }

    public int totalCounts2(List<IndForm> input) {
        int total = 0;
        for (IndForm form : input) {
            total += form.total2();
        }
        return total;
    }

    public List<IndForm> groupForms(List<IndForm> input) {
        List<IndForm> output = new ArrayList<>();
        int groupId = -1;
        IndForm groupForm = null;
        for (IndForm form : input) {
            if (form.getGroupId() != groupId) {
                groupId = form.getGroupId();
                groupForm = new IndForm(groupId, 0);
                output.add(groupForm);
            }
            if (groupForm == null) {
                throw new RuntimeException("Program Error");
            }
            groupForm.incrementRespondents();
            for (Question key : form.getFields().keySet()) {
                Integer curVal = groupForm.getField(key.getLetter());
                if(curVal == null) {
                    curVal = 0;
                }
                groupForm.setField(key.getLetter(), curVal + 1);
            }
        }
        return output;
    }

    public List<IndForm> parseInput(List<String> lines) {
        List<IndForm> output = new ArrayList<>();
        int groupId = 0;
        int lineNo = 0;
        for (String line : lines) {
            if (line != null) {
                lineNo++;
                if (line.trim().length() == 0) {
                    groupId++;
                } else {
                    output.add(createForm(groupId, lineNo, line.trim()));
                }
            }
        }
        return output;
    }

    public IndForm createForm(int groupId, int indId, String line) {
        IndForm output = new IndForm(groupId, indId);
        for (char ch : line.toCharArray()) {
            output.setField(String.valueOf(ch), 1);
        }
        return output;
    }
}
