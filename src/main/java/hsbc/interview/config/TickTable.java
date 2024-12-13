package hsbc.interview.config;

import hsbc.interview.config.handlers.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TickTable implements Config {
    private List<Number[]> tTable = new ArrayList<Number[]>();
    public TickTable(){
        tTable.clear();
    }

    public TickTable(List<Number[]> value) {
        if(validateTickTable(value))
            this.tTable = value;
        else
            tTable = new ArrayList<Number[]>();
    }

    public void add(Number[] r){
        if(validateSingle(r))
            tTable.add(r);
        else
            return;
    }

    private boolean validateSingle(Number[] r) {
        return r.length==3 && r[0]!=null && r[2]!=null;
    }

    public void resetAll(List<Number[]> value){
        tTable.clear();
        if(validateTickTable(value))
            this.tTable = value;
    }
    
    private boolean validateTickTable(List<Number[]> value) {
        boolean r = false;
        r = r && value!=null && value.size()>0;
        for (Number[] item:value) {
            r = r && item.length==3 && item[0]!=null && item[2]!=null;
        }
        return r;
    }

    public Number determineTickSize(Number price) {
        float p = price.floatValue();
        for(Number[] i: tTable){
            if (i[1]!=null && p>=i[0].floatValue() && p<= i[1].floatValue()){
                return i[2];
            } else if (i[1]==null && p>i[0].floatValue()){
                return i[2];
            } else
                continue;
        }
        return null;
    }
    
}
