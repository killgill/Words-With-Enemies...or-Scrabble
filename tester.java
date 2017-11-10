import java.util.ArrayList;

public class tester{
   public static void main(String[] args) {
      AnagramDictionary dict = new AnagramDictionary("sowpods.txt");
      ArrayList<String> toPrint = dict.getAnagramsOf("food");
      for (int i =0; i<toPrint.size(); i++){
         System.out.println(toPrint.get(i));
      }
   }
}
