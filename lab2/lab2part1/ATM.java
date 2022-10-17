public class ATM {
  /**
   * This is a class which represents an ATM system.
   * For now we can only enter a balance.
   */
  public void go() {
    System.out.println("Welcome to online ATM banking");
    System.out.println("How much do you want in your account?");

    Toolbox myToolbox = new Toolbox();
    int balance = myToolbox.readIntegerFromCmd();
    System.out.println("Your balance is £" + balance);
  }

  public static void main(String[] args) {
    ATM myATM = new ATM();
    myATM.go();
  }
}