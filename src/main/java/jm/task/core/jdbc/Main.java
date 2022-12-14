package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


        User user = new User();
        user.setName("Пётр");
        user.setLastName("Иванов");
        user.setAge(Byte.valueOf("16"));

        User user1 = new User("Ivan", "Petrov", (byte) 24);
        User user2 = new User("Андрей", "Коновалов", (byte) 38);
        User user3 = new User("Владислав", "Пельш", (byte) 46);

        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser(user.getName(), user.getLastName(), user.getAge());
        userDaoHibernate.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userDaoHibernate.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userDaoHibernate.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        userDaoHibernate.getAllUsers();
//        userDaoHibernate.removeUserById(1);
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();

/*
        userService.createUsersTable();
        userService.saveUser(user.getName(),user.getLastName(), user.getAge());
        userService.saveUser(user1.getName(),user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(),user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(),user3.getLastName(), user3.getAge());
*/

//        userService.removeUserById(4);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
