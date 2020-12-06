package aoc.day5;

import aoc.resource.ResourceResolver;

import java.util.List;

public class SeatDecodeMain {
    private SeatDecoder decoder;

    public SeatDecodeMain(SeatDecoder decoder) {
        this.decoder = decoder;
    }

    public static void main(String[] args) throws Exception {
        SeatDecodeMain main = new SeatDecodeMain(new SeatDecoder());
        List<String> lines = main.getLines("input.txt");
        List<SeatNo> seatNos = main.getSeatNos(lines);
        SeatNo maxSeatNo = main.maxSeatNo(seatNos);
        SeatNo gapSeatNo = main.gapSeatNo(seatNos);
        System.out.printf("Total lines: %d%n" +
                "Total seats: %s%n" +
                "Max Seat No: %s (%d)%n" +
                        "Gap Seat No: %s (%d)%n",
                lines.size(),
                seatNos.size(),
                maxSeatNo,
                maxSeatNo.getSeatId(),
                gapSeatNo,
                gapSeatNo.getSeatId());
    }

    private SeatNo gapSeatNo(List<SeatNo> seatNos) {
        return decoder.findSeatNoGap(seatNos);
    }

    private SeatNo maxSeatNo(List<SeatNo> seatNos) {
        return decoder.maxSeatNo(seatNos);
    }

    private List<SeatNo> getSeatNos(List<String> lines) {
        return decoder.getSeatNos(lines);
    }

    private List<String> getLines(String inputFilename) throws Exception {
        return decoder.getLines(inputFilename);
    }
}
