package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCreate = "CREATE TABLE users " +
                "(id INTEGER not NULL auto_increment primary key, " +
                "name VARCHAR(255) not null , " +
                "lastName VARCHAR(255) not null , " +
                "age INTEGER)";

        try (PreparedStatement statement = connection.prepareStatement(sqlCreate)) {
            statement.executeUpdate();
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            System.out.println("Таблица не создана или она уже есть.");
        }

    }

    public void dropUsersTable() {

        String sqlDrop = "DROP TABLE users.users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDrop)) {
            preparedStatement.execute();
        } catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlSave = "INSERT INTO users.users (name, lastName, age) values (?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String sqlRemove = "DELETE FROM users.users WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemove)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        String sqlUsers = "SELECT id, name, lastName, age FROM users.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlUsers);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {

        String sqlClean = "TRUNCATE TABLE users.users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlClean)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
