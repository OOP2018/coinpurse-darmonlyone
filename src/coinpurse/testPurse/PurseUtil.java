package coinpurse.testPurse;

import coinpurse.*;

/**
 * check the purse class
 * @author manusporn Fukkham
 */
public class PurseUtil {
    private static String CURRENCY = "Baht";

    public static Money makeMoney(double value){
        return value < 20 ? new Coin(value,CURRENCY) : new BankNote(value,CURRENCY);
    }

    public static void main(String[] args) {
        Purse purse,purse2,purse3;

        purse = insertPurse(makeMoney(10),makeMoney(20),makeMoney(10),makeMoney(10),makeMoney(5),makeMoney(200),new Coin(5,"dollar"));
        purse2 = insertPurse(makeMoney(20),makeMoney(0.25),makeMoney(5),makeMoney(56),makeMoney(10),makeMoney(1));
        purse3 = insertPurse(makeMoney(1),makeMoney(2),makeMoney(5),makeMoney(10),makeMoney(15),makeMoney(20),makeMoney(50));

        System.out.println("Before withdraw");
        System.out.println(purse);
        System.out.println(purse2);
        System.out.println(purse3);

        purse.withdraw(new Money(5,"Dollar"));
        purse2.withdraw(35.25);
        purse3.withdraw(58);

        double[] expect = {255,57,45};

        System.out.println("After Withdraw");
        System.out.print(purse);
        System.out.println(asTrue(purse.getBalance(),expect[0]));
        System.out.print(purse2);
        System.out.println(asTrue(purse2.getBalance(),expect[1]));
        System.out.print(purse3);
        System.out.println(asTrue(purse3.getBalance(),expect[2]));
    }

    public static Purse insertPurse(Valuable...valuable){
        Purse p = new Purse(valuable.length);
        for (Valuable valuable1 : valuable){
            p.insert(valuable1);
        }
        return p;
    }
    public static boolean asTrue(double a, double b){
        return  (Double.compare(a,b) == 0);
    }
}
