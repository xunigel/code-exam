package hsbc.interview.config;

import hsbc.interview.config.handlers.Config;
import hsbc.interview.enums.InstrumentTypes;
import hsbc.interview.enums.ProductTypes;
import hsbc.interview.enums.RegulatoryTypes;

public class VariationConfig implements Config {
    public TickTable getTickTable() {
        return tickTable;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Orders orders;

    public void setTickTable(TickTable tickTable) {
        this.tickTable = tickTable;
    }

    //determined tick size
    TickTable tickTable;

    public Number getLimit() {
        return limit;
    }

    public void setLimit(Number limit) {
        this.limit = limit;
    }

    Number limit;

    public int getValMode() {
        return valMode;
    }

    public void setValMode(int valMode) {
        this.valMode = valMode;
    }

    /**
     * variation validation mode:
     *      1 - by percentage
     *      2 - by absolute value
     *      3 - by tick size
     */

    int valMode;

    public RegulatoryTypes getRegulatoryMode() {
        return regulatoryMode;
    }

    public void setRegulatoryMode(RegulatoryTypes regulatoryMode) {
        this.regulatoryMode = regulatoryMode;
    }

    //  avantage_only,
    //  disavantage_only
    //  or default as both
    RegulatoryTypes regulatoryMode;
    TickTypes tType;

    public TickTypes gettType() {
        return tType;
    }

    public void settType(TickTypes tType) {
        this.tType = tType;
    }

    public ReferencePriceTable getRpTable() {
        return rpTable;
    }

    public void setRpTable(ReferencePriceTable rpTable) {
        this.rpTable = rpTable;
    }

    ReferencePriceTable rpTable;
    public VariationConfig(TickTable ttbl, ReferencePriceTable rpt, TickTypes tt, RegulatoryTypes rType) {
        this.regulatoryMode = rType;
        this.tickTable = ttbl;
        this.tType = tt;
        this.rpTable = rpt;
    }
    public Number getReferencePriceByProduct(ProductTypes t) {
       return rpTable.getByProductType(t);
    }
    public Number getReferencePriceByInstrument(InstrumentTypes itp) {
        return rpTable.getByInstrumentType(itp);
    }

    public Number getTickSize(Number p){
        return tickTable.determineTickSize(p);
    }
}
