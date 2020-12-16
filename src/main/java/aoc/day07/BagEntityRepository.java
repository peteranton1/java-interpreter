package aoc.day07;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode
public class BagEntityRepository {
    private final Map<TypeTuple, BagEntity> bagEntityMap;

    public BagEntityRepository() {
        this.bagEntityMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("map", bagEntityMap)
                .toString();
    }

    public String toStringList() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Set<TypeTuple> entities = bagEntityMap.keySet();
            StringBuilder buf = new StringBuilder();
            for(TypeTuple tuple : entities) {
                buf.append(mapper.writeValueAsString(tuple));
                buf.append(System.lineSeparator());
            }
            return buf.toString();
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    public BagEntity findEntity(TypeTuple key) {
        if (key == null) {
            throw new RuntimeException(
                    "unable to get bag: key is null");
        }
        return bagEntityMap.computeIfAbsent(key,
                t -> BagEntity.builder()
                        .typeTuple(t)
                        .build());
    }
}
