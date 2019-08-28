//@author Daniel Hart
//Constructs a beer list to be used by BeerRanker
//
//Fix needed: error-catching code at line 34.

package beerranker;

import java.util.*;
import java.io.*;

public class BeerList {
    private List<String> beerList;

    public BeerList() {
        beerList = new ArrayList<String>();
    }

    public BeerList(Scanner input) {
        this();
        while (input.hasNextLine()) {
            beerList.add(input.nextLine());
        }
    }

    public void addBeer(String beer) {
        if (beerList.contains(beer)) {
            System.out.println("It's already on the list.");
        } else if (!beerList.isEmpty()) {
            Scanner console = new Scanner(System.in);
            int n = 0;
            for (;;) {
                System.out.println("Please type the number of the beer it's better than");
                System.out.print("(\"0\" if it's the worst). ");
                n = console.nextInt(); //A non-integer makes the program crash
                //Add code to handle the exception
                if (n == 0) {
                    beerList.add(beer);
                    break;
                } else if (n > 0 && n <= beerList.size()) {
                    beerList.add(n-1, beer);
                    break;
                } else {
                    System.out.println("Does not compute.");
                }
                System.out.println("I'd like an integer please.");
            }
        } else {
            beerList.add(beer);
        }
    }

    public void removeBeer(String beer) {
            if (beerList.contains(beer)) {
                    beerList.remove(beer);
            } else {
                    System.out.println("It's not on here, kiddo");
            }
    }

    public String fav() {
            if (!beerList.isEmpty()) {
                    return beerList.get(0);
            } else {
                    return "You don't have any beer, laddy!";
            }
    }

    public void write(PrintStream output) {
            if (beerList.size() != 0) {
                    for (int i = 0; i < beerList.size(); i++) {
                            output.println(beerList.get(i));
                    }
            } else {
                    output.println("You have no beer :(");
            }
    }

    public void print() {
            if (beerList.size() != 0) {
                    for (int i = 0; i < beerList.size(); i++) {
                            System.out.println((i + 1) + ". " + beerList.get(i));
                    }
            } else {
                    System.out.println("You have no beer :(");
            }
    }
}