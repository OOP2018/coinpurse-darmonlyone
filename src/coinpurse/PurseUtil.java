package coinpurse;

/**
 * check the purse class
 * @author manusporn Fukkham
 */
public class PurseUtil {

    public static void main(String[] args) {
        Purse purse = new Purse(3);
//        System.out.println(purse.getBalance());
//        System.out.println(purse.isFull());
        purse.insert(new Coin(10,"TBH"));
        purse.insert(new Coin(5,"TBH"));
        purse.insert(new Coin(2,"TBH"));
        purse.insert(new Coin(1,"TBH"));
        purse.insert(new Coin(3,"TBH"));
//        System.out.println(purse.count());
//        System.out.println(purse.isFull());
//        System.out.println(purse.getBalance());
//        System.out.println(purse.toString());
        System.out.println(purse.withdraw(17));
    }
}
