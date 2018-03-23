package coinpurse.strategy;

import coinpurse.Valuable;

import java.util.List;

/**
 * strategy to withdraw money
 * @author Manusporn fukkham
 */
public interface WithDrawStrategy {

    /**
     * withdraw money in greedy strategy
     * find an return items from a collection  whose total value equals
     * the requested amount
     * @param amount amount of money to withdraw, with same currency. if amount is 0
     *               will return same money List
     * @param money contain the money to with draw, must not contain null
     *              and idt must not be empty
     *
     * @return the money of which hav withdraw, if the list of money can withdraw
     */
    List<Valuable> withdraw(Valuable amount, List<Valuable> money);
}
