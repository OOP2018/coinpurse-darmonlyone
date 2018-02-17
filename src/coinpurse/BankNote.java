package coinpurse;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class BankNote extends Money{
    /**next serial number of BankNote*/
    private static long nextSerialNumber = 1000000;
    /**serial of the note*/
    private long serialNumber;
    /** currency of banknote that its take in*/
    private String valueCurrency;
    /**
     * Initialize new BankNote object
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value, String currency ){
        super(value,getCountryCurrency() == null ? currency : getCountryCurrency());
        this.serialNumber = nextSerialNumber++;
        valueCurrency = currency;
    }
    /**
     * @return return the serial number
     */
    public long getSerial() {
        return serialNumber;
    }

    /**
     * too set new SerialNumber for new Factory
     * @param nextSerialNumber start number of SerialNumber
     */
    public static void setNextSerialNumber(long nextSerialNumber) {
        BankNote.nextSerialNumber = nextSerialNumber;
    }
    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
        return String.format("%.0f-%s(note) [%d]",getValue(),valueCurrency,serialNumber);
    }
}
