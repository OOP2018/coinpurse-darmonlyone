package coinpurse;

import java.util.ArrayList;
import java.util.List;

public abstract class MoneyFactory{

    private static MoneyFactory moneyFactory = null;
    private List<Valuable> moneyFactoryList;

    private MoneyFactory(){
        moneyFactoryList = new ArrayList<Valuable>();
    }

    public static MoneyFactory getInstance(){
        return moneyFactory;
    }

    public abstract Valuable createMoney(double value);

    public Valuable createMoney(String value) throws IllegalAccessException {
            return createMoney(Double.parseDouble(value));
    }
}
