package hr.bornast.fantasy.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum Card {
    YELLOW(18),
    RED(19);

    private int value;
    private static Map map = new HashMap<>();

    private Card(int value) {
        this.value = value;
    }

    static {
        for (Card card : Card.values()) {
            map.put(card.value, card);
        }
    }

    public static Card valueOf(int card) {
        return (Card) map.get(card);
    }

    public int getValue() {
        return value;
    }
}
