package hsbc.interview.config;

import java.util.Arrays;

public enum TickTypes {
    Percentage(1),
    Abs(2),
    Size(3);

    private int code;

    TickTypes(int c) {
        code = c;
    }

    public static TickTypes fromCode(final int code) {
        return Arrays.stream(TickTypes.values())
                .filter(item -> item.code == code)
                .findAny()
                .orElse(null);
    }

    public static TickTypes fromName(final String name) {
        return Arrays.stream(TickTypes.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
