package lab4part3;

import java.util.ArrayList;
import java.util.Iterator;

public class UserGroup {
  ArrayList<User> users;
  public UserGroup() {
    this.users = new ArrayList<User>();
  }

  public ArrayList<User> getUsers() {
    return this.users;
  }

  public void addSampleData() {
    this.users.add(new User("sh5", "editor", "Son Hoang"));
    this.users.add(new User("bv1", "admin", "Bryan Vullo"));
    this.users.add(new User("vr1", "editor", "Valencia"));
    this.users.add(new User("rs8", "user", "Ryan Sohota"));
    this.users.add(new User("ab9", "user", "Alfie Brown"));
    this.users.add(new User("jk3", "admin", "John Kennedy"));
    this.users.add(new User("jm6", "user", "Johnny Midgley"));
    this.users.add(new User("rj7", "user", "Ronnie Jack"));
    this.users.add(new User("sj2", "user", "Steve Johnson"));
    this.users.add(new User("rs3", "admin", "Riley Sorb"));
  }

  public User getUser(int index){
    return this.users.get(index);
  }

  public void printUsernames() {
    for (User user : users) {
      System.out.println(user.getUsername() + " " + user.getUserType());
    }
  }

  public void removeFirstUser() {
    users.remove(0);
  }

  public void removeLastUser() {
    users.remove(users.size() - 1);
  }

  public void removeUser(String username) {
    int index = 0;
    for (User user : users) {
      if (user.getUsername() == username) {
        break;
      }
      index = ++index;
    }
    users.remove(index);
  }

  public Iterator<User> getUserIterator() {
    Iterator<User> it;
    return it = this.users.iterator();
  }

  public void addUser(User user) {
    this.users.add(user);
  }
}
