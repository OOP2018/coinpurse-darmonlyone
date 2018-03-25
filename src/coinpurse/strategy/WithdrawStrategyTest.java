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
    /**list of purse*/
    private List<Valuable> valuableList;
    /**list of purse*/
    private List<Valuable> withdrawList;
    /**The strategy to withdraw*/
    private WithDrawStrategy withDrawStrategy;
    /**Comparator of valuable**/
    private Comparator<Valuable> comp = new ValueComparator();
    @Before
    public void setUp(){
//        withDrawStrategy = new RecusiveWithdraw();
        withDrawStrategy = new GreedyStrategy();
        valuableList = new ArrayList<>();
        withdrawList = new ArrayList<>();
    }

    /**test withdraw all valuable*/
    @Test(timeout = 2000)
    public void testWithdrawAll(){
        assertTrue(valuableList.isEmpty());
        valuableList.addAll(makeMoneyArray(1,2,5,10,20,50,100,500,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        withdrawList = withDrawStrategy.withdraw(makeMoney(1688),valuableList);
        double value = 0;
        for (Valuable valuable : withdrawList){
            value += valuable.getValue();
        }
        assertTrue(valuableList.containsAll(withdrawList));
        assertEquals(withdrawList,valuableList);
        assertFalse(value == 0);
        assertTrue(value == 1688);
        assertFalse(withdrawList.isEmpty());
    }

    /**test withdraw high value*/
    @Test(timeout = 2000)
    public void testWithdrawHighAmount(){
        valuableList.addAll(makeMoneyArray(1,1,2,5,10,20,50,100,500,500,1000,1000,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        withdrawList = withDrawStrategy.withdraw(makeMoney(3502),valuableList);
        assertFalse(valuableList.isEmpty());
        double value = 0;
        for (Valuable valuable : withdrawList){
            value += valuable.getValue();
        }
        assertFalse(withdrawList.equals(valuableList));
        assertFalse(value == 4189);
        assertTrue(value != 0);
        assertEquals(3502,value,TOL);
    }

    /**test withdraw zero amount*/
    @Test(timeout = 2000)
    public void testWithdrawZeroAmount(){
        valuableList.addAll(makeMoneyArray(1,2,5,10,20,50,100,500,1000));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        withdrawList = withDrawStrategy.withdraw(makeFakeMoney(0,"Baht"),valuableList);
        double contain = 0;
        for (Valuable valuable : valuableList){
            contain += valuable.getValue();
        }
        assertTrue(withdrawList.equals(valuableList));
        assertEquals(1688,contain,TOL);
        assertFalse( contain == 0);
        assertTrue(withdrawList.size() == 9);
        assertFalse(withdrawList.size() < 9);
        assertFalse(withdrawList.isEmpty());
    }

    /**test withdraw with shuffle amount*/
    @Test(timeout = 2000)
    public void testWithdrawRandomAmount(){
        valuableList.addAll(makeMoneyArray(2,1,2,5,10,20,50,5,100,500,100,1000,500));
        valuableList.sort(comp);
        Collections.reverse(valuableList);
        withdrawList = withDrawStrategy.withdraw(makeFakeMoney(1674,"Baht"),valuableList);
        assertFalse(withdrawList.isEmpty());
        assertTrue(withdrawList.size() != 0);
        withdrawList.addAll(withDrawStrategy.withdraw(makeFakeMoney(619,"Baht"),valuableList));
        assertFalse(withdrawList.isEmpty());
        assertTrue(valuableList.containsAll(withdrawList));
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
        withdrawList = withDrawStrategy.withdraw(makeFakeMoney(1,"Won"),valuableList);
        withdrawList.addAll(withDrawStrategy.withdraw(makeFakeMoney(2,"LOL"),valuableList));
        withdrawList.addAll(withDrawStrategy.withdraw(makeFakeMoney(5,"Won"),valuableList));
        assertEquals(new ArrayList<>().add(makeFakeMoney(1,"Won")),withdrawList.contains(makeFakeMoney(1,"Won")));
        assertFalse(withdrawList.isEmpty());
        assertTrue(withdrawList.size() == 1);
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
        list.add(1,new Money(10,THB));
    }

    /**test Empty list*/
    @Test(expected = NullPointerException.class)
    public void testWithdrawWithNullAmount(){
        List<Valuable> list = new ArrayList<>();
        list.add(null);
        list.addAll(makeMoneyArray(1000, 1000));
        withdrawList = withDrawStrategy.withdraw(makeMoney(10),list);
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
