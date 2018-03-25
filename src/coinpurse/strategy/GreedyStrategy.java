package coinpurse.strategy;

import coinpurse.Valuable;
import coinpurse.ValueComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Greeedy stratrgy to withdraw a money
 * @author Manusporn Fukkham
 */
public class GreedyStrategy implements WithDrawStrategy {

    /**
     * withdraw money in greedy strategy
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
        if (amount.getValue() == 0)return new ArrayList<>();
        List<Valuable> withDraw = new ArrayList<>();
        double amountNeededToWithdraw = amount.getValue();
        for (Valuable monies : money){
            if (amountNeededToWithdraw != 0 && monies.getCurrency().equalsIgnoreCase(amount.getCurrency())) {
                if ((amountNeededToWithdraw - monies.getValue() >= 0)){
                    amountNeededToWithdraw -= monies.getValue();
                    withDraw.add(monies);
                }
            }
        }
        if (amountNeededToWithdraw > 0 || withDraw.isEmpty()){
            return null;
        }
        return withDraw;
    }
}
