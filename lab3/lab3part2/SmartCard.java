package lab3part2;

public class SmartCard {
  private String owner;
  private Boolean staffStatus = false;

  public SmartCard(String name) {
    this.owner = name;
  }

  public String getOwner() {
    return this.owner;
  }

  public Boolean isStaff() {
    return this.staffStatus;
  }

  public void setStaff(Boolean status) {
    this.staffStatus = status;
  }


}
