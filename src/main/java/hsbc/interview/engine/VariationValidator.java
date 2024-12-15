package hsbc.interview.engine;

import hsbc.interview.config.TickTypes;
import hsbc.interview.enums.InstrumentTypes;
import hsbc.interview.enums.RegulatoryTypes;
import hsbc.interview.enums.SideOptions;
import hsbc.interview.config.VariationConfig;
import hsbc.interview.pojo.Order;

public class VariationValidator {

    private VariationConfig _config;

    public VariationValidator(VariationConfig config) {
        _config = config;
    }
    public void validate(Order o, VariationConfig config) {
        if(RegulatoryTypes.ADVANTAGE_ONLY.equals(config.getRegulatoryMode().name())){
            validateAdv(o,config);
        } else if(RegulatoryTypes.DISADVANTAGE_ONLY.equals(config.getRegulatoryMode().name())) {
            validateDadv(o,config);
        } else {
            validateBoth(o,config);
        }
    }

    private void validateBoth(Order o, VariationConfig config) {
        InstrumentTypes pt = InstrumentTypes.fromCode(o.getInstrument());
        Number nrp = config.getReferencePriceByInstrument(pt);
        if(nrp==null){
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Reference Price Unavailable, Block.");
            return;
        }
        float rp = nrp.floatValue();
        float op = o.getPrice();
        TickTypes ttp = config.gettType();
        Number sizen = config.getTickSize(nrp);
        if(sizen==null){
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Tick Size is Invalid, Block.");
            return;
        }
        float size = sizen.floatValue();
        float t ;
        t = getVariationValue(rp, op, ttp, size);
        float limit = config.getLimit().floatValue();
        String desc, alert, variation;
        if(Math.abs(t)>=limit) {
            alert = "Yes";
            variation = determineVariationResult(ttp, rp,op,size,""+t);//String.format(variation_pattern,rp,op,""+t);
            if(t>=0)
                desc = t+" >= " +limit+", block";
            else
                desc = "abs(" + t+") >= " +limit+", block";
            o.setVariation(variation);
            o.setDescription(desc);
            o.setAlert(alert);
        } else {
            alert = "No";
            variation = determineVariationResult(ttp,rp,op,size,""+t);
            if(t>=0)
                desc = t+" < " +limit+", pass";
            else
                desc = "abs(" + t+") < " +limit+", pass";
            o.setVariation(variation);
            o.setDescription(desc);
            o.setAlert(alert);
        }
    }

    String var_pattern_ts = "(%,.2f-%,.2f)/%,.2f = %s";
    String var_pattern_percntg = "100*(%,.2f-%,.2f)/%,.2f = %s";
    String var_pattern_abs = "(%,.2f-%,.2f) = %s";
    private String determineVariationResult(TickTypes ttp, float rp, float op, float size,String t) {
        if(ttp.name().equals(TickTypes.Percentage.name())) {
            return String.format(var_pattern_percntg,rp,op,rp,t);
        } else if(ttp.name().equals(TickTypes.Abs.name())){
            return String.format(var_pattern_abs,rp,op,t);
        } else if(ttp.name().equals(TickTypes.Size.name())){
            return String.format(var_pattern_ts,rp,op,size,t);
        } else
            return "Unsupported Variation Type: "+ttp.name();
    }

    private static float getVariationValue(float rp, float op, TickTypes ttp, float size) {
        float t;
        if(TickTypes.Percentage.equals(ttp)) {
            t = 100*(rp - op)/ op;
        } else if(TickTypes.Abs.equals(ttp)){
            t= rp - op;
        } else {
            t = (rp - op)/ size;
        }
        return t;
    }

    private void validateDadv(Order o, VariationConfig config) {
        SideOptions os = o.getSide();
        Number rpn = config.getReferencePriceByInstrument(InstrumentTypes.fromCode(o.getInstrument()));
        if(rpn == null) {
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Reference Price Unavailable, Block.");
            return;
        }
        float op = o.getPrice();
        float rp = rpn.floatValue();
        Number sizen = config.getTickSize(rpn);
        if(sizen==null){
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Tick Size is Invalid, Block.");
            return;
        }
        float size = sizen.floatValue();
        TickTypes ttp = config.gettType();
        float t = getVariationValue(rp,op,ttp,size);
        if(SideOptions.Buy.name().equals(os.name())){
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
        Number rpn = config.getReferencePriceByInstrument(InstrumentTypes.fromCode(o.getInstrument()));
        if(rpn == null) {
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Reference Price Unavailable, Block.");
            return;
        }
        Number sizen = config.getTickSize(rpn);
        if(sizen==null){
            o.setAlert("Yes");
            o.setVariation("N/A");
            o.setDescription("Tick Size is Invalid, Block.");
            return;
        }
        float op = o.getPrice();
        float rp = rpn.floatValue();
        float size = sizen.floatValue();
        //float size = config.gettSize();
        TickTypes ttp = config.gettType();
        float t = getVariationValue(rp,op,ttp,size);
        if(SideOptions.Buy.name().equals(os.name())){
            String desc, alert, variation;
            if(Math.abs(t)>=size) {
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
                variation =  determineVariationResult(ttp,rp,op,size,""+t);
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
