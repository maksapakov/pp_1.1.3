package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public static final String DELETE_SQL = """
            DELETE FROM katadbtest.users
            WHERE id_users = ?
            """;
    public static final String SAVE_USER = """
            INSERT INTO katadbtest.users (name, last_name, age) 
            VALUES (?,?,?)
            """;
    public static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS katadbtest.users (
            id_users BIGINT(19) PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
            name VARCHAR(45) NOT NULL,
            last_name VARCHAR(45) NOT NULL,
            age TINYINT(3) NOT NULL CHECK(age > 0 AND age < 150))
            """;

    public void createUsersTable() {
/*
        String sql = "CREATE TABLE IF NOT EXISTS katadbtest.users (" +
                     "id_users BIGINT(19) PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL," +
                     "name VARCHAR(45) NOT NULL," +
                     "last_name VARCHAR(45) NOT NULL," +
                     "age TINYINT(3) NOT NULL CHECK(age > 0 AND age < 150))";
*/

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS katadbtest.users";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO katadbtest.users (name, last_name, age) VALUES (?,?,?)";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
//        String sql = "DELETE FROM katadbtest.users WHERE id_users=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id_users, name, last_name, age FROM katadbtest.users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id_users"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE katadbtest.users";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
