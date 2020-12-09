package aoc.day07;

import java.util.HashMap;
import java.util.Map;

public class BagEntityRepository {
    private final Map<TypeTuple, BagEntity> bagEntityMap;

    public BagEntityRepository() {
        this.bagEntityMap = new HashMap<>();
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
