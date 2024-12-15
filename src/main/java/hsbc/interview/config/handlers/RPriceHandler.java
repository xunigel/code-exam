package hsbc.interview.config.handlers;

import hsbc.interview.config.ReferencePriceTable;
import hsbc.interview.enums.ConsolePrinter;
import hsbc.interview.enums.ProductTypes;
import hsbc.interview.enums.ReferencePrice;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class RPriceHandler implements Handler{
    @Override
    public Config process(String file) {

        try  {
            File cf = new File(file);
            FileReader fr = new FileReader(cf);
            LineNumberReader reader = new LineNumberReader(fr);
            //output header
            String l = reader.readLine();
            ConsolePrinter.hrule();
            ConsolePrinter.printf(l);

            ReferencePriceTable rpt = new ReferencePriceTable();
            parseRefPrices(rpt,reader);

            return rpt;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseRefPrices(ReferencePriceTable rpt, LineNumberReader reader) throws IOException {
        //parse reference price
        while(true) {
            ReferencePrice rprc = new ReferencePrice();
            String l = reader.readLine();
            if(l==null)
                return;
            String[] r = l.split(",");
            if (r.length == 5) {
                rprc.setInstrucment(r[0]);
                rprc.setpType(ProductTypes.fromName(r[1]));
                try {
                    Number n = NumberFormat.getInstance().parse(r[2]);
                    rprc.settPrice(n);
                } catch (ParseException e) {
                    rprc.settPrice(null);
                }
                try {
                    Number n = NumberFormat.getInstance().parse(r[3]);
                    rprc.setLtPrice(n);
                } catch (ParseException e) {
                    rprc.setLtPrice(null);
                }
                try {
                    Number n = NumberFormat.getInstance().parse(r[4]);
                    rprc.setcPrice(n);
                } catch (ParseException e) {
                    rprc.setcPrice(null);
                }
                rpt.addReferencePrice(rprc);
            }
        }
    }
}
