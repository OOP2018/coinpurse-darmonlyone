package coinpurse;

/**
 * Factory class of making money for make separate Currency
 * @author Manusporn Fukkham
 */
public abstract class MoneyFactory{
    /** singleton instance of MoneyFactory. */
    private static MoneyFactory moneyFactory = null;

    /**
     * get an instance of MoneyFactory. This method returns an
     * object of a subclass (such as ThaiMoneyFactor).
     * @return Money Factory to use
     */
    public static MoneyFactory getInstance(){
        return moneyFactory;
    }

    /**
     * create new money object in the local currency.
     * If the value is not a valid currency amount, then throw
     * IllegalArgumentException.
     * @param value amount of money to create
     */
    public abstract Valuable createMoney(double value);

    /**
     *  parse the String as a double and call the other createMoney method
     *  if String value isn't a number then throw IllegalArgumentException
     * @param value
     * @return
     */
    public Valuable createMoney(String value) {
        try {
            return createMoney(Double.parseDouble(value));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Static method to a "set" the MoneyFactory object that is used.
     * @param f MoneyFactory to set
     */
    public static void setFactory(MoneyFactory f){
            moneyFactory = f;
    }
}
