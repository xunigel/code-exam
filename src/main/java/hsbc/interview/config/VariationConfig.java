package hsbc.interview.config;

import hsbc.interview.config.handlers.Config;

public class VariationConfig implements Config {
    //determined tick size
    float tSize;

    public float gettSize() {
        return tSize;
    }

    public void settSize(float tSize) {
        this.tSize = tSize;
    }

    public float getrPrice() {
        return rPrice;
    }

    public void setrPrice(float rPrice) {
        this.rPrice = rPrice;
    }

    public String getValMode() {
        return valMode;
    }

    public void setValMode(String valMode) {
        this.valMode = valMode;
    }

    //determined reference price
    float rPrice;

    //variation validateion mode:
    //  avantage_only,
    //  disavantage_only
    //  or default as both
    String valMode;
    VariationConfig(float tSize, float rPrice) {
        this.tSize = tSize;
        this.rPrice = rPrice;
        this.valMode = RegulatoryMode.BOTH;
    }
    VariationConfig(float tSize, float rPrice, String valMode) {
        this.tSize = tSize;
        this.rPrice = rPrice;
        this.valMode = valMode;
    }
}
