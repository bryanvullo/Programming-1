package lab3part3;

public class CardLock {
  private SmartCard lastCardSeen;
  private Boolean studentAccess = false;

  public void swipeCard(SmartCard card) {
    this.lastCardSeen = card;
  }

  public SmartCard getLastCardSeen() {
    return this.lastCardSeen;
  }

  public Boolean isUnlocked() {
    // if the card type is staff or if the door allows student access
    if (this.getLastCardSeen().isStaff() || this.studentAccess) {
      return true;
    }
    // therefore if door is staff only and card type is student
    else {
      return false;
    }
  }

  public void toggleStudentAccess() {
    this.studentAccess = !this.studentAccess;
  }
}
