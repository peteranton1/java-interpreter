package aoc.day05;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SeatNo {
    int row;
    int col;
    public int getSeatId() {
        return row * 8 + col;
    }
}
