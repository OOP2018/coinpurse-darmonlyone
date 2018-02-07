package coinpurse;

/**
 * check the purse class
 * @author manusporn Fukkham
 */
public class PurseUtil {

    public static void main(String[] args) {
        Purse purse = new Purse(5);
        System.out.println("Balance: " + purse.getBalance());
        System.out.println("Full: " + purse.isFull());
        purse.insert(new BankNote(20,"Baht"));
        purse.insert(new BankNote(20,"Baht"));
        purse.insert(new Coin(5,"Baht"));
        purse.insert(new Coin(2,"Baht"));
        purse.insert(new Coin(1,"Baht"));
        purse.insert(new Coin(3,"Baht"));
        purse.insert(new BankNote(20,"Baht"));
        System.out.println("Contain: " + purse.count());
        System.out.println("Full: " + purse.isFull());
        System.out.println("Balance: " +purse.getBalance());
        System.out.println("String: " +purse.toString());
        purse.withdraw(23);
        System.out.println("After withdraw: "+purse.toString());
    }
}
