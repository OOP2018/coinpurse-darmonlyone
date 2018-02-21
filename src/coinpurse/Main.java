package coinpurse;

import java.util.ResourceBundle;

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
        // 2. create a ConsoleDialog with a reference to the Purse object
    	ConsoleDialog ui = new ConsoleDialog(purse);
        // 3. run the ConsoleDialog
    	ui.run();

    }
}
