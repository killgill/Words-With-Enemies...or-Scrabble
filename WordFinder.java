import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFinder {
   private AnagramDictionary dict;
   public static void main(String[] args) throws FileNotFoundException {
      String filename = "sowpods.txt";
      if (args.length > 0) {
	 filename = args[0];
      }

      dict = new AnagramDictionary(filename);
      System.out.println("Type . to quit");
      Scanner in = new Scanner(System.in);
      run(in);

   }

   private void run(Scanner in){
      System.out.print("Rack? ");
      String s = in.nextLine();
      if (s.equals(".")){
	 System.exit(0);
      }
      Rack rack = new Rack(s);
      ArrayList<String> allSubsets = rack.returnSubsets();

      ArrayList<String> toPrint = dict.getAnagramsOf(s);
      for (int i =0; i<toPrint.size(); i++){
         System.out.println(toPrint.get(i));
      }
      toPrint = rack.returnSubsets();
      for (int i =0; i<toPrint.size(); i++){
         System.out.println(toPrint.get(i));
      }


      run(in);

   }

}

