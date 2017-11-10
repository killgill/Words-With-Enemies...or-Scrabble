// Name: 
// USC NetID: 
// CS 455 PA4
// Fall 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   private File file;
   private Map<String,LinkedList<String>> dict;
   


   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      inFile = new File(fileName);
      try (Scanner in = new Scanner(inFile)) {
	 dict = createDict(in);
      }
      catch (FileNotFoundException exception) {
	 System.out.println("File not found: " + exception.getMessage());
      }
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       String canon = getCanon(s);
       if (dict.containsKey(canon)){
	  return dict.get(canon);
       }
       return new ArrayList<String>(); // DUMMY CODE TO GET IT TO COMPILE
   }

   private Map<String,LinkedList<String>> createDict(Scanner in){
      Map<String,LinkedList<String>> temp = new Map<String,LinkedList<String>>;
      String word = new String;
      String canon = new String;
      while(in.hasNextLine) {
	 word = in.nextLine();
	 canon = getCanon(word);
	 if (!temp.containsKey(canon)){
	    LinkedList<String> temp2 = new LinkedList<String>;
	    temp2.add(word);
	    temp.put(canon,temp2);
	 }
	 else {
	    LinkedList<String> temp2 = new LinkedList<String>;
	    temp2 = temp.get(canon);
	    temp2.add(word);
	    temp.put(canon,temp2);
	 }
      }
      return temp;
   }

   /**
   adapted from http://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/ 
   */
   private String getCanon(String s) {
      char charArray[] = s.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }


   
   
}
