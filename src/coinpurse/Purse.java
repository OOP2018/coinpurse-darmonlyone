package coinpurse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
// You will use Collections.sort() to sort the Valuable

/**
 *  A Valuable purse contains money.
 *  You can insert coins and banknote, withdraw money, check the balance,
 *  and check if the purse is full.
 *  
 *  @author Manusporn Fukkham
 */
public class Purse {
    /**Comparator of valuable**/
    private Comparator<Valuable> comp = new ValueComparator();
    /** Collection of objects in the purse. */
    private List<Valuable> money;
    /** Capacity is maximum number of items the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;
    
    /** 
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of money you can put in purse.
     */
    public Purse( int capacity ) {
        money = new ArrayList<Valuable>();
        this.capacity = capacity;
    }

    /**
     * Count and return the number of money in the purse.
     * This is the number of money, not their value.
     * @return the number of money in the purse
     */
    public int count() { return money.size(); }
    
    /** 
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
        double balance = 0;
        for (Valuable monies : money){
            balance += monies.getValue();
        }
        return balance;
	}

    
    /**
     * Return the capacity of the money in purse.
     * @return the capacitym
     */
    public int getCapacity() { 
		return capacity;
	}
    
    /** 
     *  Test whether the purse is full.
     *  The purse is full if number of items in purse equals
     *  or greater than the purse capacity.
     *  @return true if purse is full.
     */
    public boolean isFull() {
        return money.size() == capacity;
    }

    /** 
     * Insert a money into the purse.
     * The money is only inserted if the purse has space for it
     * and the money has positive value.  No worthless money!
     * @param valuable is a valuable object to insert into purse
     * @return true if valuable inserted, false if can't insert
     */
    public boolean insert( Valuable valuable ) {
        // if the purse is already full then can't insert anything.
        if (money.size() < capacity && valuable.getValue()>0) {
            money.add(valuable);
            return true;
        }
        else return false;
    }
    
    /**  
     *  Withdraw the requested amount of money.
     *  Return an array of valuable withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of valuable objects for money withdrawn,
	 *    or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw( double amount ) {
        if(amount < 0 )return null;
		// This code assumes you decrease amount each time you remove a coin and bank note.
	    // This code assumes you decrease amount each time you remove a coin and bank note.
    	// Your code might use some other variable for the remaining amount to withdraw.
        money.sort(comp);
        List<Valuable> withDraw = new ArrayList<Valuable>();
        double amountNeededToWithdraw = amount;
        for (int i = money.size()-1 ; i >= 0 ; i--) {
            if (amountNeededToWithdraw != 0) {
                if ((amountNeededToWithdraw - money.get(i).getValue() >= 0)){
                    amountNeededToWithdraw -= money.get(i).getValue();
                    withDraw.add(money.get(i));
                }
            }
        }
        //removing withDraw money form purse
        for (Valuable valuable : withDraw){
            money.remove(valuable);
        }
		// Remove the valuable you want to withdraw from purse,
		// and return them as an array.
		// Use list.toArray( array[] ) to copy a list into an array.
		// toArray returns a reference to the array itself.
        Valuable[] moneyArray = new Valuable[withDraw.size()];
        if (amountNeededToWithdraw > 0 || withDraw.isEmpty()){
            money.addAll(withDraw);
            return null;
        }
        return withDraw.toArray(moneyArray);
	}
  
    /** 
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    @Override
    public String toString() {
        int valuableHave = 0;
        String str = "Purse have ";
        for (Valuable valuable : money){
            valuableHave++;
            str = str.concat(valuable.toString() + " ");
        }
    	return String.format("%s, %d valuable with value %.2f ",str,valuableHave,getBalance());
    }

}
