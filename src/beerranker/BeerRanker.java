//@author Daniel Hart
//Constructs a ranked list of your favorite beers and saves them
//to a text file. The better the beer, the higher it appears on the list.
//
//Fixes needed: Code to catch FileNotFoundException at line 24=====DONE
//Address comment on line 47====DONE!
//Develop better display
//Line 77 option 3 needs correct functionality

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
        primaryFunctions(console, beers);
        beers.write(new PrintStream(new File(fileName)));
    }
    
    private static BeerList loadList(Scanner console) throws FileNotFoundException {
        BeerList myBeers;
        String rankedBefore;
        for (;;) {
            System.out.print("Have you ranked beers before? (Answer \"y\" or \"n\") ");
            rankedBefore = console.nextLine();
            if (rankedBefore.trim().toLowerCase().equals("y")) {
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
            } else if (rankedBefore.trim().toLowerCase().equals("n")) {
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
	
    private static void primaryFunctions(Scanner console, BeerList beers) 
            throws FileNotFoundException {
        String choice;
        for (;;) {
            if (!beers.isEmpty()) {
                showBeerList(beers);
            }
            showMenu();
            System.out.println();
            choice = console.nextLine();
            System.out.println();
            if (choice.trim().equals("1")) {
                System.out.print("What beer would you like to add? Please type the name. ");
                String beerAddition = console.nextLine();
                if (!beers.containsBeer(beerAddition)) {
                    addBeer(console, beerAddition, beers);
                    System.out.println("Beer added!");
                } else {
                    System.out.println(beerAddition + " is already on your list.");
                }
            } else if (choice.trim().equals("2")) {
                System.out.print("What beer would you like to remove? Type the name of the beer. ");
                if (beers.remove(console.nextLine())) {
                    System.out.println("Beer removed!");
                } else {
                    System.out.println("That's not on the list, kiddo.");
                }
            }	else if (choice.trim().equals("3")) {
                System.out.println(beers.fav()); //This is not correct
            }	else if (choice.trim().equals("4")) {
                System.out.println(beers.fav());
            } else if (choice.trim().equals("5")) {
                break;
            } else {
                System.out.println("The numbers are near the top of the keyboard");
            }
        }
    }
    
    private static void showBeerList(BeerList beers) {
        System.out.println();
        System.out.println("Here are your beers:");
        System.out.println();
        beers.print();
    }
    
    private static void showMenu() {
        System.out.println();
        System.out.println("What would you like to do? (type the appropriate number)");
        System.out.println("1. Add a beer");
        System.out.println("2. Remove a beer");
        System.out.println("3. Edit a beer name");
        System.out.println("4. Show your favorite beer");
        System.out.println("5. Quit the program");
    }
    
    private static void addBeer(Scanner console, String beerName, BeerList beers) {
        int position;
        for (;;) {   
            try {
                System.out.println("Where on your list would you like the beer to appear?");
                System.out.print("Please type the position number. ");
                position = console.nextInt();
                if (beers.add(beerName, position)) {
                    break;
                } else {
                    System.out.println("Please type a number that is already on your list"
                            + " or one more than the last item on your list.");
                }
            } catch (InputMismatchException e) {
                System.out.println("I'd like an integer please.");
                console.nextLine();
            }
        }
    }
}