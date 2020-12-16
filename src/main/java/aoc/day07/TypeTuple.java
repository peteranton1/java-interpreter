package aoc.day07;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringStyle;

@Builder
@Getter
@EqualsAndHashCode
public class TypeTuple {
    String t1;
    String t2;

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder
                .ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("t1", t1)
                .append("t2", t2)
                .toString();
    }
}
