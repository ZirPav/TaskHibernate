package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    private static final String testName = "Ivan";
    private static final String testLastName = "Ivanov";
    private static final byte testAge = 5;

    public static void main(String[] args) {

        /*userService.createUsersTable();

        userService.saveUser(testName, testLastName, testAge);
        userService.saveUser("Pavel", "Ziryukin", (byte) 27);

        userService.saveUser("Artem", "Ola", (byte) 31);
        userService.saveUser("Olga", "Liona", (byte) 25);


        */

/*        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }*/

        //userService.cleanUsersTable();

        //userService.dropUsersTable();

        /*userService.saveUser("Pavel", "Ziryukin", (byte) 27);
        userService.saveUser("Artem", "Ola", (byte) 31);
        userService.saveUser("Olga", "Liona", (byte) 25);*/
        //userService.removeUserById(2);


        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        List<User> userList = userService.getAllUsers();

        System.out.println(userList.size());
    }
}
