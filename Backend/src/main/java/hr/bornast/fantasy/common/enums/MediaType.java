package hr.bornast.fantasy.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum MediaType {
    IMAGE(8),
    VIDEO(9);

    private int value;
    private static Map map = new HashMap<>();

    private MediaType(int value) {
        this.value = value;
    }

    static {
        for (MediaType mediaType : MediaType.values()) {
            map.put(mediaType.value, mediaType);
        }
    }

    public static MediaType valueOf(int mediaType) {
        return (MediaType) map.get(mediaType);
    }

    public int getValue() {
        return value;
    }
}
