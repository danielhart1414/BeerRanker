//@author Daniel Hart
//A beer list to be used by BeerRanker

package beerranker;

import java.util.*;
import java.io.*;

public class BeerList {
    private List<String> myBeers;   //stores beer preferences

    //Used when the user ranks for the first time
    public BeerList() {
        myBeers = new ArrayList<String>();
    }
    
    //Used to generate BeerList when the user inputs a file holding data from a
    //previous ranking session
    public BeerList(Scanner input) {
        this();
        if (input.hasNextLine()) {
            String firstBeer = input.nextLine();
            if (!firstBeer.equals("You've ranked no beer :(")) {
                myBeers.add(firstBeer);
                while (input.hasNextLine()) {
                    myBeers.add(input.nextLine());
                }
            }
        }
    }

    //Adds a beer to the list; returns whether the operation succeeded or failed.
    //Failure occurs when the user inputs an invalid position.
    public boolean add(String beer, int position) {
        boolean success = position > 0 && position <= (myBeers.size() + 1);
        if (success) {
            myBeers.add(position-1, beer);
        }
        return success;
    }

    //Removes a beer from the list; returns whether the operation succeeded or
    //failed. Failure occurs if the beer is not on the list and thus cannot be
    //removed.
    public boolean remove(String beer) {
        boolean success = myBeers.contains(beer);
        if (success) {
            myBeers.remove(beer);
        }  
        return success;
    }

    //Returns the number 1 beer on the list; if there is no beer on the list,
    //returns a message stating so.
    public String fav() {
        if (!myBeers.isEmpty()) {
            return myBeers.get(0) + " is your favorite beer.";
        } else {
            return "You haven't ranked any beer, laddy!";
        }
    }

    //Returns the list as a string
    public String toString() {
        String result;
        if (!myBeers.isEmpty()) {
            result = myBeers.get(0);
            if (myBeers.size() > 1) {
                for (int i = 1; i < myBeers.size(); i++) {
                    result += System.lineSeparator() + myBeers.get(i);
                }
            }
        } else {
            result = "You've ranked no beer :(";
        }
        return result;
    }

    //Returns the list as a string; includes rank number beside each beer
    public String numberedToString() {
        String result;
        if (!myBeers.isEmpty()) {
            result = "1. " + myBeers.get(0);
            if (myBeers.size() > 1) {
                for (int i = 1; i < myBeers.size(); i++) {
                    result += System.lineSeparator() + (i + 1) + ". " + myBeers.get(i);
                }
            }
        } else {
            result = "You've ranked no beer :(";
        }
        return result;
    }
    
    //Returns whether the list is empty
    public boolean isEmpty() {
        return myBeers.isEmpty();
    }
    
    //Returns whether the list contains a given beer
    public boolean containsBeer(String beer) {
        return myBeers.contains(beer);
    }
    
    //Returns list size
    public int size() {
        return myBeers.size();
    }
    
    //If the position is valid, replaces a list entry with newName
    public void reviseEntry(int position, String newName) {
        if (position > 0 && position <= myBeers.size()) {
            myBeers.set(position-1, newName);
        }
    }
}