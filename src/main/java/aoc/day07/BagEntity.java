package aoc.day07;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Builder
@Getter
@EqualsAndHashCode
public class BagEntity {
    TypeTuple typeTuple;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("type", typeTuple)
                .toString();
    }
}
