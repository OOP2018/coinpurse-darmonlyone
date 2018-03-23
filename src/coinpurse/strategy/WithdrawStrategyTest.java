package coinpurse.strategy;

import java.util.*;

import coinpurse.*;
import coinpurse.moneyfactory.MoneyFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WithdrawStrategyTest {
    /**Thai currency for valuable*/
    private final String THB = "Baht";
    /** tolerance for comparing two double values */
    private static final double TOL = 1.0E-6;
    private List<Valuable> valuableList;
    /**The strategy to withdraw*/
    private WithDrawStrategy withDrawStrategy;
    /**Comparator of valuable**/
    private Comparator<Valuable> comp = new ValueComparator();
    @Before
    public void setUp(){
//        withDrawStrategy = new RecusiveWithdraw();
        withDrawStrategy = new GreedyStrategy();
        valuableList = new ArrayList<>();
    }

    /**test withdraw all valuable*/
    @Test(timeout = 2000)
    public void testWithdrawAll(){
        assertTrue(valuableList.isEmpty());
        valuableList.addAll(makeMoneyArray(1,2,5,10,20,50,100,500,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        valuableList = withDrawStrategy.withdraw(makeMoney(1688),valuableList);
        double value = 0;
        for (Valuable valuable : valuableList){
            value += valuable.getValue();
        }
        assertFalse(value == 0);
        assertTrue(value == 1688);
        assertFalse(valuableList.isEmpty());
    }

    /**test withdraw high value*/
    @Test(timeout = 2000)
    public void testWithdrawHighAmount(){
        valuableList.addAll(makeMoneyArray(1,1,2,5,10,20,50,100,500,500,1000,1000,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        valuableList = withDrawStrategy.withdraw(makeMoney(3502),valuableList);
        assertFalse(valuableList.isEmpty());
        double value = 0;
        for (Valuable valuable : valuableList){
            value += valuable.getValue();
        }
        assertFalse(value == 4189);
        assertTrue(value != 0);
        System.out.println("asd"+valuableList);
        assertEquals(3502,value,TOL);
    }

    /**test withdraw zero amount*/
    @Test(timeout = 2000)
    public void testWithdrawZeroAmount(){
        valuableList.addAll(makeMoneyArray(1,2,5,10,20,50,100,500,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(0,"Baht"),valuableList);
        double contain = 0;
        for (Valuable valuable : valuableList){
            contain += valuable.getValue();
        }
        assertEquals(1688,contain,TOL);
        assertFalse( contain == 0);
        assertTrue(valuableList.size() == 9);
        assertFalse(valuableList.size() < 9);
        assertFalse(valuableList.isEmpty());
    }

    /**test withdraw with shuffle amount*/
    @Test(timeout = 2000)
    public void testWithdrawRandomAmount(){
        valuableList.addAll(makeMoneyArray(2,1,2,5,10,20,50,5,100,500,100,1000,500));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(1674,"Baht"),valuableList);
        assertFalse(valuableList.isEmpty());
        assertTrue(valuableList.size() != 0);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(621,"Baht"),valuableList);
        System.out.println(valuableList);
        assertTrue(valuableList.isEmpty());
    }

    /**test withdraw with not thai currency*/
    @Test(timeout = 2000)
    public void testWithdrawDifferentCurrency(){
        valuableList.addAll(makeMoneyArray(1,2,5,10,20,50,100,500,1000));
        valuableList.add(makeFakeMoney(1,"Won"));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        List<Valuable> listCheck = new ArrayList<>();
        listCheck.addAll(valuableList);
        listCheck.remove(0);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(1,"Won"),valuableList);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(2,"LOL"),valuableList);
        valuableList = withDrawStrategy.withdraw(makeFakeMoney(5,"Won"),valuableList);
        assertEquals(new ArrayList<>(),valuableList);
        assertTrue(valuableList.isEmpty());
    }

    /**test withdraw over amount*/
    @Test(timeout = 2000)
    public void testWithDrawOverAmount(){
        List<Valuable> list = new ArrayList<>();
        list.addAll(makeMoneyArray(1000, 1000));
        list = withDrawStrategy.withdraw(makeFakeMoney(10000000, "Baht"), list);
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0 );
        double value = 0;
        for (Valuable valuable : list){
            value += valuable.getValue();
        }
        assertEquals(0,value,TOL);

    }

    /**test Empty list*/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListThrowException(){
        List<Valuable> list = new ArrayList<>();
        list.add(5,new Money(10,THB));
    }

    /**test Empty list*/
    @Test(expected = NullPointerException.class)
    public void testWithdrawWithNullAmount(){
        List<Valuable> list = new ArrayList<>();
        list.add(null);
        list.addAll(makeMoneyArray(1000, 1000));
        list = withDrawStrategy.withdraw(makeMoney(10),list);
    }
    /** Make a Money using requested value currency as Baht */
    private Valuable makeMoney(double value) {
        return new Money(value,"Baht");
    }
    /** Make a Money as List using requested value currency as Baht */
    private List<Valuable> makeMoneyArray(double ...values) {
        List<Valuable> list = new ArrayList<>();
        for (double value : values){
             list.add(new Money(value,THB));
        }
        return list;
    }

    /** Make a Money using requested value and currency  */
    private Valuable makeFakeMoney(double value, String currency){
        return new Money(value,currency);
    }
}
