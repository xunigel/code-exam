package hsbc.interview.api;

/**
 * Assumption:
 *  A reference Price should be passed in Variation Validator at runtime for calculation.
 *
 */
public class ReferencePrice {
    public String getInstrucment() {
        return instrucment;
    }

    public void setInstrucment(String instrucment) {
        this.instrucment = instrucment;
    }

    public ProductTypes getpType() {
        return pType;
    }

    public void setpType(ProductTypes pType) {
        this.pType = pType;
    }

    public float gettPrice() {
        return tPrice.floatValue();
    }

    public void settPrice(Number tPrice) {
        this.tPrice = tPrice;
    }

    public float getLtPrice() {
        return ltPrice.floatValue();
    }

    public void setLtPrice(Number ltPrice) {
        this.ltPrice = ltPrice;
    }

    public float getcPrice() {
        return cPrice.floatValue();
    }

    public void setcPrice(Number cPrice) {
        this.cPrice = cPrice;
    }

    private String instrucment;

    private ProductTypes pType;
    //Theo Price
    private  Number tPrice;
    //Last Trade Price
    private  Number ltPrice;
    //Close Price
    private  Number cPrice;

    public Number getReferencePrice() {
        //null value means target price is unavailable.
        if(ltPrice!=null) {
            return ltPrice;
        } else if(cPrice!=null){
            return cPrice;
        } else if(tPrice!=null) {
            return tPrice;
        } else
            //if no price candidate is found, will block trade.
            return null;
    }
}
