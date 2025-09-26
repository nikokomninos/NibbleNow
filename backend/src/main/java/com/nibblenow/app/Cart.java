package com.nibblenow.app;

import java.util.ArrayList;

public class Cart
{
    private ArrayList<MenuItem> contents;

    public Cart()
    {
        contents = new ArrayList<MenuItem>();
    }

    public ArrayList<MenuItem> getContents()
    {
        return(contents);
    }

    public void setContents(ArrayList<MenuItem> newContents)
    {
        this.contents = newContents;
    }

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

    public void addToCart(MenuItem newContent)
    {
        this.contents.add(newContent);
    }

    public boolean isEmpty()
    {
        return(this.contents.size() == 0);
    }
}
