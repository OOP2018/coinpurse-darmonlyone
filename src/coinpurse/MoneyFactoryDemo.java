package coinpurse;

import java.util.Arrays;

public class MoneyFactoryDemo {
    public static void main(String[] args) {
        System.out.println("*------Thai Factory------*");
        setMoneyFactory(new ThaiMoneyFactory());
        double[] ThaiAmount = {0.05,0.25,0.5,0.2,1,2,3,5,10,20,50,100,200,500,1000};
        double[] ThaiExpect = {0.25,0.50,1,2,5,10,20,50,100,500,1000};
        test(ThaiAmount,ThaiExpect,-1);
        System.out.println("*------Malaysia Factory------*");
        setMoneyFactory(new MalayMoneyFactory());
        double[] MalayAmount = {0.05,0.25,0.1,0.2,0.5,1,2,3,5,10,20,50,100,200,500,1000};
        double[] MalayExpect = {0.05,0.1,0.2,0.5,1,2,5,10,20,50,100};
        test(MalayAmount,MalayExpect,1);
    }
    public static void test(double[] amount, double[] expect,int num){
        MoneyFactory factory = MoneyFactory.getInstance();
        Valuable[] expects = createMoney(factory, expect);
        Valuable[] valuables = createMoney(factory, amount);
        System.out.println("Add "+Arrays.toString(amount)+",\"100\" to the factory");
        for (int i = 0 ; i < valuables.length-1 ; i++){
            if (valuables[i] != null)
                System.out.println((expect(expects[i], valuables[i])) ? " : true" : " : false");
        }
        System.out.println("Test create String");
        Valuable valuableString = createMoney(factory,"100");
        String expectString = num>0 ? "Ringgit":"Baht";
        System.out.printf("Expect: %-30s Actual: 100-%s(note) [xxxxxxx]    : ", valuableString, expectString);
        System.out.println(valuableString.getValue() == 100 && valuableString.getCurrency().equals(expectString));
    }
    public static boolean expect(Valuable expect, Valuable actual){
        System.out.printf("Expect: %-30s Actual: %-30s", expect, actual);
        return expect.equals(actual);
    }
    public static Valuable[] createMoney(MoneyFactory m,double...value){
        Valuable[] valuables = new Valuable[value.length];
        int i = 0;
        for (double v : value) {
            try {
                valuables[i] = m.createMoney(v);
                i++;
            }catch (IllegalArgumentException e){
            }
        }
        return valuables;
    }
    public static Valuable createMoney(MoneyFactory m,String value){
        return m.createMoney(value);
    }
    public static void setMoneyFactory(MoneyFactory m){
        MoneyFactory.setFactory(m);
    }

}
