package coinpurse;

/**
 * MoneyFactory class for Thai Currency
 * @author Manusporn Fukkham
 */
public class ThaiMoneyFactory extends MoneyFactory {
    /** Thai Currency */
    private final String CURRENCY = "Baht";

    /**
     * Constructor of ThaiMoneyFactory
     */
    public ThaiMoneyFactory(){
        BankNote.setNextSerialNumber(100000);
        Money.setCountryCurrency(CURRENCY);
    }
    /**
     * create new money object in the local currency.
     * If the value is not a valid currency amount, then throw
     * IllegalArgumentException.
     * @param value amount of money to create
     * @return Banknote or Coin of Valuable class
     */
    @Override
    public Valuable createMoney(double value) {
        if (value == 0.25 || value  == 0.50) return new Coin(value,CURRENCY);
        else if (value == 1 || value == 2 || value == 5 || value == 10) return new Coin(value,CURRENCY);
        else if (value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) return new BankNote(value,CURRENCY);
        else throw new IllegalArgumentException("Thailand doesn't have this value banknote or coin");
    }
}
