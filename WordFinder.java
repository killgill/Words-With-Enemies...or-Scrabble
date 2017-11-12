// Name: Karan Singh Gill
// USC NetID: karansig
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Collections;
/**
This class handles putting everything together from the other classes.
It prompts the user for a rack, finds all possible words and their scores,
and then lists them out for the user before prompting for another rack.
*/
public class WordFinder {

   private AnagramDictionary dict;
   private ScoreTable scoreTable;
   /**
   The main method's task is to create a WordFinder object called scrabble, 
   ensure that scrabble has an operating AnagramDictionary and scoreTable, 
   and creates a Scanner for System.in. It also tells the user to type . to quit.
   It also checks to see where the dictionary should be generated from through its 
   arguments.
   */
   public static void main(String[] args) throws FileNotFoundException {
      WordFinder scrabble = new WordFinder();
      String filename = "sowpods.txt";
      if (args.length > 0) {
         filename = args[0];
      }

      scrabble.dict = new AnagramDictionary(filename);
      scrabble.scoreTable = new ScoreTable();
      System.out.println("Type . to quit.");
      Scanner in = new Scanner(System.in);
      scrabble.run(in);

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
   /**
   Run is essentially the method that continuously loops until the user terminates the program.
   It prompts for a Rack, creates the Rack, and returns all subsets of the rack.
   It then finds all possible anagrams from the dictionary from all the subsets.
   It then calls the scoresAndWords method which creates a TreeMap that sorts the words by decreasing score.
   It then prints how many words can be made and the generic sorted by score message.
   Finally, it prints every possible word with its score in descending order of scores and then alphabetical order.
   To complete the loop, it calls itself again with the same Scanner.
   */
   private void run(Scanner in){
      System.out.print("Rack? ");
      String s = in.nextLine();
      if (s.equals(".")){
         System.exit(0);
      }

      Rack rack = new Rack(s);
      ArrayList<String> allSubsets = rack.returnSubsets();
      ArrayList<String> allWords = new ArrayList<String>();

      for (int i = 0; i<allSubsets.size(); i++){
         allWords.addAll(dict.getAnagramsOf(allSubsets.get(i)));
      }
      
      TreeMap<Integer,ArrayList<String>> scores = this.scoresAndWords(allWords);

      System.out.println(String.format("We can make %d words from \"%s\"",allWords.size(),getCanon(s)));
      if (allWords.size()>0) {
         System.out.println("All of the words with their scores (sorted by score):");
      }

      for (Map.Entry<Integer, ArrayList<String>> curr : scores.entrySet()) {
         int temp3 = (curr.getValue()).size();
         for (int i = 0; i<temp3; i++) {
               System.out.println(curr.getKey() + ": " + (curr.getValue()).get(i));
         }
      }
      run(in);
   }

   /**
   The scoresAndWords method takes the ArrayList of all possible words from the dictionary
   It then creates a TreeMap with Integers as keys and arraylists of strings as values.
   This TreeMap is sorted in reverse order so that the highest scores are output first.
   For every word, the score is checked, and used as a key. If the key doesn't already exist,
   a new ArrayList with that word only is created. If the key does exist, the current ArrayList
   associated with it has the new word added to it, and is then sorted alphabetically. The alphabetical
   sort ensures that when multiple words have the same score, they're printed in alphabetical order.
   */
   private TreeMap<Integer,ArrayList<String>> scoresAndWords(ArrayList<String> allWords) {
      TreeMap<Integer,ArrayList<String>> scores = new TreeMap<Integer,ArrayList<String>>(Collections.reverseOrder());
      for (int i = 0; i<allWords.size(); i++){
         int temp1 = scoreTable.getScore(allWords.get(i));
         if (!scores.containsKey(temp1)) {
            ArrayList<String> temp2 = new ArrayList<String>();
            temp2.add(allWords.get(i));
            scores.put(temp1, temp2);
         }
         else {
            ArrayList<String> temp2 = scores.get(temp1);
            temp2.add(allWords.get(i));
            Collections.sort(temp2);
            scores.put(temp1,temp2);
         }
      }
      return scores;
   }
}

