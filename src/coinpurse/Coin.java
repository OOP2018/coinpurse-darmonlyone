package coinpurse;

/**
 * Coin represents coinage (money) with fixed value and currency.
 * @author Manusporn Fukkham
 */
public class Coin extends Money {


    /**
     * Initialize new Coins object
     * @param value amount of the money
     * @param currency brand of value
     */
    public Coin(double value , String currency){
        super(value, currency);

    }

    /**
     * Make a string form an object
     * @return a string representation of the argumen
     */
    @Override
    public String toString() {
      return (getValue() >= 1 ? String.format("%.0f-%s(coin)",getValue(),getActualCurrency()) : String.format("%.2f-%s(coin)",getValue(),getCurrency()));
    }

}
