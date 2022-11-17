package people.musicians;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import people.Person;
import utils.SoundSystem;

public abstract class MusicianPerson extends Person implements Musician {
  private String instrument;
  private int instrumentID;
  private int SOFT;
  private int LOUD;
  private int loudness;
  private List<Integer> notes = new ArrayList<Integer>();
  private Iterator<Integer> nextNote;
  private int seat;
  private SoundSystem soundSystem;

  public MusicianPerson(String name, SoundSystem soundSystem, int seat,
      String instrument, int instrumentID, int SOFT, int LOUD) {
    super(name);
    this.soundSystem = soundSystem;
    setSeat(seat);

    //unique data for each type of musician
    this.instrument = instrument;
    this.instrumentID = instrumentID;
    this.SOFT = SOFT;
    this.LOUD = LOUD;
  }

  public MusicianPerson(String name, SoundSystem soundSystem,
      String instrument, int instrumentID, int SOFT, int LOUD) {
    super(name);
    this.soundSystem = soundSystem;

    //unique data for each type of musician
    this.instrument = instrument;
    this.instrumentID = instrumentID;
    this.SOFT = SOFT;
    this.LOUD = LOUD;
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
    if (nextNote.hasNext()) { //checks if there is a note to be played
      int note = nextNote.next(); //gets the next note
      soundSystem.playNote(seat, note, loudness); //plays the note
    }
  }

  public int getInstrumentID() {
    return instrumentID;
  }
}
