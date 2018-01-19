package coinpurse;

import java.util.ArrayList;
import java.util.List;

public class MoneyUtit {
    public static void main(String[] args) {
        List<Coin> coins = new ArrayList<Coin>();
        coins.add( new Coin(20.0, "Dollar"));
        coins.add( new Coin(20.0, "Bath"));
        coins.add( new Coin(0.5, "Bath"));
        coins.add( new Coin(0.25, "Bath"));
        coins.add( new Coin(1.0, "Bath"));
        coins.add( new Coin(5.0, "Bath"));
        printCheckCoin(coins);
    }
    private static void printCheckCoin(List<Coin> coins){
        System.out.println("We have coin");
        for (Coin coin: coins){
            System.out.print(coin + " ");
        }
        System.out.println("");
        sortCoins(coins);
    }
    private static List<Coin> filterByCurrency(List<Coin> coins, String currency){
        for (Coin coin : coins){
            if (!coin.getCurrency().equals(currency))
                coins.remove(coin);
        }
        return coins;
    }
    private static void sortCoins(List<Coin> coins){
        java.util.Collections.sort(coins);
        System.out.println("After sort ");
        for (Coin coin: coins){
            System.out.print(coin + " ");
        }
    }
}
