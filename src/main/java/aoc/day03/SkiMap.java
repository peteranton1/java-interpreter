package aoc.day03;

import aoc.resource.ResourceResolver;

import java.util.List;

import static java.util.Comparator.naturalOrder;

public class SkiMap {
    private int curRow;
    private int curCol;
    private int maxRow;
    private int maxCol;
    private char[][] mountainMap;

    public SkiMap(String inputFile) throws Exception {
        loadInputFile(inputFile);
    }

    private void loadInputFile(String inputFile) throws Exception {
        List<String> lines = new ResourceResolver()
                .getResourceAsLines(
                        "aoc/day03/" + inputFile);
        this.curRow = 0;
        this.curCol = 0;
        this.maxCol = lines.stream()
                .map(String::length)
                .max(naturalOrder())
                .orElse(0);
        this.maxRow = lines.size();
        this.mountainMap = new char[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            String line = lines.get(row);
            for (int col = 0; col < maxCol; col++) {
                char[] colChars = line.toCharArray();
                mountainMap[row][col] = colChars[col];
            }
        }
        System.out.printf("Loaded: %s, rows: %d, cols: %d%n",
                inputFile, maxRow, maxCol);
    }

    public int move(int right, int down) {
        curRow = curRow + down;
        if (curRow >= maxRow) {
            return 0;
        }
        curCol = curCol + right;
        if (curCol >= maxCol) {
            curCol = curCol - maxCol;
        }
        char foundChar = mountainMap[curRow][curCol];
        return (foundChar == '#' ? 1 : 0);
    }

    public boolean isEof() {
        return (curRow > maxRow);
    }

    public void reset() {
        curRow = 0;
        curCol = 0;
    }
}
