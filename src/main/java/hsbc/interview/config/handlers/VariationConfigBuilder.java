package hsbc.interview.config.handlers;

import hsbc.interview.config.ReferencePriceTable;
import hsbc.interview.config.TickTable;
import hsbc.interview.config.TickTypes;
import hsbc.interview.config.VariationConfig;
import hsbc.interview.enums.RegulatoryTypes;

public class VariationConfigBuilder {
    public static VariationConfig build(TickTable t, ReferencePriceTable rpt, TickTypes tt, RegulatoryTypes rType){
        return new VariationConfig(t, rpt, tt, rType);
    }
}
