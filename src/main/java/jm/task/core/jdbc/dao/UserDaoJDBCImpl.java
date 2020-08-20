package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCreate = "CREATE TABLE users2 " +
                "(id INTEGER not NULL auto_increment, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age INTEGER)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCreate)) {
            preparedStatement.execute();
        } catch (SQLException e) {

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

        String sqlSave = "INSERT INTO users.users (name, last_name, age) values (?, ?, ?);";

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

        String sqlUsers = "SELECT id, name, LAST_NAME, age FROM users.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlUsers);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
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
