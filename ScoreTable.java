// Name: Karan Singh Gill
// USC NetID: karansig
// CS 455 PA4
// Fall 2017

public class ScoreTable {
   private int[] scores;

   public ScoreTable() {
      
      scores = new int[26];
      
      for (int i = 0; i<26; i++) {
         if (i == 0 || i == 4 || i == 8 || i == 11 || i == 13 || i ==14 ||
         i == 17 || i == 18 || i == 19 || i == 20) {
            scores[i] = 1;
         }
         else if (i == 3 || i == 6) {
            scores[i] = 2;
         }
         else if (i == 1 || i == 2 || i == 12 || i == 15) {
            scores[i] = 3;
         }
         else if (i == 5 || i == 7 || i == 21 || i == 22 || i == 24) {
            scores[i] = 4;
         }
         else if (i == 10) {
            scores[i] = 5;
         }
         else if (i == 9 || i == 23) {
            scores[i] = 8;
         }
         else if (i == 16 || i == 25) {
            scores[i] = 10;
         }
      }

   }

   public int getScore(String word) {
      String lower = word.toLowerCase();
      int score = 0;
      for (int i = 0; i< lower.length(); i++) {
         score += scores[lower.charAt(i)-'a'];
      }
      return score;
   }
}
