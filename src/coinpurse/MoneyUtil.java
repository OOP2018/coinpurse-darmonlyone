package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Test a Valuable class of using purse that contain coin and banknote
 * @author Manusporn Fukkham
 */
public class MoneyUtil {

    public static void main(String[] args) {
        List<Valuable> valuable = new ArrayList<>();
        valuable.add(new Coin(1,"Bath"));
        valuable.add(new BankNote(100 , "Bath"));
        valuable.add(new BankNote(200,"Bath"));
        valuable.add( new BankNote(0.5, "Bath"));
        valuable.add( new Coin(0.25, "Bath"));
        valuable.add( new Coin(1.0, "Dollar"));
        valuable.add( new Coin(2.0, "Dollar"));
        valuable.add( new Coin(3.0, "Bath"));
        valuable.add( new Coin(4.0, "Bath"));
        valuable.add( new Coin(5.0, "Rubie"));
        printCheckPurse(filterByCurrency(valuable,"Bath"));
        sortPurse(valuable);
    }

    /**
     * print check list of purse
     * @param valuable List of Valuable
     */
    public static void printCheckPurse(List<Valuable> valuable){
        System.out.println("Purse we need");
        for (Valuable valuables: valuable){
            System.out.print(valuables + " ");
        }
        System.out.println("");
        sortPurse(valuable);
    }

    /**
     * to filter the purse
     * @param valuable List of Valuable
     * @param currency currency that need for fill this valuable
     * @return List of Valuable that filter the currency
     */
    public static List<Valuable> filterByCurrency(List<Valuable> valuable, String currency){
        printPurseHave(valuable);
        List<Valuable> valuables = new ArrayList<>();
        for (int i = 0 ; i < valuable.size() ; i++){
            if (valuable.get(i).getCurrency().equals(currency))
                valuables.add(valuable.get(i));
        }
        return valuables;
    }

    /**
     * to print the out the money that purse contain
     * @param valuables List of Valuable
     */
    private static void printPurseHave(List<Valuable> valuables) {
        System.out.println("We have coin");
        for (Valuable coin: valuables){
            System.out.print(coin + " ");
        }
        System.out.println("");
    }

    /**
     * sorting the purse
     * @param valuable List of Valuable
     */
    public static void sortPurse(List<Valuable> valuable){
        Comparator<Valuable> comp = new ValueComparator();
        Collections.sort(valuable,comp);
        System.out.println("After sort ");
        for (Valuable coin: valuable){
            System.out.print(coin + " ");
        }
    }
}
