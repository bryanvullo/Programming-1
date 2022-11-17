package people.musicians;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import people.Person;
import utils.SoundSystem;

public class Cellist extends MusicianPerson {
  public Cellist(String name, SoundSystem soundSystem, int seat) {
    super(name, soundSystem, seat,
        "Cello", 43, 50, 100); //sets unique data of a Cellist
  }

  public Cellist(String name, SoundSystem soundSystem) {
    super(name, soundSystem,
        "Cello", 43, 50, 100); //sets unique data of a Cellist
  }
}
