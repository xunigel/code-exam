package hsbc.interview.in;

import hsbc.interview.config.*;
import hsbc.interview.config.handlers.*;
import hsbc.interview.enums.ConsolePrinter;
import hsbc.interview.enums.RegulatoryTypes;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class UserInput {
    public static VariationConfig input()throws ParseException {
        Scanner in = new Scanner(System.in);
        ConsolePrinter.hrule();
        ConsolePrinter.printf("Welcome to Variation Validator!");
        ConsolePrinter.printf("Please input file path to tick table(CSV):");
        String tickStr = in.nextLine();
        TickTableHandler tth = new TickTableHandler();
        Config tickConfig = tth.process(tickStr);
        ConsolePrinter.printf("Please input file path to reference prices(CSV):");
        String rpcs = in.nextLine();
        RPriceHandler prcHandler = new RPriceHandler();
        Config rprcConfig = prcHandler.process(rpcs);

        ConsolePrinter.printf("Please input calculation method(1 | 2 | 3):");
        ConsolePrinter.printf("1 - By Percentage;");
        ConsolePrinter.printf("2 - By Absolute Value;");
        ConsolePrinter.printf("3 - By Tick Size;");
        String vtype = in.nextLine();
        TickTypes tt = TickTypes.fromCode(Integer.parseInt(vtype));


        ConsolePrinter.printf("Please input regulatory type(A | D | B):");
        ConsolePrinter.printf("A - ADVANTAGE_ONLY;");
        ConsolePrinter.printf("D - DISADVANTAGE_ONLY;");
        ConsolePrinter.printf("B - BOTH;");
        String rType = in.nextLine();
        RegulatoryTypes rt = RegulatoryTypes.fromCode(rType);


        VariationConfig cfg = VariationConfigBuilder.build((TickTable) tickConfig,
                (ReferencePriceTable) rprcConfig, tt, rt);

        ConsolePrinter.printf("Now input file path to orders(CSV):");
        String odf = in.nextLine();
        OrdersHandler odHandler = new OrdersHandler();
        Config orderConfig = odHandler.process(odf);
        cfg.setOrders((Orders) orderConfig);

        ConsolePrinter.printf("Please setup a variation limit number:");
        ConsolePrinter.printf("For Percentage: Input 0 ~ 100, two decimal number;");
        ConsolePrinter.printf("For Absolute Value: Input two decimal number;");
        ConsolePrinter.printf("For Tick Size: Input a number.");
        String limit = in.nextLine();
        cfg.setLimit(NumberFormat.getInstance().parse(limit));
        return cfg;
    }
}
