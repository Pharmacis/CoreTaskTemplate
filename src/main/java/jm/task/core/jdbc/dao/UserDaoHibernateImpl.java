package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getSessionFactory ();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession ();
        String sqlCommand ="create table if not exists users (id bigint auto_increment," + " name varchar(256), lastName varchar(256), age bigint, primary key (id))";

        Transaction trx = session.beginTransaction();
        session.createSQLQuery(sqlCommand).executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.createSQLQuery(sqlCommand).executeUpdate();
        trx.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession ();
        session.beginTransaction ();
        session.save (new User (name,lastName,age));
        session.getTransaction ().commit ();
        session.close ();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession ();
        Query query = session.createQuery ("DELETE FROM User WHERE id = :id");
        query.setParameter ("id",id);
        session.close ();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession ();
        List<User> result = (List <User>)session.createQuery( "from User" ).list();
        session.close ();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession ();
        Transaction transaction = session.beginTransaction ();
        session.createQuery ("DELETE FROM User").executeUpdate ();
        transaction.commit ();
        session.close ();

    }
}
