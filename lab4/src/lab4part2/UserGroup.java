package lab4part2;

import java.util.ArrayList;

public class UserGroup {
  ArrayList<User> users;
  public UserGroup() {
    this.users = new ArrayList<User>();
  }
  public ArrayList<User> getUsers() {
    return this.users;
  }

  public void addSampleData() {
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("vr1", "admin", "Valencia"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
    this.users.add(new User("bv1", "user", "Bryan Vullo"));
  }

  public User getUser(int index){
    return this.users.get(index);
  }

  public void printUsernames() {
    for (User user : users) {
      System.out.println(user.getUsername() + " " + user.getUserType());
    }
  }

  public static void main(String[] args) {
    UserGroup myGroup = new UserGroup();
    myGroup.addSampleData();
    System.out.println(myGroup.getUsers());
    myGroup.printUsernames();
  }
}
