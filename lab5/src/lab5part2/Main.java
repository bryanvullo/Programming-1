package lab5part2;

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
  }
}