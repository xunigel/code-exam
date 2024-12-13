package hsbc.interview.config.handlers;

import hsbc.interview.api.ConsolePrinter;
import hsbc.interview.config.TickTable;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TickTableHandler implements Handler{
    @Override
    public Config process(String file) {

        try  {
            File cf = new File(file);
            FileReader fr = new FileReader(cf);
            LineNumberReader reader = new LineNumberReader(fr);
            //output header of tick table
            String l = reader.readLine();
            ConsolePrinter.hrule();
            ConsolePrinter.printf(l);

            List<Number[]> res = new ArrayList<>();
            //parse tick table
            while(l!=null){
                l = reader.readLine();
                Number[] func = parseOneRow(l);
                res.add(func);
            }
            TickTable tt = new TickTable(res);
            return tt;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Number[] parseOneRow(String l) {
        String[] s = l.split(",");
        Number[] r = new Number[3];
        for (int i=0; i<3;i++) {
            String sn = s[i];
            if(sn==null ||sn.isEmpty()){
                r[i] = null;
            } else {
                try {
                    r[i] = NumberFormat.getInstance().parse(sn.trim());
                } catch (Exception e) {
                    r[i]=null;
                }
            }
        }
        return r;
    }
}
