// Name: Karan Singh Gill
// USC NetID: karansig
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   //The rack is stored in the same form that is required for the allSubsets method for ease of use.
   private int[] rackMult;
   private String rackUnique;
   /**
   The constructor just calls the method which creates the canonical form (i.e. the form with mult and unique)
   so that the allSubsets method can later be called
   */
   public Rack(String s){
      getCanon(s);
   }
   /**
   Returns all possible subsets of the Rack
   */
   public ArrayList<String> returnSubsets(){
      return new ArrayList<String>(allSubsets(rackUnique, rackMult, 0)); //ensures that original object can't be modified
   }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
   Adapted from http://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
   This method first sorts the string in alphabetical order, and then uses a for loop 
   to construct the unique String and the mult Array
   */
   private void getCanon(String s) {
      char[] charArray = s.toCharArray();
      Arrays.sort(charArray);
      String unique = Character.toString(charArray[0]);
      int letterPosition = 0;
      int uniqueLetters = 0;
      int[] mult = new int[charArray.length];
      mult[0] = 1;
      for(int i = 1; i<charArray.length; i++) {
         if (charArray[i] == charArray[letterPosition]) {
            mult[uniqueLetters]++;
         }
         else {
            unique += Character.toString(charArray[i]);
            letterPosition = i;
            uniqueLetters++;
            mult[uniqueLetters]++;
         }
      }
      rackUnique = unique;
      rackMult = mult;
   }
}
