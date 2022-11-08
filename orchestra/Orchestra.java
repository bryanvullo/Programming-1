package orchestra;

import people.musicians.Musician;

import java.util.HashMap;
import java.util.Map;

public class Orchestra {
  HashMap<Integer, Musician> seating = new HashMap<Integer, Musician>();

  public Orchestra() {
    for (int i = 0; i < 16; i++) { //initialises the seats in the seating hashmap
      seating.put(i, null);
    }
  }

  public int sitDown(Musician musician) {
    for (Musician musicianInSeat : seating.values()) { //only care about the value
      if (musicianInSeat == musician) {
        return 2; //musician is already in the orchestra
      }
    }
    for (Map.Entry<Integer, Musician> seat : seating.entrySet()) { //we want both key and value
      // as we need to check if seat is empty. If it is, then add the musician to that seat
      if (seat.getValue() == null) {
        seating.put(seat.getKey(), musician);
        musician.setSeat(seat.getKey());
        return 0;
      }
    }
    return 1; //musician isn't already seated nor are there any available seats
  }

  public boolean isSeated(Musician musician) {
    for (Musician musicianInSeat : seating.values()) {
      if (musicianInSeat == musician) {
        return true; //the musician is seated, return true
      }
    }
    return false; //cannot be found in seats, then return false
  }

  public void standUp(Musician musician) {
    for (Map.Entry<Integer, Musician> seat : seating.entrySet()) {
      if (seat.getValue() == musician) {
        int seatNumber = seat.getKey();
        seating.put(seatNumber, null);
        //TODO musician.setSeat(null) instead of an int
        break;
      }
    }
  }

  public void playNextNote() {
    for (Musician musician : seating.values()) {
      if (musician != null) {
        musician.playNextNote();
      }
    }
  }
}
