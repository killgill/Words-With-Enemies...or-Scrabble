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

public class WordFinder {

   private AnagramDictionary dict;
   private ScoreTable scoreTable;

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
      scrabble.run(in,scrabble);

   }

   private String getCanon(String s) {
      char charArray[] = s.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }

   private void run(Scanner in, WordFinder scrabble){
      System.out.print("Rack? ");
      String s = in.nextLine();
      if (s.equals(".")){
         System.exit(0);
      }

      Rack rack = new Rack(s);
      ArrayList<String> allSubsets = rack.returnSubsets();
      ArrayList<String> allWords = new ArrayList<String>();

      for (int i = 0; i<allSubsets.size(); i++){
         allWords.addAll(scrabble.dict.getAnagramsOf(allSubsets.get(i)));
      }
      
      TreeMap<Integer,ArrayList<String>> scores = scrabble.scoresAndWords(allWords, scrabble);

      System.out.println(String.format("We can make %d words from \"%s\"",allWords.size(),scrabble.getCanon(s)));
      if (allWords.size()>0) {
         System.out.println("All of the words with their scores (sorted by score):");
      }

      for (Map.Entry<Integer, ArrayList<String>> curr : scores.entrySet()) {
         int temp3 = (curr.getValue()).size();
         for (int i = 0; i<temp3; i++) {
               System.out.println(curr.getKey() + ": " + (curr.getValue()).get(i));
         }
      }
      scrabble.run(in,scrabble);
   }

   private TreeMap<Integer,ArrayList<String>> scoresAndWords(ArrayList<String> allWords, WordFinder scrabble) {
      TreeMap<Integer,ArrayList<String>> scores = new TreeMap<Integer,ArrayList<String>>(Collections.reverseOrder());
      for (int i = 0; i<allWords.size(); i++){
         int temp1 = scrabble.scoreTable.getScore(allWords.get(i));
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

