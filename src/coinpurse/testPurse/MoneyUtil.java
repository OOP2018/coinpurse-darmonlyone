package coinpurse.testPurse;

import coinpurse.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Test a Valuable class of using purse that contain coin and banknote
 * @author Manusporn Fukkham
 */
public class MoneyUtil {

    public static void main(String[] args) {
        Money max = MoneyUtil.max(new BankNote(100,"Baht"),new BankNote(500,"Baht"));
        System.out.println(max);
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
        System.out.println("Purse we need");
        printCheckPurse(filterByCurrency(valuable,"Bath"));
        printPurseHave(valuable);
        System.out.println("After sort ");
        sortMoney(valuable);
    }

    /**
     * print check list of purse
     * @param valuable List of Valuable
     */
    public static void printCheckPurse(List<Valuable> valuable){
        for (Valuable valuables: valuable){
            System.out.print(valuables + " ");
        }
        sortMoney(valuable);
    }

    /**
     * to filter the purse
     * @param valuable List of Valuable
     * @param currency currency that need for fill this valuable
     * @return List of Valuable that filter the currency
     */
    public static <E  extends Valuable> List<E> filterByCurrency(List<E> valuable, String currency){
        List<E> valuables = new ArrayList<>();
        for (E valuable1 : valuable){
            if (valuable1.getCurrency().equals(currency))
                valuables.add(valuable1);
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
    public static void sortMoney(List<? extends Valuable> valuable){
        Comparator<Valuable> comp = new ValueComparator();
        valuable.sort(comp);
        System.out.println(valuable);
    }
    /**
     * Return the larger argument, based on sort order, using
     * the objects' own compareTo method for comparing.
     * @param args one or more Comparable objects to compare.
     * @return the argument that would be last if sorted the elements.
     * @throws IllegalArgumentException if no arguments given.
     */
    @SafeVarargs
    public static <E extends Comparable<? super E>> E max(E ... args) {
        E max = args[0];
        for (E e :args) max = (e.compareTo(max)>0) ? e : max ;
        return max;
    }

}
