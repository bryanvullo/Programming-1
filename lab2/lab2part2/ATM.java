public class ATM {
  /**
   * This is a class which represents an ATM system.
   * For now we can only enter a balance, withdraw and deposit.
   * though there are some bugs such as the account is able to go negative.
   */
  private int balance = 0;
  private Toolbox myToolbox = new Toolbox();

  /** this is the main menu of the ATM system. */
  public void go() {
    System.out.println("Welcome to online ATM banking");
    System.out.println("How much do you want in your account?");

    balance = myToolbox.readIntegerFromCmd();

    System.out.println();
    System.out.println("What do you want to do?");
    System.out.println("1 : Withdraw");
    System.out.println("2 : Deposit");
    System.out.println("3 : Inquire");
    System.out.println("4 : Quit");
    int option = myToolbox.readIntegerFromCmd();

    if (option == 1) {
      withdraw();
    } else {
      if (option == 2) {
        deposit();
      } else {
        if (option == 3) {
          inquire();
        } else {
          if (option == 4) {
            quit();
          }
        }
      }
    }
  }

  /**
   * This is the withdraw method.
   * allows user to remove meney from the balance.
   */
  public void withdraw() {
    System.out.println("*****************************************");
    System.out.println("              Withdrawal                 ");
    System.out.println("*****************************************");

    System.out.println("How much would you like to withdraw?");
    int amount = myToolbox.readIntegerFromCmd();
    balance = balance - amount;

    System.out.println("*****************************************");
    System.out.println("     Your new balance is " + balance);
    System.out.println("*****************************************");
  }

  /**
   * this is the deposit method.
   * allows user to add money to their balance.
   */
  public void deposit() {
    System.out.println("*****************************************");
    System.out.println("                Deposit                  ");
    System.out.println("*****************************************");

    System.out.println("How much would you like to deposit?");
    int amount = myToolbox.readIntegerFromCmd();
    balance = balance + amount;

    System.out.println("*****************************************");
    System.out.println("     Your new balance is " + balance);
    System.out.println("*****************************************");
  }

  /** this method outputs the user's balance. */
  public void inquire() {
    System.out.println("*****************************************");
    System.out.println("     Your new balance is " + balance);
    System.out.println("*****************************************");
  }

  /** this method quits the program. */
  public void quit() {
    System.out.println("*****************************************");
    System.out.println("                GoodBye!");
    System.out.println("*****************************************");
  }

  public static void main(String[] args) {
    ATM myATM = new ATM();
    myATM.go();
  }
}