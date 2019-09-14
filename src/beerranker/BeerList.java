//@author Daniel Hart
//A beer list to be used by BeerRanker
//
//Fix needed: error-catching code at line 34. Done!

package beerranker;

import java.util.*;
import java.io.*;

public class BeerList {
    private List<String> myBeers;

    public BeerList() {
        myBeers = new ArrayList<String>();
    }

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

    public boolean add(String beer, int position) {
        boolean success = position > 0 && position <= (myBeers.size() + 1);
        if (success) {
            myBeers.add(position-1, beer);
        }
        return success;
    }

    public boolean remove(String beer) {
        boolean success = myBeers.contains(beer);
        if (success) {
            myBeers.remove(beer);
        }  
        return success;
    }

    public String fav() {
        if (!myBeers.isEmpty()) {
            return myBeers.get(0) + " is your favorite beer.";
        } else {
            return "You haven't ranked any beer, laddy!";
        }
    }

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
    
    public boolean isEmpty() {
        return myBeers.isEmpty();
    }
    
    public boolean containsBeer(String beer) {
        return myBeers.contains(beer);
    }
}