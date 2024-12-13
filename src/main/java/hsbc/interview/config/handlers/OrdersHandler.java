package hsbc.interview.config.handlers;

import hsbc.interview.api.ConsolePrinter;
import hsbc.interview.api.SideOptions;
import hsbc.interview.config.Orders;
import hsbc.interview.pojo.Order;

import java.io.*;

public class OrdersHandler implements Handler{
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

            Orders res = new Orders();
            //parse tick table
            while(l!=null){
                l = reader.readLine();
                Order order = parseOneRow(l);
                res.add(order);
            }
            return res;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Order parseOneRow(String l) {
        String[] s = l.split(",");
        Order r = new Order();
        r.set_no(Integer.parseInt(s[0].trim()));
        r.setInstrument(s[1].trim());
        r.setSide(SideOptions.fromName(s[2].trim()));
        r.setPrice(Float.parseFloat(s[3].trim()));
        return r;
    }
}
