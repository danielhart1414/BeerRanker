//@author Daniel Hart
//A beer list to be used by BeerRanker
//
//Fix needed: error-catching code at line 34.

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
            if (!firstBeer.equals("You have no beer :(")) {
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

    public void write(PrintStream output) {
        if (myBeers.size() != 0) {
            for (int i = 0; i < myBeers.size(); i++) {
                output.println(myBeers.get(i));
            }
        } else {
            output.println("You have no beer :(");
        }
    }

    public void print() {
        if (myBeers.size() != 0) {
            for (int i = 0; i < myBeers.size(); i++) {
                System.out.println((i + 1) + ". " + myBeers.get(i));
            }
        } else {
            System.out.println("You have no beer :("); //Remove code on line 64 primaryFunctions
            //and revise showBeerList for when it's empty.
        }
    }
    
    public boolean isEmpty() {
        return myBeers.isEmpty();
    }
    
    public boolean containsBeer(String beer) {
        return myBeers.contains(beer);
    }
}