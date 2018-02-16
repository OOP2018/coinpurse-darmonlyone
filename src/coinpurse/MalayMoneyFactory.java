package coinpurse;

/**
 * MoneyFactory class for Malaysia Currency
 * @author Manusporn Fukkham
 */
public class MalayMoneyFactory extends MoneyFactory {
    /** Malay currency*/
    private final String CURRENCY = "Ringgit";

    /**
     * Constructor of MalayMoneyFactory
     */
    public MalayMoneyFactory(){
        BankNote.setNextSerialNumber(1000000);
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
        if (value == 0.05 || value  == 0.10|| value == 0.20 || value == 0.50) return new Coin(value*100,"Sen");
        else if (value == 1 || value == 2 || value == 5 || value == 10) return new BankNote(value,CURRENCY);
        else if (value == 20 || value == 50 || value == 100 ) return new BankNote(value,CURRENCY);
        else throw new IllegalArgumentException("Malaysia doesn't have this value banknote or coin");
    }
}
