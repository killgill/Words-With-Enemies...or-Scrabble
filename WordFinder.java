import java.util.ArrayList;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Collections;

public class WordFinder {
   private static AnagramDictionary dict;
   private static ScoreTable scoreTable;
   public static void main(String[] args) throws FileNotFoundException {
      String filename = "sowpods.txt";
      if (args.length > 0) {
	 filename = args[0];
      }

      dict = new AnagramDictionary(filename);
      scoreTable = new ScoreTable();
      System.out.println("Type . to quit");
      Scanner in = new Scanner(System.in);
      run(in);

   }

   private static void run(Scanner in){
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
      
      TreeMap<Integer,ArrayList<String>> scores = new TreeMap<Integer,ArrayList<String>>();
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
      for (Map.Entry<Integer, ArrayList<String>> curr : scores.entrySet()) {
	 int temp3 = (curr.getValue()).size();
	 for (int i = 0; i<temp3; i++) {
	    System.out.println(curr.getKey() + ": " + (curr.getValue()).get(i));
	 }
      }
      run(in);

   }

}

