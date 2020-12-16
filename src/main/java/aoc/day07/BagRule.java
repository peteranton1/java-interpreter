package aoc.day07;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class BagRule {
    BagEntity entity;
    int quantity;
    List<BagRule> children;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("e", entity)
                .append("q", quantity)
                .append("c", children)
                .toString();
    }

    public int isBagPresent(BagEntity entity) {
        for (BagRule child : children) {
            if(child.entity.equals(entity)){
                return 1;
            }
        }
        return 0;
    }

    public boolean hasChildOf(LevelEntity level) {
        for(BagRule child: children){
            if(level.tup.equals(child.entity.typeTuple)){
                return true;
            }
        }
        return false;
    }
}
