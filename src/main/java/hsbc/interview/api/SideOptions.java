package hsbc.interview.api;

import java.util.Arrays;

public enum SideOptions {
    Buy("1"),
    Sell("2");

    private String code;

    SideOptions(String code) {
        this.code = code;
    }

    public static SideOptions fromCode(final String code) {
        return Arrays.stream(SideOptions.values())
                .filter(item -> item.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static SideOptions fromName(final String name) {
        return Arrays.stream(SideOptions.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
