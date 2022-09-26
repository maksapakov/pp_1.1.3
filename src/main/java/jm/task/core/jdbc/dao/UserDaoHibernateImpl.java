package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        openTransactionSession();

        Session session = getSession();
        Query<User> query = session.createQuery(CREATE_TABLE, User.class);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        openTransactionSession();

        String sql = "SELECT * FROM users";

        Session session = getSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery(sql, User.class);
        nativeQuery.executeUpdate();
        List<User> users = nativeQuery.list();

        closeTransactionSession();

        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}
