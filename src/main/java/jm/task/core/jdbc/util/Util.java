package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.schema.internal.HibernateSchemaManagementTool;

import javax.xml.validation.Schema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "mypass";
    public static final String URL = "jdbc:mysql://localhost:3306/katadbtest";

    private static Session session;
    private static Transaction transaction;
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/katadbtest");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "mypass");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//                Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println("Порядок?");
        return sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static Transaction getTransaction() {
        return transaction;
    }
    public static Session openSession() {
        return Util.getSessionFactory().openSession();
    }

    public static Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public static void closeSession() {
        session.close();
    }
    public static void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
//            System.out.println("Ok");
        } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
//            System.out.println("Not OK");
        }
        return connection;
    }
}
