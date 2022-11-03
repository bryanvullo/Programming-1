package lab8part3;

interface Transport {
  // all transportation carry some sort of cargo or passengers
  public abstract void load();

  // all transportation moves from one place to another but in different ways so its an abstract method
  public abstract void move();

  // all transport can only operate in specific locations
  public abstract void location();
}
