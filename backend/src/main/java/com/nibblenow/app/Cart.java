package com.nibblenow.app;

import java.util.ArrayList;


/**
 * Cart:
 * 
 * An object that represents a cart for a user.
 * The MenuItems are stored in an ArrayList called contents.
 * This class contains methods that act on the contents ArrayList.
 * 
 * @author Nikolaos Komninos
 */
public class Cart
{
    // ArrayList to store the MenuItem's the user has in their cart.
    private ArrayList<MenuItem> contents;

    /**
     * Default constructor for Cart class
     */
    public Cart()
    {
        contents = new ArrayList<MenuItem>();
    }

    /**
     * Returns the contents of the cart as an ArrayList of MenuItem's
     * @return an ArrayList of Menu Item's representing the contents of the cart.
     */
    public ArrayList<MenuItem> getContents()
    {
        return(contents);
    }

    /**
     * Sets the contents of the cart given an ArrayList of MenuItem's
     * @param newContents this is an ArrayList of Menu Item's that will replace the
     * current contents in this cart.
     */
    public void setContents(ArrayList<MenuItem> newContents)
    {
        this.contents = newContents;
    }

    /** Checks to see if the carts are equal by iterating through the items in contents
     *  and uses the custom equals method that was written for the MenuItem.
     * @param cart_p represents the cart we are comparing
     * @return True if the two carts are the same, false otherwise.
     */ 
    public boolean isEqual(Cart cart_p)
    {
        ArrayList<MenuItem> passedCartContents = cart_p.getContents();
        boolean equal = false;

        for(int i = 0; i < passedCartContents.size(); i++)
        {
            equal = passedCartContents.get(i).equals(this.contents.get(i));
        }

        return(equal);
    }

    /**
     * This function will add a MenuItem to the cart or the contents ArrayList.
     * @param newContent this is the MenuItem that should be added to the cart.
     */
    public void addToCart(MenuItem newContent)
    {
        this.contents.add(newContent);
    }

    /**
     * Removes an item from a user's cart
     * @param item the item being removed from the cart
     * @return the removed item
     */
    public MenuItem removeFromCart(MenuItem item)
    {
        MenuItem mi = null;
        for (int i = 0; i < contents.size(); i++)
        {
            if (contents.get(i).equals(item))
            {
                mi = contents.remove(i);
                break;
            }
        }
        return mi;
    }

    /**
     * This function will check to see if the cart is empty.
     * @return True if the cart is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return(this.contents.size() == 0);
    }
}
