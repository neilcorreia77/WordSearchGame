import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;
import java.lang.Math;
import java.util.TreeSet;
import java.util.Arrays;
import java.lang.Character;
import java.util.ArrayList;



public class WordSearchImplementingClass implements WordSearchGame {

   private TreeSet<String> lexicon;
   private String[][] board;
   private int size;
   private boolean[][] arr;
   private List<Integer> path;
   private List<Integer> newPath;
   private int length;
   
   public WordSearchImplementingClass() {
      lexicon = null;
      board = new String[4][4];
      board[0][0] = "E";
      board[0][1] = "E";
      board[0][2] = "C";
      board[0][3] = "A";
      board[1][0] = "A";
      board[1][1] = "L";
      board[1][2] = "E";
      board[1][3] = "P";
      board[2][0] = "H";
      board[2][1] = "N";
      board[2][2] = "B";
      board[2][3] = "O";
      board[3][0] = "Q";
      board[3][1] = "T";
      board[3][2] = "T";
      board[3][3] = "Y";   
      path = new ArrayList<Integer>();
      newPath = new ArrayList<Integer>();
   }
   
   /**
     * Loads the lexicon into a data structure for later use. 
     * 
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         lexicon = new TreeSet<String>(); 
         Scanner scanner = new Scanner(new File(fileName));
         while (scanner.hasNext()) {
            lexicon.add(scanner.next().toUpperCase());
         }  
         scanner.close();
      }
      
      catch(java.io.FileNotFoundException e) {  
         throw new IllegalArgumentException();
      }   
      
    
    
   }
    
    /**
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     * 
     * @param letterArray This array of length N^2 stores the contents of the
     *     game board in row-major order. Thus, index 0 stores the contents of board
     *     position (0,0) and index length-1 stores the contents of board position
     *     (N-1,N-1). Note that the board must be square and that the strings inside
     *     may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is  not
     *     square.
     */
   public void setBoard(String[] letterArray) {
    
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      
      int e = (int) Math.sqrt(letterArray.length);
      int test = e * e;
      
      if (letterArray.length != test) {
         throw new IllegalArgumentException();
      }
      
      board = new String[(int) e][(int) e];
      int n = 0;
      for (int i = 0; i < e; i++) {
         for (int j = 0; j < e; j++) {
            board[i][j] = letterArray[n++];   
         }
      
      }
      size = (int) e;
    
   }
    
      /**
     * Creates a String representation of the board, suitable for printing to
     *   standard out. Note that this method can always be called since
     *   implementing classes should have a default board.
     */
   public String getBoard() {
      String gettingBoard = "";
      for (int i = 0; i < size; i++) {
         gettingBoard += "\n";
         for (int j = 0; j < size; j++) {
            gettingBoard = board[i][j] + " ";
         }    
      }
      return gettingBoard;
   }
    
     /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     * 
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
         
      }
         
      if (lexicon == null) {
         throw new IllegalStateException();
      }
     
      return null;
   }
   
   /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      int score = 0;
      Iterator<String> iterator = words.iterator();
      while (iterator.hasNext()) {
         String newWord = iterator.next();
         if (newWord.length() >= minimumWordLength && isValidWord(newWord)) {
            score += (newWord.length() - minimumWordLength) + 1;
         }
      }
      return score;
   
   
   }
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      
      wordToCheck = wordToCheck.toUpperCase();
      return lexicon.contains(wordToCheck);
   }
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      
      prefixToCheck = prefixToCheck.toUpperCase();
      String prefixCheck = lexicon.ceiling(prefixToCheck);
      if (prefixCheck != null) {
         return prefixCheck.startsWith(prefixToCheck);
      
      }
      return false;
   
   
   }
   
       /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      path.clear();
      newPath.clear();
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0)) == Character.toUpperCase(wordToCheck.charAt(0))) {
               int newInt = j + (i * length);
               path.add(newInt);
               efficiency(wordToCheck, board[i][j], i, j);
               if (!newPath.isEmpty()) {
                  return newPath;
               }
               path.clear();
               newPath.clear();
            }
         
         }
      
      }
      return path;
   
   }
   
   public void efficiency(String wordToCheck, String current, int x, int y) {
      arr[x][y] = true;
      if (!(isValidPrefix(current))) {
         return;
      }
      if (current.toUpperCase().equals(wordToCheck.toUpperCase())) {
         newPath = new ArrayList(path);
         return;
      }
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (current.equals(wordToCheck)) {
               return;
            }
            if ((x + i) <= (length - 1) && (y + j) <= (length - 1) && (x + i) >= 0 && (y + j) >= 0 && !arr[x + i][y + j]) {
               arr[x + i][y + j] = true;
               int newInt = (x + i) * length + y + j;
               path.add(newInt);
               efficiency(wordToCheck, current + board[x + i][y + j], x + i, y + j);
               arr[x + i][y + j] = false;
               path.remove(path.size() - 1);
            
            
            
            }
            
         }
         arr[x][y] = false;
         return;
      
      
      
      }
   
   }
   
   







}