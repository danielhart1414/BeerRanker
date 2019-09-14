//@author Daniel Hart
//Constructs a ranked list of your favorite beers and saves them
//to a text file. The better the beer, the higher it appears on the list.
//
//Fixes needed: Code to catch FileNotFoundException at line 24
//Address comment on line 47
//Develop better display

package beerranker;

import java.util.*;
import java.io.*;

public class BeerRanker {
    static String fileName;
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Beer Ranker!");
        System.out.println();
        BeerList beers = loadList(console);
        solve(console, beers);
    }
    
    private static BeerList loadList(Scanner console) throws FileNotFoundException {
        BeerList myBeers;
        String s;
        for (;;) {
            System.out.print("Have you ranked beers before? (Answer \"y\" or \"n\") ");
            s = console.nextLine();
            if (s.trim().toLowerCase().equals("y")) {
                Scanner input;
                for(;;) {
                    System.out.print("What file holds the beer that you've ranked? ");
                    fileName = console.nextLine();
                    try {
                        input = new Scanner(new File(fileName));
                        break;
                    } catch (FileNotFoundException e) {
                        System.out.println("That file does not exist.");
                    }
                }
                myBeers = new BeerList(input);
                break;
            } else if (s.trim().toLowerCase().equals("n")) {
                System.out.print("What text file would you like to store your new beer list to? ");
                fileName = console.nextLine();
                myBeers = new BeerList();
                break;
            } else {
                System.out.println("You failed to answer y or n.");
                System.out.println("Please try again.");
            }
        }
        return myBeers;
    }
	
    private static void solve(Scanner console, BeerList beers) 
            throws FileNotFoundException {
        for (;;) {
            System.out.println();
            System.out.println("Here are your beers:"); //This should only print when a beer list exists
            System.out.println();
            beers.print();
            System.out.println();
            System.out.println("What would you like to do? (type the appropriate number)");
            System.out.println("1. Add a beer");
            System.out.println("2. Remove a beer");
            System.out.println("3. Get your favorite beer");
            System.out.println("4. Quit the program");
            System.out.println();
            String s = console.nextLine();
            System.out.println();
            if (s.equals("1")) {
                System.out.print("What beer would you like to add? ");
                beers.add(console.nextLine());
            } else if (s.equals("2")) {
                System.out.print("What beer would you like to remove? (Type the name of the beer) ");
                beers.remove(console.nextLine());
            }	else if (s.equals("3")) {
                System.out.println(beers.fav());
            } else if (s.equals("4")) {
                break;
            } else {
                System.out.println("The numbers are near the top of the keyboard");
            }
        }
        beers.write(new PrintStream(new File(fileName)));
    }
}
