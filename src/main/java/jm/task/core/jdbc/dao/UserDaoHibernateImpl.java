package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS katadbtest.users (
            id_users BIGINT(19) PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
            name VARCHAR(45) NOT NULL,
            last_name VARCHAR(45) NOT NULL,
            age TINYINT(3) NOT NULL CHECK(age > 0 AND age < 150))
            """;

    private static final String DROP_TABLE = """
            DROP TABLE if exists katadbtest.users
            """;

    private static final String CLEAN_TABLE = """
            TRUNCATE users
            """;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        openTransactionSession();

        Session session = getSession();
        session.createNativeQuery(CREATE_TABLE, User.class).executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {
        openTransactionSession();

        Session session = getSession();
        session.createNativeQuery(DROP_TABLE, User.class).executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        openTransactionSession();

        Session session = getSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.merge(user);

        closeTransactionSession();
    }

    @Override
    public void removeUserById(long id) {
        openTransactionSession();

        User user;

        Session session = getSession();
        user = (User) session.getReference(User.class, id);
        session.remove(user);

        closeTransactionSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        openTransactionSession();

        Session session = getSession();
        List<User> users = session.createQuery("FROM User", User.class).list();

        closeTransactionSession();

        System.out.println(users);

        return users;
    }

    @Override
    public void cleanUsersTable() {
        openTransactionSession();

        Session session = getSession();
        session.createNativeQuery(CLEAN_TABLE, User.class).executeUpdate();

        closeTransactionSession();
    }
}
