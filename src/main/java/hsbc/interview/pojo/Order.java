package hsbc.interview.pojo;

import hsbc.interview.enums.SideOptions;

public class Order {
    public static final String CSV_SEPARATER =",";
    public static final String DOUBLE_QUOTE ="\"";
    private int _no;
    private String instrument;
    private SideOptions side;

    private float price;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    private String alert;

    public int get_no() {
        return _no;
    }

    public void set_no(int _no) {
        this._no = _no;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String variation;

    private String description;


    public SideOptions getSide() {
        return side;
    }

    public void setSide(SideOptions side) {
        this.side = side;
    }

    public String toString(){
        return _no+CSV_SEPARATER
                +DOUBLE_QUOTE+instrument+DOUBLE_QUOTE+CSV_SEPARATER
                +DOUBLE_QUOTE+side.name()+DOUBLE_QUOTE+CSV_SEPARATER
                +DOUBLE_QUOTE+price+DOUBLE_QUOTE+CSV_SEPARATER
                +DOUBLE_QUOTE+alert+DOUBLE_QUOTE+CSV_SEPARATER
                +DOUBLE_QUOTE+variation+DOUBLE_QUOTE+CSV_SEPARATER
                +DOUBLE_QUOTE+description+DOUBLE_QUOTE;
    }

    public int hashCode(){
        return toString().hashCode();
    }

    public boolean isEmpty() {
        return price<=0 || side ==null || instrument==null;
    }

    public boolean equals(Object o) {
        if (o instanceof Order){
            return o.toString().equals(this.toString());
        } else {
            return false;
        }
    }

}
