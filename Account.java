public class Account {
  private int balance = 10;
  private boolean active = false;

  public void withdrawFiver() {
    if (active != true) {
      System.out.println("Your account isn't active");
      System.out.println("Withdrawal is not allowed");
    } else if (balance < 5) {
      System.out.println("Not enough money");
    } else {
      balance = balance - 5;
      System.out.println("Your new balance: £" + balance);
    }
  }

  public static void main(String[] args) {
    Account a = new Account();
    a.active = true;
    System.out.println("balance before £" + a.balance);
    a.withdrawFiver();
    System.out.println("balance after £" + a.balance);
  }
}