package coinpurse;

import java.util.ArrayList;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class BankNote extends Money{
    /**next serial number*/
    private static long nextSerialNumber = 1000000;
    /**serial of the note*/
    private long serialNumber;
    /** currency of banknote that its take in*/
    private String valueCurrency;

    /**
     * Initialize new BankNote object
     * @param value amount of the money
     * @param currency brand of value
     * @param newSerialNumber for the serial number of the factory
     */
    public BankNote(double value, String currency,long newSerialNumber ){
        super(value,getCountryCurrency() == null ? currency : getCountryCurrency());
        valueCurrency = currency;
        this.serialNumber = newSerialNumber;
    }

    /**
     * Initialize new BankNote object within didn't make form factory
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value, String currency){
        this(value,currency,nextSerialNumber++);
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
