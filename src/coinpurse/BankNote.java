package coinpurse;

import java.util.ArrayList;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class BankNote extends Money{
    /**serial of the note*/
    private long serialNumber;
    /** currency of banknote that its take in*/
    private String valueCurrency;
    /** the currency of each serial Banknote */
    private static ArrayList<String> serialCurrencyArrayList = new ArrayList<>();
    /** the serialNumber of each serial Banknote */
    private static ArrayList<Long> serialNumberArrayList = new ArrayList<>();

    /**
     * Initialize new BankNote object
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value, String currency ){
        super(value,getCountryCurrency() == null ? currency : getCountryCurrency());
        valueCurrency = currency;
        long newSerialNumber;
        if (!serialCurrencyArrayList.contains(currency)) {
            newSerialNumber = 1000000;
            serialCurrencyArrayList.add(currency);
            serialNumberArrayList.add((long) 1000000);
        }else{
            newSerialNumber = serialNumberArrayList.get(serialCurrencyArrayList.indexOf(currency)) + 1;
            serialNumberArrayList.set(serialCurrencyArrayList.indexOf(currency), newSerialNumber);
        }
        this.serialNumber = newSerialNumber;
    }
    /**
     * @return return the serial number
     */
    public long getSerial() {
        return serialNumber;
    }

    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
        return String.format("%.0f-%s(note)[%d]",getValue(),valueCurrency,serialNumber);
    }
}
