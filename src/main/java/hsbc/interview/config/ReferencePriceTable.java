package hsbc.interview.config;

import hsbc.interview.enums.InstrumentTypes;
import hsbc.interview.enums.ProductTypes;
import hsbc.interview.enums.ReferencePrice;
import hsbc.interview.config.handlers.Config;

import java.util.HashMap;
import java.util.Map;

public class ReferencePriceTable implements Config {
    Map<ProductTypes, Number> rpriceByProductType = new HashMap<>();
    Map<InstrumentTypes, Number> rpriceByInstType = new HashMap<>();

    public void addReferencePrice(ReferencePrice rpc) {
        rpriceByProductType.put(rpc.getpType(), rpc.getReferencePrice());
        rpriceByInstType.put(rpc.getInstrucment(), rpc.getReferencePrice());
    }

    public Number getByProductType(ProductTypes pt) {
        return rpriceByProductType.get(pt);
    }

    public Number getByInstrumentType(InstrumentTypes itp) {
        return rpriceByInstType.get(itp);
    }
}
