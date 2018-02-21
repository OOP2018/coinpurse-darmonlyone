package coinpurse;

import java.util.ResourceBundle;

public class MoneyFactoryReader {
    /** Create MoneyFactory from reading file
     * @return factory form reading
     */
    public static MoneyFactory getFactory(){
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
