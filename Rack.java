// Name: 
// USC NetID: 
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   private int[] rackMult;
   private String rackUnique;

   public Rack(String s){
      getCanon(s);
   }
   public ArrayList<String> returnSubsets(){
      return allSubsets(rackUnique, rackMult, 0);
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
   adapted from http://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/ 
   */
//   private void takeString(String s){
//      TreeSet<char> charSet = new Treeset<char>();
//      char[] charArray = s.toCharArray();
//      for (int i = 0; i<charArray.length; i++){
//	 charSet.add(charArray[i]);
//      }
//      int[] mult = new int[charSet.size()];
//      for (int i = 0; i<charSet.size(); i++){
//	 for (int j = 0; i<charArray.length; j++){
//	    if 
//   }

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
      System.out.println(unique);
   }
}
