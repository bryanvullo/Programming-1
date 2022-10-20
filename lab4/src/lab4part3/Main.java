package lab4part3;

import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    UserGroup myGroup = new UserGroup();
    myGroup.addSampleData();
    myGroup.printUsernames();

    System.out.println("----ADMINISTRATORS----");

    UserGroup administrators = new UserGroup();
    Iterator<User> iterator;
    iterator = myGroup.getUserIterator();
    User user;
    while (iterator.hasNext()) {
      user = iterator.next();
      if (user.getUserType() == "admin") {
        administrators.addUser(user);
      }
    }
    administrators.printUsernames();

    user = administrators.getUser(administrators.getUsers().size() - 1);
    user.setUserType("user");

    System.out.println("----LAST PRINT----");
    System.out.println("----ALL USERS----");
    myGroup.printUsernames();
    System.out.println("----ADMINS----");
    administrators.printUsernames();
    //when a users type is changed such as from 'admin' to 'user' they remain in the admin group
    //a solution to this is that a new method such as 'removeNonAdmins' can be made to remove all the not admins types
    //this can be implements in the UserGroup class
    //or a new class can be made eg 'AdminGroup' which inherits from 'UserGroup' and add the new method there.
  }
}
