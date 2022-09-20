package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.model.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        User user = new User();
        user.setName("Пётр");
        user.setLastName("Иванов");
        user.setAge(Byte.valueOf("16"));

        userService.saveUser(user.getName(),user.getLastName(), user.getAge());
//        userService.removeUserById(7);
//        userService.dropUsersTable();
//        userService.createUsersTable();
//        userService.cleanUsersTable();
        userService.getAllUsers();
    }


}
