package lab5part3;

import java.util.HashMap;
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

  public HashMap<String, Integer> getWordCounts() {
    String[] words = getWordArray();
    HashMap<String, Integer> myMap = new HashMap<>();

    for (String word : words) {
      if (myMap.containsKey(word)) {
        Integer value = myMap.get(word);
        value = ++value;
        myMap.put(word, value);
      }
      else {
        Integer value = 1;
        myMap.put(word, value);
      }
    }
    return myMap;
  }
}