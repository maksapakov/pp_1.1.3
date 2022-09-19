package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.model.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        User user = new User();
        user.setName("Пётр");
        user.setLastName("Иванов");
        user.setAge(Byte.valueOf("16"));

        userService.saveUser(user.getName(),user.getLastName(), user.getAge());
        userService.removeUserById(7);
        userService.dropUsersTable();
        userService.createUsersTable();
/*
        Util.statement.executeUpdate("CREATE TABLE IF NOT EXISTS katadbtest.test (" +
                "id bigint auto_increment primary key," +
                "name varchar(40) not null," +
                "lastname varchar(40) not null," +
                "age tinyint(3) not null)");
        Util.statement.executeUpdate("INSERT INTO katadbtest.test (name, lastname, age) value ('Nikolay', 'Porechenkov', '35')");
        Util.statement.executeUpdate("INSERT INTO katadbtest.test (name, lastname, age) value ('Peter', 'Velikiy', '28')");
        ResultSet resultSet = Util.statement.executeQuery("select * from katadbtest.test");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4));
        }
*/

//        Util.statement.executeUpdate("DROP TABLE katadbtest.test");
        /*
        Util util = new Util();

        String query = "select * from katadbtest.users";
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("idUser"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));

                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }


}
