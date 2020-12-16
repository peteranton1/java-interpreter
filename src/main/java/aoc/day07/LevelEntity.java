package aoc.day07;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

@Builder
@Getter
public class LevelEntity {
    TypeTuple tup;
    int lev;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LevelEntity)) return false;
        LevelEntity that = (LevelEntity) o;
        return Objects.equals(tup, that.tup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tup);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("tup", tup)
                .append("lev", lev)
                .toString();
    }
}
