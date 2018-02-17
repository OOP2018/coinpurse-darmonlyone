package coinpurse;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Money Factory test for using Junit 4 test
 * @author Manusporn Fukkham
 */
public class MoneyFactoryTest {
    /**thai currency*/
    private final String BAHT = "Baht";
    /**malay currency*/
    private final String RINGGIT = "Ringgit";

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
    /** test make thai monet by createMoney() with Money();*/
    @Test
    public void testCreateThaiMoney(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());
        MoneyFactory m = MoneyFactory.getInstance();
        assertEquals(new Coin(1,BAHT),m.createMoney(1));
        assertEquals(new Coin(2,BAHT),m.createMoney("2"));
        assertEquals(new Coin(5,BAHT),m.createMoney(5));
        assertEquals(new Coin(10,BAHT),m.createMoney("10"));
        assertEquals(new BankNote(20,BAHT),m.createMoney(20));
        assertEquals(new BankNote(100,BAHT),m.createMoney("100"));
        assertEquals(new BankNote(500,BAHT),m.createMoney(500));
        assertEquals(new BankNote(1000,BAHT),m.createMoney("1000"));
    }
    /** test make thai money with double*/
    @Test
    public void testMakeThaiMoneyDouble(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());

        assertTrue(testMakeMoney(1));
        assertTrue(testMakeMoney(2));
        assertTrue(testMakeMoney(5));
        assertTrue(testMakeMoney(10));
        assertTrue(testMakeMoney(20));
        assertTrue(testMakeMoney(100));
        assertTrue(testMakeMoney(500*2));
        assertTrue(testMakeMoney(1000/2));

        assertFalse(testMakeMoney(0.25));
        assertFalse(testMakeMoney(0.5));
        assertFalse(testMakeMoney(0.1));
        assertFalse(testMakeMoney(0.35));
        assertFalse(testMakeMoney(3));
        assertFalse(testMakeMoney(4));
        assertFalse(testMakeMoney(800));
        assertFalse(testMakeMoney(1001));
    }
    /** test make thai money with String*/
    @Test
    public void testMakeThaiMoneyString(){
        MoneyFactory.setFactory(new ThaiMoneyFactory());

        assertTrue(testMakeMoney("1"));
        assertTrue(testMakeMoney("2"));
        assertTrue(testMakeMoney("5"));
        assertTrue(testMakeMoney("10"));
        assertTrue(testMakeMoney("20"));
        assertTrue(testMakeMoney("100"));
        assertTrue(testMakeMoney("500"));
        assertTrue(testMakeMoney("1000"));

        assertFalse(testMakeMoney("damn"));
        assertFalse(testMakeMoney("0.25"));
        assertFalse(testMakeMoney("0.5"));
        assertFalse(testMakeMoney("0.1"));
        assertFalse(testMakeMoney("0.35"));
        assertFalse(testMakeMoney("3"));
        assertFalse(testMakeMoney("4s"));
        assertFalse(testMakeMoney("800"));
        assertFalse(testMakeMoney("1021"));
    }

    /** test make malay monet by createMoney() with Money();*/
    @Test
    public void testCreateMalayMoney(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory m = MoneyFactory.getInstance();
        assertEquals(new Coin(5,"Sen"),m.createMoney(0.05));
        assertEquals(new Coin(10,"Sen"),m.createMoney(0.1));
        assertEquals(new Coin(20,"Sen"),m.createMoney("0.20"));
        assertEquals(new Coin(50,"Sen"),m.createMoney(0.5));
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
        assertTrue(testMakeMoney(0.05));
        assertTrue(testMakeMoney(0.10));
        assertTrue(testMakeMoney(0.2));
        assertTrue(testMakeMoney(0.50));
        assertTrue(testMakeMoney(1));
        assertTrue(testMakeMoney(2));
        assertTrue(testMakeMoney(5));
        assertTrue(testMakeMoney(10));
        assertTrue(testMakeMoney(50));
        assertTrue(testMakeMoney(100));
        assertTrue(testMakeMoney(100/5));

        assertFalse(testMakeMoney(0.11));
        assertFalse(testMakeMoney(0.35));
        assertFalse(testMakeMoney(3));
        assertFalse(testMakeMoney(4));
        assertFalse(testMakeMoney(800));
        assertFalse(testMakeMoney(99));
    }
    /** test make malay money with String*/
    @Test
    public void testMakeMalayMoneyString(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        assertTrue(testMakeMoney("0.05"));
        assertTrue(testMakeMoney("0.10"));
        assertTrue(testMakeMoney("0.2"));
        assertTrue(testMakeMoney("0.50"));
        assertTrue(testMakeMoney("1"));
        assertTrue(testMakeMoney("2"));
        assertTrue(testMakeMoney("5"));
        assertTrue(testMakeMoney("10"));
        assertTrue(testMakeMoney("50"));
        assertTrue(testMakeMoney("20"));
        assertTrue(testMakeMoney("100"));

        assertFalse(testMakeMoney("false"));
        assertFalse(testMakeMoney("0.12"));
        assertFalse(testMakeMoney("0.35"));
        assertFalse(testMakeMoney("as3"));
        assertFalse(testMakeMoney("4.0"));
        assertFalse(testMakeMoney("501"));
        assertFalse(testMakeMoney("102"));
    }
    @Test
    public void testCollectToStringCurrency(){
        MoneyFactory.setFactory(new MalayMoneyFactory());
        MoneyFactory factory = MoneyFactory.getInstance();
        Coin n1 = (Coin) factory.createMoney(0.20);
        Coin n2 = (Coin) factory.createMoney("0.05");
        Coin n3 = (Coin) factory.createMoney("0.10");
        Coin n4 = (Coin) factory.createMoney(0.5);
        assertEquals("20-Sen(coin)",n1.toString());
        assertEquals("5-Sen(coin)",n2.toString());
        assertEquals("10-Sen(coin)",n3.toString());
        assertEquals("50-Sen(coin)",n4.toString());
        assertEquals(RINGGIT,n1.getCurrency());
        assertEquals(RINGGIT,n2.getCurrency());
        assertEquals(RINGGIT,n3.getCurrency());
        assertEquals(RINGGIT,n4.getCurrency());

    }
}
