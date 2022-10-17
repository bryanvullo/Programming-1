/**
 * This is a class which represents an ATM system.
 * For now we can only enter a balance, withdraw and deposit.
 * now the account cannot go negative and erroneous inputs aren't allowed
 */
public class ATM {
  private Integer balance = -1;
  private Toolbox myToolbox = new Toolbox();

  /** this is the main menu of the ATM system. */
  public void go() {
    System.out.println("Welcome to online ATM banking");
    System.out.println("How much do you want in your account?");

    while (balance < 0) { //doesnt allow for a negative input
      balance = myToolbox.readIntegerFromCmd();
    }
    while (true) { //loops indefinately until user quits by pressing 4
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
    //doesnt allow a negative input or make balance go into negative
    if (amount < 0 || amount > balance) {
      while (amount < 0 || amount > balance) {
        amount = myToolbox.readIntegerFromCmd();
      }
    }

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
    if (amount < 0) { //doesnt allow a negative input
      while (amount < 0) {
        amount = myToolbox.readIntegerFromCmd();
      }
    }
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
    System.exit(0); //quits the program
  }

  public static void main(String[] args) {
    ATM myATM = new ATM();
    myATM.go();
  }
}