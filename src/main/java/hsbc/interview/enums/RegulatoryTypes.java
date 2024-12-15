package hsbc.interview.enums;

import java.util.Arrays;

public enum RegulatoryTypes {
    ADVANTAGE_ONLY("A"),
    DISADVANTAGE_ONLY("D"),
    BOTH("B");

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    RegulatoryTypes(String code){
        this.code = code;
    }
    public static RegulatoryTypes fromCode(final String code) {
        return Arrays.stream(RegulatoryTypes.values())
                .filter(item -> item.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static RegulatoryTypes fromName(final String name) {
        return Arrays.stream(RegulatoryTypes.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
