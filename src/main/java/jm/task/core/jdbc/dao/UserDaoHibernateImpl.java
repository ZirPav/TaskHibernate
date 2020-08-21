package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    private static final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sqlCreate = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER not NULL auto_increment primary key, " +
                "name VARCHAR(255) not null , " +
                "lastName VARCHAR(255) not null , " +
                "age INTEGER)";

        session.createSQLQuery(sqlCreate).executeUpdate();
        transaction.commit();
        System.out.println("Таблица существует.");
        session.close();


    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sqlDrop = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sqlDrop).executeUpdate();
        transaction.commit();
        System.out.println("Таблица не существует.");
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(User.class, id);

        if (user != null) {
            session.delete(user);
            System.out.println("User удален.");
        }

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("from User").list();

        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sqlClean = "TRUNCATE TABLE users";

        try {
            session.createSQLQuery(sqlClean).executeUpdate();
            transaction.commit();
            System.out.println("Таблица была очищена.");
        } catch (Exception e) {
            System.out.println("Таблица не была очищена.");
        } finally {
            session.close();
        }


    }
}
