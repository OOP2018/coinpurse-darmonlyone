package coinpurse;

public class Money implements Valuable{
    /**amount of the money*/
    private double value;
    /**brand of value*/
    private String currency;
    /** actual currency*/
    private static String countryCurrency;

    public Money(double value , String currency){
        if(value < 0 ) throw new IllegalArgumentException("The value must not be negative.");
        this.value = value;
        this.currency = currency;
    }
    /**
     * Compare value
     * @param money the object to compare the value
     * @return -1 as less than, 1 as more than , and 0 as total
     */
    @Override
    public int compareTo(Valuable money){
        return Double.compare(this.getValue(),money.getValue());
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

        Money money = (Money) obj;

        return this.getCurrency().equals(money.getCurrency()) && money.getValue() == this.getValue();
    }
    /**
     * @return amount of value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return currency of the value
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * set Country currency
     * @param actualCurrency
     */
    public static void setCountryCurrency(String actualCurrency) {
        countryCurrency = actualCurrency;
    }
    /**
     * @return Country currency of the value
     */
    public static String getCountryCurrency() {
        return countryCurrency;
    }
}
