package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();


    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Pavel", "Ziryukin", (byte) 27);
        System.out.println(String.format("User с именем - %s добавлен в базу", userService.getAllUsers().get(0).getName()));
        userService.saveUser("Artem", "Olaf", (byte) 31);
        System.out.println(String.format("User с именем - %s добавлен в базу", userService.getAllUsers().get(1).getName()));
        userService.saveUser("Olga", "Liona", (byte) 25);
        System.out.println(String.format("User с именем - %s добавлен в базу", userService.getAllUsers().get(2).getName()));
        userService.saveUser("Maia", "Soana", (byte) 35);
        System.out.println(String.format("User с именем - %s добавлен в базу", userService.getAllUsers().get(3).getName()));

        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
