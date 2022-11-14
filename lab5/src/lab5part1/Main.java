package lab5part1;

public class Main {
  public static void main(String[] args) {
    String platoQuote = "You can discover more about a person in an hour of play than in a year of conversation";
    String rooseveltQuote = "When you play play hard when you work dont play at all";

    WordGroup plato = new WordGroup(platoQuote);
    WordGroup roosevelt = new WordGroup(rooseveltQuote);

    String[] platoWords = plato.getWordArray();
    String[] rooseveltWords = roosevelt.getWordArray();

    for (String word : platoWords) {
      System.out.println(word);
    }
    for (String word : rooseveltWords) {
      System.out.println(word);
    }
  }
}