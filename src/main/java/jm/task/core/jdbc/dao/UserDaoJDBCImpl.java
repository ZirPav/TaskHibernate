package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE users " +
                    "(id INTEGER not NULL auto_increment primary key, " +
                    "name VARCHAR(255) not null , " +
                    "lastName VARCHAR(255) not null , " +
                    "age INTEGER)");
            System.out.println("Таблица успешно создана.");
        } catch (SQLException e) {
            System.out.println("Таблица не создана или она уже есть.");
        }

    }

    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена или её уже нет.");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO users.users (name, lastName, age) values (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Сохранение не удалось.");
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Удаление пользователя не удалось.");
        }

    }

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM users");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("При получении списка пользователей произошла ошибка.");
        }
        return allUsers;
    }

    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Очистка таблицы прошла успешно.");
        } catch (SQLException e) {
            System.out.println("Отчистка таблицы не удалась.");
        }
    }
}
