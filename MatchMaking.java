/*
 *  Source program for Lab Assignment 8 for CS 1A
 *  Written by Anatolii Bogdanov, 03/02/2016
 *  
 */

import java.text.*;
import java.util.*;

public class MatchMaking {
   public static void main(String[] args) {
      // new array of applicants
      DateProfile[] applicant = {
         new DateProfile('M', 'F', 8, 3, 20, "Hugh Hefner"),
         new DateProfile('F', 'M', 8, 2, 1, "Mary Lynn Rajskub"),
         new DateProfile('M', 'F', 2, 4, 10, "Brad Pitt"),
         new DateProfile('F', 'M', 7, 5, 2, "Carmen Miranda"),
      };
      
      //loops for displaying each applicant
      for (int i = 0; i < applicant.length; i++) {
         System.out.println(applicant[i].getName());
         for (int k = 0; k < applicant.length; k++) {
            // because of incorrect displaying :( 
            NumberFormat tidy = NumberFormat.getInstance(Locale.US);
            tidy.setMaximumFractionDigits(5);
            
            System.out.println("    "
                  + applicant[k].getName() + " fit: "
                  + tidy.format(applicant[i].fitValue(applicant[k])));
         }
      }
   }
}

class DateProfile {
   private char gender;
   private char searchGender;
   private int romance;
   private int finance;
   private int distance;
   private String name;
   
   public static final int MAX_INT = 10;
   public static final int MIN_INT = 1;
   public static final int DEFAULT_INT = 1;
   public static final char DEFAULT_GENDER = 'M';
   public static final char DEFAULT_SEARCH_GENDER = 'F';
   public static final String DEFAULT_NAME = "undefined";
   public static final int MAX_LENGTH = 50;
   public static final int MIN_LENGTH = 1;
   
   //default constructor
   DateProfile() {
      setGender(DEFAULT_GENDER);
      setSearchGender(DEFAULT_SEARCH_GENDER);
      setRomance(DEFAULT_INT);
      setFinance(DEFAULT_INT);
      setDistance(DEFAULT_INT);
      setName(DEFAULT_NAME);
   }
   
   // constructor
   DateProfile(char gender, char searchGender, int romance, int finance,
         int distance, String name) {
      if (!setGender(gender))
         setGender(DEFAULT_GENDER);
      if (!setSearchGender(searchGender))
         setSearchGender(DEFAULT_SEARCH_GENDER);
      if (!setRomance(romance))
         setRomance(DEFAULT_INT);
      if (!setFinance(finance))
         setFinance(DEFAULT_INT);
      if (!setDistance(distance))
         setDistance(DEFAULT_INT);
      if (!setName(name))
         setName(DEFAULT_NAME);
   }
   
   // getters
   char getGender() { return gender; }
   char getSearchGender() { return searchGender; }
   int getRomance() { return romance; }
   int getFinance() { return finance; }
   int getDistance() { return distance; }
   String getName() { return name; }
   
   // setters with test
   boolean setGender(char gdr) {
      if (validGender(gdr)) {
         this.gender = DEFAULT_GENDER;
         return false;
      }
      this.gender = gdr;
      return true;
   }
   
   boolean setSearchGender(char srchGdr) {
      if (validGender(srchGdr)) {
         this.searchGender = DEFAULT_SEARCH_GENDER;
         return false;
      }
      this.searchGender = srchGdr;
      return true;
   }
   
   boolean setName(String name) {
      if (!validName(name)) {
         this.name = DEFAULT_NAME;
         return false;
      }
      this.name = name;
      return true;
   }
   
   boolean setRomance(int romance) {
      if (!validInteger(romance)) {
         this.romance = DEFAULT_INT;
         return false;
      }
      this.romance = romance;
      return true;
   }
   
   boolean setFinance(int finance) {
      if (!validInteger(finance)) {
         this.finance = DEFAULT_INT;
         return false;
      }
      this.finance = finance;
      return true;
   }
   
   boolean setDistance(int distance) {
      if (!validInteger(distance)) {
         this.distance = DEFAULT_INT;
         return false;
      }
      this.distance = distance;
      return true;
   }
   
   // testers for valid values
   boolean validGender(char gdr) {
      if (gdr == DEFAULT_GENDER || gdr == DEFAULT_SEARCH_GENDER)
         return false;
      return true;
   }
   
   boolean validName(String name) {
      if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH)
         return false;
      return true;
   }
   
   boolean validInteger(int integer) {
      if (integer < MIN_INT || integer > MAX_INT)
         return false;
      return true;
   }
   
   // compute fit score
   public double fitValue(DateProfile partner) {
      double finalResult;
      
      finalResult = this.determineGenderFit(partner) 
            * this.determineRomanceFit(partner)
            * this.determineFinanceFit(partner)
            * this.determineDistanceFit(partner);
      
      return finalResult;
   }
   
   // compute gender fit
   public double determineGenderFit(DateProfile partner) {
      if (this.gender == partner.searchGender 
            && this.searchGender == partner.gender)
         return 1.0;
      return 0.0;
   }
   
   // compute romance fit
   public double determineRomanceFit(DateProfile partner) {
      double subtraction;
      if (this.romance > partner.romance) {
         subtraction = this.romance - partner.romance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else if(partner.romance > this.romance) {
         subtraction = partner.romance - this.romance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else 
         return 1.0;
   }
   
   //compute finance fit
   public double determineFinanceFit(DateProfile partner) {
      double subtraction;
      if (this.finance > partner.finance) {
         subtraction = this.finance - partner.finance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else if(partner.finance > this.finance) {
         subtraction = partner.finance - this.finance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else 
         return 1.0;
   }
   
   //determine distance fit
   public double determineDistanceFit(DateProfile partner) {
      double subtraction;
      if (this.distance > partner.distance) {
         subtraction = this.distance - partner.distance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else if(partner.distance > this.distance) {
         subtraction = partner.distance - this.distance;
         subtraction = (10 - subtraction) * 0.1;
         return subtraction;
      } else 
         return 1.0;
   }
}


/* ------------------- Sample Run #1 --------------------
Hugh Hefner
    Hugh Hefner fit: 0
    Mary Lynn Rajskub fit: 0.9
    Brad Pitt fit: 0
    Carmen Miranda fit: 0.648
Mary Lynn Rajskub
    Hugh Hefner fit: 0.9
    Mary Lynn Rajskub fit: 0
    Brad Pitt fit: 0.032
    Carmen Miranda fit: 0
Brad Pitt
    Hugh Hefner fit: 0
    Mary Lynn Rajskub fit: 0.032
    Brad Pitt fit: 0
    Carmen Miranda fit: 0.09
Carmen Miranda
    Hugh Hefner fit: 0.648
    Mary Lynn Rajskub fit: 0
    Brad Pitt fit: 0.09
    Carmen Miranda fit: 0
---------------------- End Sample Run #1 ---------------- */
