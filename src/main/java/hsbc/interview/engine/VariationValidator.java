package hsbc.interview.engine;

import hsbc.interview.api.SideOptions;
import hsbc.interview.config.RegulatoryMode;
import hsbc.interview.config.VariationConfig;
import hsbc.interview.pojo.Order;

public class VariationValidator {

    private VariationConfig _config;

    VariationValidator(VariationConfig config) {
        _config = config;
    }
    public void validate(Order o, VariationConfig config) {
        if(RegulatoryMode.ADVANTAGE_ONLY.equalsIgnoreCase(config.getValMode())){
            validateAdv(o,config);
        } else if(RegulatoryMode.DISADVANTAGE_ONLY.equalsIgnoreCase(config.getValMode())) {
            validateDadv(o,config);
        } else {
            validateBoth(o,config);
        }
    }
    String variation_pattern = "(%,.2f-%,.2f)/0.01 = %s";

    private void validateBoth(Order o, VariationConfig config) {
        float rp = config.getrPrice();
        float op = o.getPrice();
        float size = config.gettSize();
        float t = (rp-op)*100;
        String desc, alert, variation;
        if(Math.abs(t)>=size) {
            alert = "Yes";
            variation = String.format(variation_pattern,rp,op,""+t);
            if(t>=0)
                desc = t+" >= " +size+", block";
            else
                desc = "abs(" + t+") >= " +size+", block";
            o.setVariation(variation);
            o.setDescription(desc);
            o.setAlert(alert);
        } else {
            alert = "No";
            variation = String.format(variation_pattern,rp,op,""+t);
            if(t>=0)
                desc = t+" < " +size+", pass";
            else
                desc = "abs(" + t+") < " +size+", pass";
            o.setVariation(variation);
            o.setDescription(desc);
            o.setAlert(alert);
        }
    }

    private void validateDadv(Order o, VariationConfig config) {
        SideOptions os = o.getSide();
        float rp = config.getrPrice();
        float op = o.getPrice();
        float size = config.gettSize();
        float t = (rp-op)*100;
        if(SideOptions.Buy.name().equals(os.name())){
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0) {
                    alert = "No";
                    desc = t + " >= " + size + ", buy lower, pass";
                } else {
                    alert = "Yes";
                    desc = "abs(" + t + ") >= " + size + ", block";
                }
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            } else {
                alert = "No";
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0)
                    desc = t+" < " +size+", pass";
                else
                    desc = "abs(" + t+") < " +size+", pass";
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            }
        } else {
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0) {
                    alert = "Yes";
                    desc = t + " >= " + size + ", block";
                } else {
                    alert = "No";
                    desc = "abs(" + t + ") >= " + size + ", sell higher,  pass";
                }
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            } else {
                alert = "No";
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0)
                    desc = t+" < " +size+", pass";
                else
                    desc = "abs(" + t+") < " +size+", pass";
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            }
        }
    }

    private void validateAdv(Order o, VariationConfig config) {
        SideOptions os = o.getSide();
        float rp = config.getrPrice();
        float op = o.getPrice();
        float size = config.gettSize();
        float t = (rp-op)*100;
        if(SideOptions.Buy.name().equals(os.name())){
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0) {
                    alert = "Yes";
                    desc = t + " >= " + size + ", block";
                } else {
                    alert = "No";
                    desc = "abs(" + t + ") >= " + size + ", buy higher, pass";
                }
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            } else {
                alert = "No";
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0)
                    desc = t+" < " +size+", pass";
                else
                    desc = "abs(" + t+") < " +size+", pass";
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            }
        } else {
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0) {
                    alert = "No";
                    desc = t + " >= " + size + ", sell lower, pass";
                } else {
                    alert = "Yes";
                    desc = "abs(" + t + ") >= " + size + ", block";
                }
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            } else {
                alert = "No";
                variation = String.format(variation_pattern,rp,op,""+t);
                if(t>=0)
                    desc = t+" < " +size+", pass";
                else
                    desc = "abs(" + t+") < " +size+", pass";
                o.setVariation(variation);
                o.setDescription(desc);
                o.setAlert(alert);
            }
        }
    }
}
