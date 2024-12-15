package hsbc.interview.enums;

import java.util.Arrays;

public enum InstrumentTypes {
    HSIZ4("HSIZ4"),
    KS200400F5_KS("KS200400F5.KS"),
    VOD_L("VOD.L");

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    InstrumentTypes(final String code) {
        this.code = code;
    }

    private String code;
    public static InstrumentTypes fromCode(final String code) {
        return Arrays.stream(InstrumentTypes.values())
                .filter(item -> item.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static InstrumentTypes fromName(final String name) {
        return Arrays.stream(InstrumentTypes.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
