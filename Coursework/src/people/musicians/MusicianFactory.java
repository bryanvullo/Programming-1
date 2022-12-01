package people.musicians;

import utils.SoundSystem;

public class MusicianFactory {

  public static MusicianPerson createMusician(String instrument, String name,
      SoundSystem soundSystem) {
    if (instrument.equals("Violin")) {
      return new Violinist(name, soundSystem);
    } else if (instrument.equals("Cello")) {
      return new Cellist(name, soundSystem);
    } else if (instrument.equals("Piano")) {
      return new Pianist(name, soundSystem);
    } else {
      System.err.println("we only accept piano, cello and violin musicians, given " + instrument);
      System.err.println("defaulted to piano"); //handled 'gracefully'
      return new Pianist(name, soundSystem);
    }
  }
}
