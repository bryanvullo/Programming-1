package lab5part2;

import java.util.HashSet;

public class WordGroup {
  String words;

  public WordGroup(String words) {
    this.words = words.toLowerCase();
  }

  public String[] getWordArray() {
    return this.words.split(" ");
  }

  public HashSet<String> getWordSet(WordGroup group) {
    HashSet<String> mySet = new HashSet<String>();
    for (String word : this.getWordArray()) {
      mySet.add(word);
    }
    for (String word : group.getWordArray()) {
      mySet.add(word);
    }
    return mySet;
  }
}