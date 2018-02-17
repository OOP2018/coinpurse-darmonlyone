package coinpurse;

import java.util.ResourceBundle;

/**
 * Money Factory read file
 * @author Mansuporn Fukkham
 */
public class ReadFile {
    public static MoneyFactory readFile(){
        ResourceBundle bundle = ResourceBundle.getBundle( "purse" );
        String factorials = bundle.getString( "moneyfactory" );
        MoneyFactory factory = null;
        try {
            factory = (MoneyFactory)Class.forName(factorials).newInstance();
        }
        catch (ClassCastException cce) {
            //the object could not be cast to type MoneyFactory
            System.out.println(factorials+" is not type MoneyFactory");
        }
        catch (Exception ex) {
            // any other exception means we could not create an object
            System.out.println("Error creating MoneyFactory "+ex.getMessage() );
        }
        // if no factory then quit
        if (factory == null) System.exit(1);
        return factory;
    }
}
