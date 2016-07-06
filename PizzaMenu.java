/*
 *  Source program for Lab Assignment 9 for CS 1A
 *  Written by Anatolii Bogdanov, 03/09/2016
 *  
 */

import java.text.*;
import java.util.*;

public class PizzaMenu {
   public static void main(String[] args) {
      // some variable. without them program doesn't work(
     Scanner reader = new Scanner(System.in);
     boolean exit = false;
     boolean quit = false;
     int userInteger;
     char firstChar;
     String numOfTopping;
     
     // outer loop
     while(!exit) {
        displayMainMenu();
        
        // each time create 
        PizzaOrder newPizza = new PizzaOrder();
        
        firstChar = getSizeFromUser();
        
        // setting size depends on first letter
        if (firstChar == 'Q') {
           exit = true;
           break;
        } else if (firstChar == 'S')
           newPizza.setSize(0);
        else if (firstChar == 'M')
           newPizza.setSize(1);
        else if (firstChar == 'L')
           newPizza.setSize(2);
        else
           newPizza.setSize(3);
        
        
        if (firstChar == 'S' || firstChar == 'M' || firstChar == 'L'
              || firstChar == 'D' ) {
           // inner loop
           while(!quit) {
              // exiting inner loop if toppings is full
              if (newPizza.toppingsIsFull()) {
                 System.out.println("\n" + newPizza.getPrice());
                 break;
              }
              displayIngridients();
              
              userInteger = Integer.parseInt(reader.nextLine());
              
              // exit when input is zero
              if (userInteger == 0) {
                 newPizza.displayPizza(newPizza.getSize());
                 
                 System.out.println("\n" + newPizza.getPrice());
                 break;
              } else {
                 numOfTopping = PizzaOrder.TOPPINGS_OFFERED[userInteger - 1];
                 if(!newPizza.addTopping(numOfTopping)) {
                    quit = true;
                    break;
                 }
                 newPizza.displayPizza(newPizza.getSize());
              }
           }
        }
      }
     
   }

   static char getSizeFromUser() {
      Scanner reader = new Scanner(System.in);
      String userInput;
      char userChar;
      userInput = reader.nextLine().toUpperCase();
      userChar = userInput.charAt(0);
      return userChar;
   }
   
   static void displayMainMenu() {
      System.out.print("\nSize of pizza ('s', 'm', 'l', 'd') or 'q'"
            + " to quit: ");
   }
   
   static void displayIngridients() {
      System.out.print("\nSelect an item by number (0 when done): \n"
            + "   1. onions   \n   2. bell peppers   \n"
            + "   3. olives   \n   4. pepperoni   \n   "
            + "Selection: ");
   }
}

class PizzaOrder {
   public static final String[] TOPPINGS_OFFERED = { "onions", "bell peppers",
         "olives", "pepperoni" };
   public static final double[] TOPPING_BASE_COST = { 0.99, 2.99, 1.29, 2.29 } ;
   public static final double BASE_PRICE = 15;
   
   private int size;
   private final int MAX_TOPPINGS = 10;
   private String[] toppings = new String[MAX_TOPPINGS];
   private int numToppings = 0;
   
   private static final int DEFAULT_SIZE = 0;
   
   // default constructor
   PizzaOrder() {
      setSize(0);
   }
   
   // constructor 1 param
   PizzaOrder(int size) {
      this.size = size;
   }
   
   boolean toppingsIsFull() {
      if (numToppings == 10)
         return true;
      return false;
   }
   
   boolean setSize(int size) {
      if(!validSize(size))
         return false;
      this.size = size;
      return true;
   }
   
   boolean addTopping(String topping) {
      for (int i = 0; i <= MAX_TOPPINGS; i++) {
         if (numToppings == 10) {
            return false;
         }
         if (toppings[i] == null) {
            toppings[i] = topping;
            numToppings++;
            break;
         }
      }
      return true;
   }
   
   boolean addTopping(int n) {
      for (int i = 0; i <= MAX_TOPPINGS; i++) {
         if (numToppings == 10) {
            return false;
         }
         if (toppings[i] == null) {
            toppings[i] = TOPPINGS_OFFERED[n];
            numToppings++;
            break;
         }
      }
      return true;
   }
   
   double getPrice() {
      double price, sumOfToppings;
      sumOfToppings = 0;
      for (int i = 0; i < numToppings; i++)
         for (int k = 0; k < TOPPINGS_OFFERED.length; k++)
            if (toppings[i] == TOPPINGS_OFFERED[k])
               sumOfToppings += TOPPING_BASE_COST[k];
      price = (BASE_PRICE + sumOfToppings) * calculatePrecent();
      return price;
   }
   
   int getSize() {
      return size;
   }
   
   String stringizeSize(int size) {
      if (size == 0)
         return "small";
      if (size == 1)
         return "medium";
      if (size == 2)
         return "large";
      else
         return "super-duper";
   }
   
   double calculatePrecent () {
      if (size == 0)
         return 1;
      if (size == 1)
         return 1.15;
      if (size == 2)
         return 1.25;
      else
         return 1.35;
   }
   
   boolean validSize(int size) {
      if (size == 0 || size == 1 || size == 2 || size == 3)
         return true;
      return false;
   }
   
   public void displayPizza(int size) {
      System.out.print("\nCurrent pizza: " + stringizeSize(size)
      + getToppings());
   }
   
   public void resetToppings() {
      for (int i = 0 ; i < numToppings; i++) {
         toppings[i] = null;
      }
   }
   
   String getToppings() {
      String returnString = "";
      for (int i = 0; i < numToppings; i++) {
         returnString += " + " + toppings[i];
      }
      return returnString;
   }
}


/* ------------------- Sample Run #1 --------------------

Size of pizza ('s', 'm', 'l', 'd') or 'q' to quit: m

Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 1

Current pizza: medium + onions
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 2

Current pizza: medium + onions + bell peppers
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 3

Current pizza: medium + onions + bell peppers + olives
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 4

Current pizza: medium + onions + bell peppers + olives + pepperoni
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 1

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 2

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions + be
ll peppers
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 3

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions + be
ll peppers + olives
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 4

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions + be
ll peppers + olives + pepperoni
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 1

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions + be
ll peppers + olives + pepperoni + onions
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 2

Current pizza: medium + onions + bell peppers + olives + pepperoni + onions + be
ll peppers + olives + pepperoni + onions + bell peppers
39.214999999999996

Size of pizza ('s', 'm', 'l', 'd') or 'q' to quit: d

Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 1

Current pizza: super-duper + onions
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 2

Current pizza: super-duper + onions + bell peppers
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 3

Current pizza: super-duper + onions + bell peppers + olives
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 1

Current pizza: super-duper + onions + bell peppers + olives + onions
Select an item by number (0 when done): 
   1. onions   
   2. bell peppers   
   3. olives   
   4. pepperoni   
   Selection: 0

Current pizza: super-duper + onions + bell peppers + olives + onions
28.701000000000004

Size of pizza ('s', 'm', 'l', 'd') or 'q' to quit: q

---------------------- End Sample Run #1 ---------------- */
