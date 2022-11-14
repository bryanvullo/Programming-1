package lab5part3;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    String platoQuote = "You can discover more about a person in an hour of play than in a year of conversation";
    String rooseveltQuote = "When you play play hard when you work dont play at all";

    WordGroup plato = new WordGroup(platoQuote);
    WordGroup roosevelt = new WordGroup(rooseveltQuote);

    String[] platoWords = plato.getWordArray();
    String[] rooseveltWords = roosevelt.getWordArray();

    System.out.println("PLATO");
    for (String word : platoWords) {
      System.out.println(word);
    }
    System.out.println("ROOSEVELT");
    for (String word : rooseveltWords) {
      System.out.println(word);
    }

    System.out.println("HASHSET");
    HashSet<String> platovelt = plato.getWordSet(roosevelt);

    for (String word : platovelt) {
      System.out.println(word);
    }

    System.out.println("HASHMAP");
    HashMap<String, Integer> platoCount = plato.getWordCounts();
    HashMap<String, Integer> rooseveltCount = roosevelt.getWordCounts();

    for (String word : platoCount.keySet()) {
      System.out.println(word + ": " + platoCount.get(word));
    }
    for (String word : rooseveltCount.keySet()) {
      System.out.println(word + ": " + rooseveltCount.get(word));
    }

    System.out.println("COMPLETE SET OF WORD COUNTS");
    for (String word : platovelt) {
      Integer value = 0;
      if (platoCount.containsKey(word)){
        value = value + platoCount.get(word);
      }
      if (rooseveltCount.containsKey(word)){
        value = value + rooseveltCount.get(word);
      }
      System.out.println(word + ": " + value);
    }

  }
}