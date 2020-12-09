package aoc.day07;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class BagRule {
    BagEntity entity;
    int quantity;
    Set<BagRule> children;
}
