package aoc.day07;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Builder
@Getter
public class BagRepository {
    BagEntityRepository entityRepository;
    BagRuleRepository ruleRepository;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("entity", entityRepository)
                .append("rule", ruleRepository)
                .toString();
    }
}
