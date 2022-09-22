package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private static final String DELETE_SQL = """
            DELETE FROM katadbtest.users
            WHERE id_users = ?
            """;
    private static final String SAVE_USER = """
            INSERT INTO katadbtest.users (name, last_name, age)
            VALUES (?,?,?)
            """;
    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS katadbtest.users (
            id_users BIGINT(19) PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
            name VARCHAR(45) NOT NULL,
            last_name VARCHAR(45) NOT NULL,
            age TINYINT(3) NOT NULL CHECK(age > 0 AND age < 150))
            """;

    private static final String DROP_TABLE = """
            DROP TABLE IF EXISTS katadbtest.users
            """;

    private static final String SELECT_ALL = """
            SELECT id_users, name, last_name, age FROM katadbtest.users
            """;

    private static final String CLEAR_TABLE = """
            TRUNCATE katadbtest.users
            """;

    public void createUsersTable() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

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

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

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

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_TABLE)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
