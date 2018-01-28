package coinpurse;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class BankNote implements Valuable{

    private static long nextSerialNumber = 1000000;
    /**amount of the money*/
    private double value;
    /**brand of value*/
    private String currency;
    /**serial of the note*/
    private long serialNumber;

    /**
     * Initialize new BankNote object
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value, String currency ){
        this.value = value;
        this.currency = currency;
        this.serialNumber = nextSerialNumber++;
    }

    /**
     * @return currency of the value
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * @return amount of value
     */
    public double getValue() {
        return value;
    }
    /**
     * @return return the serial number
     */
    public long getSerialNumber() {
        return serialNumber;
    }

    /**
     * Test the of the object
     * @param obj that to test with
     * @return the same of an object, value and currency
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BankNote bankNote = (BankNote) obj;

        return this.currency.equals(bankNote.currency)
                && bankNote.value == this.value;
    }

    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
        return String.format("%.0f-%s(note) [%d]",value,currency,serialNumber);
    }

}
