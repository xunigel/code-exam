package hsbc.interview.out;

import hsbc.interview.config.Orders;
import hsbc.interview.enums.ConsolePrinter;
import hsbc.interview.pojo.Order;

public class Output {
    public static void saveToCSV(Orders ods){
        ConsolePrinter.printf("Output to CSV file not impletmented.....");
    }
    public static void printToScreen(Orders ods){
        //print header
        ConsolePrinter.printf("No,Instrument,Side,Price,Alert,Variation,Description");
        boolean b = true;
        while(ods.size()>0 &&b){
            Order o = ods.pop();
            if(o!=null)
                ConsolePrinter.printf(o.toString());
            else
                b = false;
        }
    }
}
