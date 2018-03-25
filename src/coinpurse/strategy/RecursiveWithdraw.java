package coinpurse.strategy;

import coinpurse.Coin;
import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Withdraw Strategy using recusion
 * @author Manusporn Fukkham
 */
public class RecursiveWithdraw implements WithDrawStrategy{

    /**
     * withdraw money in using recursion
     * find an return items from a collection  whose total value equals
     * the requested amount
     * @param amount amount of money to withdraw, with same currency. if amount is 0
     *               will return same money List
     * @param money contain the money to with draw, must not contain null
     *              and List must not be empty
     *
     * @return the money of which have withdraw, if the list of money can withdraw
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        if (amount.getValue() == 0 ) return new ArrayList<>();
        if (money.size() == 0) return null;

        Valuable first = money.get(0);
        double valuable = amount.getValue() - first.getValue();
        List<Valuable> result;
        //add choice
        if ((result = withdraw(new Money(valuable,amount.getCurrency()),money.subList(1,money.size()))) != null) {
            result.add(first);
            return result;
        }
        //skip choice
        if ((result = withdraw(amount,money.subList(1,money.size()))) != null) {
            return result;
        }
        return null;
    }

}
