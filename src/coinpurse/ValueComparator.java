package coinpurse;

import java.util.Comparator;
/**
 * A comparator of Valuable class
 * @author Manusporn Fukkham
 */
public class ValueComparator implements Comparator<Valuable> {

    /**
     * Compare two objects that implement Valuable.
     * First compare them by currency, so that "Baht" < "Dollar".
     * If both objects have the same currency, order them by value.
     *
     * @param a Valuable object to compare with b
     * @param b valuable object
     * @return 1 if have higher value and currency
     *          -1 if have lower value and currency
     *          0 if both are the same value and currency
     */
    public int compare(Valuable a, Valuable b) {
        if (a.getCurrency().equals(b.getCurrency())) {
            return a.getValue() == b.getValue() ? 0 : a.getValue() > b.getValue() ? 1 : -1;
        } else {
            //Logical; first alphabet A - Z : low - high currency value
            if (a.getCurrency().charAt(0) > b.getCurrency().charAt(0)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}