package lab5part1;

public class WordGroup {
  String words;

  public WordGroup(String words) {
    this.words = words.toLowerCase();
  }

  public String[] getWordArray() {
    return this.words.split(" ");
  }
}