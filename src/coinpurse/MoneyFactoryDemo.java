package coinpurse;

import java.util.Arrays;

/**
 * Demo of MoneyFactory for checking the collection
 * @author Manusporn Fukkham
 */
public class MoneyFactoryDemo {
    /**
     * main run testing the program
     */
    public static void main(String[] args) {
        System.out.println("*------Thai Factory------*");
        setMoneyFactory(new ThaiMoneyFactory());
        double[] thaiAmount = {0.05,0.25,0.5,0.2,1,2,3,5,10,20,50,100,200,500,1000};
        String[] thaiAmountString = {"0.05","0.25","0.5","0.2","1","2","3","5","10","20","50","100","200","500","1000"};
        double[] thaiExpect = {0.25,0.50,1,2,5,10,20,50,100,500,1000};
        testDouble(thaiAmount,thaiExpect,-1);
        testString(thaiAmountString,thaiExpect,-1);

        System.out.println("\n*------Malaysia Factory------*");
        setMoneyFactory(new MalayMoneyFactory());
        double[] malayAmount = {0.05,0.25,0.1,0.2,0.5,1,2,3,5,10,20,50,100,200,500,1000};
        String[] malayAmountString = {"0.05","0.25","0.1","0.2","0.5","1","2","3","5","10","20","50","100","200","500","1000"};
        double[] malayExpect = {0.05,0.1,0.2,0.5,1,2,5,10,20,50,100};
        testDouble(malayAmount,malayExpect,1);
        testString(malayAmountString,malayExpect,-1);
    }

    /**
     * Testing method for Factories
     * @param amount of value to make
     * @param expect an expect value to be
     */
    public static void testDouble(double[] amount, double[] expect, int num){
        MoneyFactory factory = MoneyFactory.getInstance();
        Valuable[] expects = setExpectMoney(num < 0 ? "Baht" : "Ringgit" , expect);
        Valuable[] valuables = createMoney(factory, amount);
        System.out.println("Add Double"+Arrays.toString(amount)+" to the factory");
        for (int i = 0 ; i < valuables.length ; i++){
            if (valuables[i] != null)
                System.out.println((expectCehck(expects[i], valuables[i])) ? " : true" : " : false");
        }

    }
    public static void testString(String[] amount, double[] expect, int num){
        MoneyFactory factory = MoneyFactory.getInstance();
        Valuable[] expects = setExpectMoney(num < 0 ? "Baht" : "Ringgit" , expect);

        Valuable[] valuables = new Valuable[amount.length];
        for (int i = 0; i < amount.length ; i++) {
            try {
                valuables[i] = createMoney(factory,amount[i]);
            } catch (IllegalArgumentException ignore){}
        }
        System.out.println("Add String"+Arrays.toString(amount)+" to the factory");
        int j = 0;
        for (int i = 0 ; i < valuables.length ; i++){
            if (valuables[i] != null) {
                System.out.println((expectCehck(expects[j], valuables[i])) ? " : true" : " : false");
                j++;
            }
        }
    }

    /** Check the collect of expect and actual*/
    public static boolean expectCehck(Valuable expect, Valuable actual){
        System.out.printf("Expect: %-30s Actual: %-30s", expect, actual);
        return expect.equals(actual);
    }
    /** create money of actual */
    public static Valuable[] createMoney(MoneyFactory m,double...value){
        Valuable[] valuables = new Valuable[value.length];
        int i = 0;
        for (double v : value) {
            try {
                valuables[i] = m.createMoney(v);
                i++;
            }catch (IllegalArgumentException ignored){
            }
        }
        return valuables;
    }
    /** create money form string */
    public static Valuable createMoney(MoneyFactory m,String value){
        return m.createMoney(value);
    }
    /** set Factory*/
    public static void setMoneyFactory(MoneyFactory m){
        MoneyFactory.setFactory(m);
    }
    /** set an expect valuable*/
    public static Valuable[] setExpectMoney(String currency, double[] expect){
        Valuable[] valuables = new Valuable[expect.length];
        for (int i = 0; i < valuables.length ; i++) {
            valuables[i] = makeMoneyExpect(expect[i]);
        }
        return valuables;
    }
    /** make money of an expect */
    private static Valuable makeMoneyExpect(double value) {
        try {
            return MoneyFactory.getInstance().createMoney(value);
        }catch (IllegalArgumentException ignore){
            return null;
        }
    }
}
