package coinpurse;

import java.util.Arrays;
import java.util.List;

/**
 * MoneyFactory class for Thai Currency
 * @author Manusporn Fukkham
 */
public class ThaiMoneyFactory extends MoneyFactory {
    /** Thai Currency */
    private final String CURRENCY = "Baht";
    /**Thai value of coin*/
    private Double[] thaiCoin = {1.0,2.0,5.0,10.0};
    /**Thai value of Banknote*/
    private Double[] thaiNote = {20.0,50.0,100.0,500.0,1000.0};
    /**Thai factory bankNote serial number*/
    private static long nextSerialNumber = 1000000;
    /**Constructor of ThaiMoneyFactory */
    public ThaiMoneyFactory(){
        //for coinValue; if value is 0.05 but its mean 5 so coinValue = 100
        Money.setCountryCurrencyCoinValue(CURRENCY,1);
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
        List<Double> coin = Arrays.asList(thaiCoin);
        List<Double> bankNote = Arrays.asList(thaiNote);
        if (coin.contains(value))return new Coin(value,CURRENCY);
        if (bankNote.contains(value))return new BankNote(value,CURRENCY,nextSerialNumber++);
        else throw new IllegalArgumentException("Thailand doesn't have this value banknote or coin");
    }
}
