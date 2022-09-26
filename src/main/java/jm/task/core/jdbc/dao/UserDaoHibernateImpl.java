package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
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
        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {

            session.createNativeQuery(CREATE_TABLE, User.class).executeUpdate();

        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {

            session.createNativeQuery(DROP_TABLE, User.class).executeUpdate();

        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.merge(user);
        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {

            User user = (User) session.getReference(User.class, id);

            session.remove(user);

        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        List<User> users = null;

        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {

            users = session.createQuery("from User", User.class).list();

            System.out.println(users);

        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session openTransactionSession = openTransactionSession();
             Session session = getSession()) {

            session.createNativeQuery(CLEAN_TABLE, User.class).executeUpdate();

        } catch (HibernateException hibernateException) {
            getTransaction().rollback();
            hibernateException.printStackTrace();
        }
    }
}
