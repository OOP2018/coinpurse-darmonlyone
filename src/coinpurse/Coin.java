package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class Coin implements Comparable<Coin> {
    /**amount of the money*/
    private double value;
    /**brand of value*/
    private String currency;

    /**
     * Initialize new Coins object
     * @param value amount of the money
     * @param currency brand of value
     */
    public Coin(double value , String currency){

        if(value < 0 ) throw new IllegalArgumentException("The value must not be negative.");
            this.value = value;
            this.currency = currency;
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
        return currency;
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

        Coin coin = (Coin) obj;

        return this.currency.equals(coin.currency) && coin.value == this.value;
    }

    /**
     * Compare value
     * @param coin the object to compare the value
     * @return -1 as less than, 1 as more than , and 0 as total
     */
    @Override
    public int compareTo(Coin coin){
        if (value < coin.getValue())
            return -1;
        else if (value > coin.getValue())
            return 1;
        else return 0;

    }

    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
      return (value >= 1 ? String.format("%.0f-%s",value,currency) : String.format("%.2f-%s",value,currency));
    }

}
