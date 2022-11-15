package people.musicians;

import people.Person;
import utils.SoundSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pianist extends Person implements Musician {

  private String instrument = "Piano";
  private final int instrumentID = 1;
  private final int SOFT = 75;
  private final int LOUD = 150;
  private int loudness;
  private List<Integer> notes = new ArrayList<Integer>();
  private Iterator<Integer> nextNote;
  private int seat;
  private SoundSystem soundSystem;

  public Pianist(String name, SoundSystem soundSystem, int seat) {
    super(name); //sets name
    this.soundSystem = soundSystem; //sets sound system
    setSeat(seat); //sets seat
  }

  public Pianist(String name, SoundSystem soundSystem) {
    super(name); //sets name
    this.soundSystem = soundSystem; //sets sound system
  }

  public void setSeat(int seat) {
    this.seat = seat; //sets seat to given seat
    soundSystem.setInstrument(this.seat, instrumentID);
    //sets the sound system the instrument and seat
  }

  public void readScore(int[] notes, boolean soft) {
    this.notes.clear();
    for (int note : notes) { //reads the notes into the list
      this.notes.add(note);
    }
    nextNote = this.notes.iterator(); //sets the iterator
    if (soft) { //sets the loudness
      loudness = SOFT;
    } else {
      loudness = LOUD;
    }
  }

  public void playNextNote() {
    if (nextNote.hasNext()) { //checks to see if there is a note to be played
      int note = nextNote.next(); //gets the next note
      soundSystem.playNote(seat, note, loudness); //plays the note
    }
  }

  public int getInstrumentID() {
    return instrumentID;
  }
}
