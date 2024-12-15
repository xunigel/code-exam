package hsbc.interview.config;

import hsbc.interview.config.handlers.Config;
import hsbc.interview.pojo.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Orders implements Config {
    private List<Order> _orders = new ArrayList<>();
    public void init(){
        _orders.clear();;
    }

    public int size(){
        return _orders.size();
    }

    /**
     * Assumption:
     *  the index field 'No' means the priority of the order.
     * @param o - an order to be validated of variation..
     */
    public synchronized void add(Order o) {
        if(o != null && !o.isEmpty()) {
            _orders.add( o);
        } else
            return;
    }
    public synchronized void addAll(Orders oss) {
        if(oss != null && oss.size()>0) {
            Order o = oss.pop();
            while(!o.isEmpty()){
                add(o);
                o=oss.pop();
            }
        } else
            return;
    }

    public synchronized void addAll(List<Order> oss) {
        if(oss != null && oss.size()>0) {
            for (Order o : oss) {
                add(o);
            }
        } else
            return;
    }

    public synchronized Order pop(){
        Order o = null;
        try {
            o = _orders.get(0);
            _orders.remove(o);
            return o;
        } catch (NoSuchElementException e) {
            return o;
        }
    }
}
