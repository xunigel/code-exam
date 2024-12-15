package hsbc.interview;

import hsbc.interview.config.*;
import hsbc.interview.engine.VariationValidator;
import hsbc.interview.enums.ConsolePrinter;
import hsbc.interview.out.Output;
import hsbc.interview.in.UserInput;
import hsbc.interview.pojo.Order;

import java.io.Console;
import java.text.ParseException;

/**
 * Hello HSBC!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ConsolePrinter.printf( "Hello HSBC!" );
        try {
            VariationConfig config = UserInput.input();
            VariationValidator vv = new VariationValidator(config);
            Orders ods = config.getOrders();
            Orders res = new Orders();
            while(ods.size()>0){
                Order o = ods.pop();
                vv.validate(o, config);
                res.add(o);
            }
            ConsolePrinter.hrule();
            ConsolePrinter.printf("START PRINTING RESULT TO CONSOLE....");
            Output.printToScreen(res);
            ConsolePrinter.printf("END OF CONSOLE SCREEN PRINTING....");
            Output.saveToCSV(res);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
