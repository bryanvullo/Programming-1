package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Violinist extends Person implements Musician {
  private String instrument = "Violin";
  private int instrumentID = 41;
  final int SOFT = 50;
  final int LOUD = 100;
  private int loudness;
  private List<Integer> notes = new ArrayList<Integer>();
  public Iterator<Integer> nextNote;
  private int seat;
  private SoundSystem soundSystem;

  public Violinist(String name, SoundSystem soundSystem, int seat) {
    super(name); //sets name
    this.soundSystem = soundSystem; //sets sound system
    setSeat(seat); //sets seat
  }
  public Violinist(String name, SoundSystem soundSystem) {
    super(name); //sets name
    this.soundSystem = soundSystem; //sets sound system
  }

  public void setSeat(int seat) {
    this.seat = seat; //sets seat to given seat
    soundSystem.setInstrument(this.seat, instrumentID); //sets the sound system the instrument and seat
  }

  public void readScore(int[] notes, boolean soft) {
    for (int note : notes) { //reads the notes into the list
      this.notes.add(note);
    }
    nextNote = this.notes.iterator(); //sets the iterator
    if (soft) { //sets the loudness
      loudness = SOFT;
    }
    else {
      loudness = LOUD;
    }
  }

  public void playNextNote() {
    if (nextNote.hasNext()) {
      int note = nextNote.next(); //gets the next note
      soundSystem.playNote(seat, note, loudness); //plays the note
    }
  }
}
