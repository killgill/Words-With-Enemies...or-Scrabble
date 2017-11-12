// Name: Karan Singh Gill
// USC NetID: karansig
// CS 455 PA4
// Fall 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   //stores the filename and the HashMap
   private File inFile;
   private HashMap<String,ArrayList<String>> dict;
   


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
          System.exit(0); //exits if the file is not found since the file is an argument not an input
      }
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * This method first converts the string to the canonical form
    * that is used for keys and then checks if that key exists in the 
    * dict HashMap. If it does, it returns the applicable ArrayList
    */
   public ArrayList<String> getAnagramsOf(String s) {
      String canon = getCanon(s);
      if (dict.containsKey(canon)){
         return new ArrayList<String>(dict.get(canon)); //ensures the actual dict can't be modified
      }
      return new ArrayList<String>(); //returns empty ArrayList if the key doesn't exist
   }
   /**
   This class creates the HashMap that contains all possible anagrams.
   Every word in the dictionary is first converted to its canonical form
   (suggested in the PA4 assignment) of characters in alphabetical order.
   This form is used as the key in the HashMap. If the key already exists,
   the corresponding ArrayList is called and the current word added to it.
   If the key doesn't exit, a new arraylist with that word is created for
   that key. Finally the HashMap is returned
   */
   private HashMap<String,ArrayList<String>> createDict(Scanner in){
      HashMap<String,ArrayList<String>> temp = new HashMap<String,ArrayList<String>>();
      String word;
      String canon;
      while(in.hasNext()) {
         word = in.next();
         canon = getCanon(word);
         ArrayList<String> temp2 = new ArrayList<String>();
         if (!temp.containsKey(canon)){
            temp2.add(word);
            temp.put(canon,temp2);
         }
         else {
            temp2 = temp.get(canon);
            temp2.add(word);
            temp.put(canon,temp2);
         }
      }
      return temp;
   }

   /**
   This code was adapted from http://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/ 
   It takes a string and returns the string sorted in alphabetical order
   */
   private String getCanon(String s) {
      char charArray[] = s.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }
   
}
