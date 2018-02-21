package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class Coin extends Money {
    /** currency of coin that its take in*/
    private String valueCurrency;
    /** the value of coin should be*/
    private double beValue;
    private static double coinValue = 1;
    /**
     * Initialize new Coins object
     * @param value amount of the money
     * @param currency brand of value
     */
    public Coin(double value , String currency){
        super(value,getCountryCurrency()==null ? currency : getCountryCurrency());
        valueCurrency = currency;
        beValue = value*coinValue;
    }
    /** set a amount of coinValue*/
    public static void setCoinValue(double coinValue) {
        Coin.coinValue = coinValue;
    }

    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
        return ( String.format("%.0f-%s(coin)",beValue,valueCurrency));
    }

}
