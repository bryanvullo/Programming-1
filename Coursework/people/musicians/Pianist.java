package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pianist extends MusicianPerson {

  public Pianist(String name, SoundSystem soundSystem, int seat) {
    super(name, soundSystem, seat,
        "Piano", 1, 75, 150); //sets unique data of a Pianist
  }

  public Pianist(String name, SoundSystem soundSystem) {
    super(name, soundSystem,
        "Piano", 1, 75, 150); //sets unique data of a Pianist
  }
}
