package aoc.day5;

import aoc.resource.ResourceResolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SeatDecoder {

    public List<String> getLines(String inputFilename) throws Exception {
        return new ResourceResolver()
                .getResourceAsLines(
                        "aoc/day05/" + inputFilename);
    }

    public List<SeatNo> getSeatNos(List<String> lines) {
        return lines.stream()
                .map(this::decode)
                .collect(toList());
    }

    public SeatNo maxSeatNo(List<SeatNo> seatNos) {
        return seatNos.stream()
                .max(comparing(SeatNo::getSeatId))
                .orElse(null);
    }

    public SeatNo findSeatNoGap(List<SeatNo> seatNos) {
        seatNos.sort(comparing(SeatNo::getSeatId));
        int prevSeatId = seatNos.get(0).getSeatId();
        for(SeatNo seatNo: seatNos) {
            if(seatNo.getSeatId() > prevSeatId+1) {
                int row = seatNo.getRow();
                int col = seatNo.getCol();
                if(col==0) {
                    row -= 1;
                    col = 7;
                } else {
                    col --;
                }
                return new SeatNo(row,col);
            }
            prevSeatId=seatNo.getSeatId();
        }
        return null;
    }

    public SeatNo decode(String input) {
        if (input == null || input.length() < "BFFFBBFRRR".length()) {
            throw new RuntimeException("Illegal seat number: " + input);
        }
        int row = decodeRow(input.substring(0, 7));
        int col = decodeCol(input.substring(7, 10));
        return new SeatNo(row, col);
    }

    private int decodeRow(String value) {
        char[] codes = {'F', 'B'};
        return parseBinaryString(value, codes);
    }

    private int decodeCol(String value) {
        char[] codes = {'L', 'R'};
        return parseBinaryString(value, codes);
    }

    private int parseBinaryString(String value, char[] codes) {
        int result = 0;
        int factor = 1;
        char[] reversed = charsReversed(value);
        for (char ch : reversed) {
            if (ch == codes[1]) {
                result = result + factor;
            }
            factor = factor * 2;
        }
        return result;
    }

    private char[] charsReversed(String value) {
        char[] chars = value.toCharArray();
        char[] reversed = value.toCharArray();
        for(int i=0;i<chars.length;i++) {
            reversed[reversed.length-1-i] = chars[i];
        }
        return reversed;
    }
}
