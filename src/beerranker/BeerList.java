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

    public void add(String beer) {
        if (myBeers.contains(beer)) {
            System.out.println("It's already on the list.");
        } else if (!myBeers.isEmpty()) {
            Scanner console = new Scanner(System.in);
            int n;
            for (;;) {
                System.out.println("Please type the number of the beer it's better than");
                System.out.print("(\"0\" if it's the worst). ");
                n = console.nextInt(); //A non-integer makes the program crash
                //Add code to handle the exception
                if (n == 0) {
                    myBeers.add(beer);
                    break;
                } else if (n > 0 && n <= myBeers.size()) {
                    myBeers.add(n-1, beer);
                    break;
                } else {
                    System.out.println("Does not compute.");
                }
                System.out.println("I'd like an integer please.");
            }
        } else {
            myBeers.add(beer);
        }
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
}