package hr.bornast.fantasy.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum EntityType {
    USER(5),
    PLAYER(6),
    COACH(7);

    private int value;
    private static Map map = new HashMap<>();

    private EntityType(int value) {
        this.value = value;
    }

    static {
        for (EntityType entityType : EntityType.values()) {
            map.put(entityType.value, entityType);
        }
    }

    public static EntityType valueOf(int entityType) {
        return (EntityType) map.get(entityType);
    }

    public int getValue() {
        return value;
    }
}
