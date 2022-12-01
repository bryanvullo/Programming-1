package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Violinist extends MusicianPerson {

  public Violinist(String name, SoundSystem soundSystem, int seat) {
    super(name, soundSystem, seat,
        "Violin", 41, 50, 100); //sets unique data of a Violinists
  }

  public Violinist(String name, SoundSystem soundSystem) {
    super(name, soundSystem,
        "Violin", 41, 50, 100); //sets unique data of a Violinists
  }
}
