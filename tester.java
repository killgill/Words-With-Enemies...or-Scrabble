import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tester{
   public static void main(String[] args) throws FileNotFoundException {
      AnagramDictionary dict = new AnagramDictionary("sowpods.txt");
      Scanner in = new Scanner(System.in);
      String s = in.nextLine();
      ArrayList<String> toPrint = dict.getAnagramsOf(s);
      for (int i =0; i<toPrint.size(); i++){
         System.out.println(toPrint.get(i));
      }
      Rack rack = new Rack(s);
      toPrint = rack.returnSubsets();
      for (int i =0; i<toPrint.size(); i++){
         System.out.println(toPrint.get(i));
      }
   }
}
