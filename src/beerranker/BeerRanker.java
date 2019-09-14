//@author Daniel Hart
//Constructs a ranked list of your favorite beers and saves them
//to a text file. The better the beer, the higher it appears on the list.
//Requires the BeerList class.

package beerranker;

import java.util.*;
import java.io.*;

public class BeerRanker {
    static String fileName; //This is the name of the file the beer list is stored in.
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Beer Ranker!");
        System.out.println();
        BeerList beers = loadList(console);
        primaryFunctions(console, beers);
        PrintStream storage = new PrintStream(new File(fileName));
        storage.print(beers.toString());
    }
    
    //Generates and returns either a BeerList from a file or a new BeerList
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
    
    //Contains the apps five primary functions
    private static void primaryFunctions(Scanner console, BeerList beers) 
            throws FileNotFoundException {
        String choice;
        for (;;) {
            showBeerList(beers);
            showMenu();
            System.out.println();
            choice = console.nextLine();
            System.out.println();
            if (choice.trim().equals("5")) { //quit the app
                break;
            } else {
                switch (choice.trim()) {
                    case "1":   //add a beer to the list
                        System.out.print("What beer would you like to add? Please type the name. ");
                        String beerAddition = console.nextLine();
                        if (!beers.containsBeer(beerAddition)) {
                            addBeer(console, beerAddition, beers);
                            System.out.println("Beer added!");
                        } else {
                            System.out.println(beerAddition + " is already on your list.");
                        }
                        break;
                    case "2":   //remove a beer from the list
                        System.out.print("What beer would you like to remove? Type the name of the beer. ");
                        if (beers.remove(console.nextLine())) {
                            System.out.println("Beer removed!");
                        } else {
                            System.out.println("That's not on the list, kiddo.");
                        }
                        break;
                    case "3":   //revise a beer that is on the list
                        System.out.println("Which beer name would you like to edit?");
                        System.out.print("Please type the number that is beside the name. ");
                        try {
                            int position = console.nextInt();
                            if (position > 0 && position <= beers.size()) {
                                console.nextLine();
                                System.out.println();
                                System.out.println("What would you like to call that beer?");
                                beers.reviseEntry(position, console.nextLine());
                            } else {
                                throw new InputMismatchException("Value out of range");
                            }
                        } catch(InputMismatchException e) {
                            console.nextLine();
                            System.out.println("Please type one of the numbers on your list.");
                        }
                        break;
                    case "4":   //print favorite beer
                        System.out.println(beers.fav());
                        break;
                    default:    //error message for invalid input
                        System.out.println("The numbers are near the top of the keyboard.");
                }
            }
        }
    }
    
    //Displays the BeerList for the user
    private static void showBeerList(BeerList beers) {
        if (!beers.isEmpty()) {
            System.out.println();
            System.out.println("Here are your beers:");
        }
        System.out.println();
        System.out.println(beers.numberedToString());
    }
    
    //Prints out the main app menu
    private static void showMenu() {
        System.out.println();
        System.out.println("What would you like to do? (type the appropriate number)");
        System.out.println("1. Add a beer");
        System.out.println("2. Remove a beer");
        System.out.println("3. Edit a beer name");
        System.out.println("4. Show your favorite beer");
        System.out.println("5. Quit the program");
    }
    
    //Adds a beer to the BeerList
    private static void addBeer(Scanner console, String beerName, BeerList beers) {
        int position;
        for (;;) {   
            try {
                System.out.println("Where on your list would you like the beer to appear?");
                System.out.print("Please type the position number. ");
                position = console.nextInt();
                if (beers.add(beerName, position)) {
                    console.nextLine();
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