package hsbc.interview.api;

import java.util.Arrays;

public enum ProductTypes {
    Future("3"),
    Option("4"),
    Stock("5");

    private String code;
    ProductTypes(final String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    public static ProductTypes fromCode(final String code) {
        return Arrays.stream(ProductTypes.values())
                .filter(item -> item.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static ProductTypes fromName(final String name) {
        return Arrays.stream(ProductTypes.values())
                .filter(item -> item.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
