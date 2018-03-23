package coinpurse;

import coinpurse.moneyfactory.MoneyFactory;
import coinpurse.moneyfactory.MoneyFactoryReader;
import coinpurse.strategy.GreedyStrategy;

/**
 * A main class to create objects and connect objects together.
 * The user interface needs a reference to coin purse.
 * @author Manusporn Fukkham
 */
public class Main {

    /** Create MoneyFactory from reading file*/
    public static void init(){
        MoneyFactory factory = MoneyFactoryReader.getFactory();
        MoneyFactory.setFactory(factory);
    }
    /**
     * Configure and start the application.
     * @param args not used
     */
    public static void main( String[] args ) {
        init();
        // 1. create a Purse
    	Purse purse = new Purse(10);
    	// set withdraw strategy
    	purse.setWithdrawStrategy(new GreedyStrategy());
        // 2. create a ConsoleDialog with a reference to the Purse object
    	ConsoleDialog ui = new ConsoleDialog(purse);
        // 3. run the ConsoleDialog
    	ui.run();


    }
}
