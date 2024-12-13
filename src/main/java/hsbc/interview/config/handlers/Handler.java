package hsbc.interview.config.handlers;

import hsbc.interview.config.TickTable;
import hsbc.interview.config.VariationConfig;

public interface Handler {
    Config process(String file);
}
