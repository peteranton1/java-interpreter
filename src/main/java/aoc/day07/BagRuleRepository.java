package aoc.day07;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
public class BagRuleRepository {
    private final List<BagRule> bagRules;

    public BagRuleRepository() {
        this.bagRules = new LinkedList<>();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("rules", bagRules)
                .toString();
    }

    public String toStringList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StringBuilder buf = new StringBuilder();
            for(BagRule bagRule: bagRules) {
                buf.append(mapper.writeValueAsString(bagRule));
                buf.append(System.lineSeparator());
            }
            return buf.toString();
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    public void addBagRule(BagRule bagRule) {
        bagRules.add(bagRule);
    }

    public List<BagRule> getBagRules() {
        return bagRules;
    }
}
