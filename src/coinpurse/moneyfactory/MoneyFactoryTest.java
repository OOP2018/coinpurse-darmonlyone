package coinpurse.moneyfactory;
import static org.junit.Assert.*;

import coinpurse.BankNote;
import coinpurse.Coin;
import coinpurse.Valuable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Money Factory test for using Junit 4 test
 * @author Manusporn Fukkham
 */
public class MoneyFactoryTest {
    /** tolerance for comparing two double values */
    private static final double TOL = 1.0E-6;
    /**thai currency*/
    private final String BAHT = "Baht";
    /**malay currency*/
    private final String RINGGIT = "Ringgit";
    /**not a value money factory can make as double*/
    private double[] notMoney = {0.025,0.25,0.11,1.5,3,4,11,99,101,99,9999,22.22,20.0002};
    /**not a value money factory can make as String*/
    private String[] notMoneyString = {"0.0255","0.25a","0.11","1.5","3","4","11","22.22","20.0002","123","asd","damn"};
    /**
     * Sets up the test fixture.
     * Called before every test method.
     */
    @Before
    public void setUp() {
        // nothing to initialize
    }

    /** Make a Coin and Banknote using requested value. */
    private Valuable makeMoney(double value) {
        try {
            return MoneyFactory.getInstance().createMoney(value);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
    /** test true false to make Money*/
    private boolean testMakeMoney(double value){
        try {
            makeMoney(value);
            return true;
        }catch (IllegalArgumentException e ){
            return false;
        }
    }
    /** Make a Coin and Banknote using requested value. */
    private Valuable makeMoney(String value) {
        try {
            return MoneyFactory.getInstance().createMoney(value);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
    /** test true false to make Money*/
    private boolean testMakeMoney(String value){
        try {
            makeMoney(value);
            return true;
        }catch (IllegalArgumentException e ){
            return false;
        }
    }
    /** run test method as double*/
    private void run(Object[] objects, boolean doubles){
        for (int i = 0, j = 0; doubles ? (j < notMoney.length) : (j < notMoneyString.length);) {
            if (i == objects.length)j++;
            if (j == 0){
                if (doubles) assertTrue(testMakeMoney((Double) objects[i]));
                else assertTrue(testMakeMoney((String) objects[i]));
                i++;
            } else {
                if (doubles) assertFalse(testMakeMoney(notMoney[j-1]));
                else assertFalse(testMakeMoney(notMoneyString[j-1]));
            }
        }
    }
    /** test make thai monet by createMoney() with Money();*/
    @Test
    public void testCreateThaiMoney(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());
        MoneyFactory m = MoneyFactory.getInstance();
        Assert.assertEquals(new Coin(1,BAHT),m.createMoney(1));
        assertEquals(new Coin(2,BAHT),m.createMoney("2"));
        assertEquals(new Coin(5,BAHT),m.createMoney(5));
        assertEquals(new Coin(10,BAHT),m.createMoney("10"));
        Assert.assertEquals(new BankNote(20,BAHT),m.createMoney(20));
        assertEquals(new BankNote(100,BAHT),m.createMoney("100"));
        assertEquals(new BankNote(500,BAHT),m.createMoney(500));
        assertEquals(new BankNote(1000,BAHT),m.createMoney("1000"));
    }

    /** test make thai money with double*/
    @Test
    public void testMakeThaiMoneyDouble(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());
        Double[] thaiMoney = {1.0,2.0,5.0,10.0,20.0,50.0,100.0,500.0,1000.0};
        run(thaiMoney,true);
    }
    /** test make thai money with String*/
    @Test
    public void testMakeThaiMoneyString(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());
        String[] thaiMoney = {"1","2","5","10","20","50","100","500","1000"};
        run(thaiMoney,false);
    }

    /** test make malay monet by createMoney() with Money();*/
    @Test
    public void testCreateMalayMoney(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory m = MoneyFactory.getInstance();
        assertEquals(new Coin(0.05,"Sen"),m.createMoney(0.05));
        assertEquals(new Coin(0.10,"Sen"),m.createMoney(0.1));
        assertEquals(new Coin(0.20,"Sen"),m.createMoney("0.20"));
        assertEquals(new Coin(0.50,"Sen"),m.createMoney(0.5));
        assertEquals(new BankNote(2,RINGGIT),m.createMoney("2"));
        assertEquals(new BankNote(1,RINGGIT),m.createMoney("1"));
        assertEquals(new BankNote(10,RINGGIT),m.createMoney("10"));
        assertEquals(new BankNote(5,RINGGIT),m.createMoney(5));
        assertEquals(new BankNote(10,RINGGIT),m.createMoney("10"));
        assertEquals(new BankNote(20,RINGGIT),m.createMoney(20));
        assertEquals(new BankNote(50,RINGGIT),m.createMoney("50"));
        assertEquals(new BankNote(100,RINGGIT),m.createMoney(100));
    }
    /** test make malay money with double*/
    @Test
    public void testMakeMalayMoneyDouble(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        Double[] malayMoney = {0.05,0.10,0.20,0.50,1.0,2.0,5.0,10.0,20.0,50.0,100.0};
        run(malayMoney,true);
    }
    /** test make malay money with String*/
    @Test
    public void testMakeMalayMoneyString(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        String[] malayMoney = {"0.05","0.10","0.20","0.50","1","2","5","10","20","50","100"};
        run(malayMoney,false);
    }
    /** test toString and currency */
    @Test
    public void testCollectToStringCurrency(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory factory = MoneyFactory.getInstance();

        Coin n1 = (Coin) factory.createMoney(0.20);
        assertEquals("20-Sen(coin)",n1.toString());
        assertEquals(RINGGIT,n1.getCurrency());

        Coin n2 = (Coin) factory.createMoney("0.05");
        assertEquals("5-Sen(coin)",n2.toString());
        assertEquals(RINGGIT,n2.getCurrency());

        Coin n3 = (Coin) factory.createMoney("0.10");
        assertEquals("10-Sen(coin)",n3.toString());
        assertEquals(RINGGIT,n3.getCurrency());

        Coin n4 = (Coin) factory.createMoney(0.5);
        assertEquals("50-Sen(coin)",n4.toString());
        assertEquals(RINGGIT,n4.getCurrency());

    }
    /** test currency without use toString*/
    @Test
    public void testCollectCurrency(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory factory = MoneyFactory.getInstance();
        Valuable n1 = factory.createMoney(0.20);
        assertEquals(RINGGIT,n1.getCurrency());

        Valuable n2 =  factory.createMoney("0.05");
        assertEquals(RINGGIT,n2.getCurrency());

        Valuable n3 =  factory.createMoney("0.10");
        assertEquals(RINGGIT,n3.getCurrency());

        Valuable n4 =  factory.createMoney(0.5);
        assertEquals(RINGGIT,n4.getCurrency());

        Valuable n5 = factory.createMoney(1);
        assertEquals(RINGGIT,n5.getCurrency());

        Valuable n6 =  factory.createMoney("2");
        assertEquals(RINGGIT,n6.getCurrency());

        Valuable n7 =  factory.createMoney("50");
        assertEquals(RINGGIT,n7.getCurrency());

        Valuable n8 =  factory.createMoney(100);
        assertEquals(RINGGIT,n8.getCurrency());

    }
    /** test get value equal eo expect value of Malay factory*/
    public void testGetMalayValue(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory f = MoneyFactory.getInstance();
        Valuable v = f.createMoney(0.05);
        assertEquals(0.05,v.getValue(),TOL);
        Valuable v2 = f.createMoney(0.1);
        assertEquals(0.1,v2.getValue(),TOL);
        Valuable v3 = f.createMoney(0.2);
        assertEquals(0.2,v3.getValue(),TOL);
        Valuable v4 = f.createMoney(0.5);
        assertEquals(0.5,v4.getValue(),TOL);
    }
    /** test getValue equal eo expect value*/
    @Test
    public void testGetValue(){
        testGetMalayValue();
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory f = MoneyFactory.getInstance();
        Valuable v = f.createMoney(0.05);
        assertEquals(0.05,v.getValue(),TOL);
        MoneyFactory.setFactory(new ThaiMoneyFactory());
        MoneyFactory f2 = MoneyFactory.getInstance();
        Valuable v2 = f2.createMoney(1);
        assertEquals(1,v2.getValue(),TOL);
        Valuable v3 = f2.createMoney(5);
        assertEquals(5,v3.getValue(),TOL);
    }
}
