package coinpurse;

import java.util.Arrays;
import java.util.List;

/**
 * MoneyFactory class for Malaysia Currency
 * @author Manusporn Fukkham
 */
public class MalayMoneyFactory extends MoneyFactory {
    /** Malay currency*/
    private final String CURRENCY = "Ringgit";
    /**Malay value of Coin*/
    private Double[] malayCoin = {0.05,0.10,0.20,0.50};
    /**Malay value of Banknote*/
    private Double[] malayNote = {1.0,2.0,5.0,10.0,20.0,50.0,100.0};
    /**Malay factory bankNote serial number*/
    private static long nextSerialNumber = 1000000;

    /**Constructor of MalayMoneyFactory*/
    public MalayMoneyFactory(){
        //for coinValue; if value is 0.05 but its mean 5 so coinValue = 100
        Money.setCountryCurrencyCoinValue(CURRENCY,100);
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
        List<Double> coin = Arrays.asList(malayCoin);
        List<Double> bankNote = Arrays.asList(malayNote);
        if (coin.contains(value))return new Coin(value,"Sen");
        else if (bankNote.contains(value)) return new BankNote(value,CURRENCY,nextSerialNumber++);
        else throw new IllegalArgumentException("Malaysia doesn't have this value banknote or coin");
    }
}