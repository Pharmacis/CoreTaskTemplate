package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver ((Driver) Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ());
            Connection connection = DriverManager.getConnection ("jdbc:mysql://127.0.0.1:3306/db_example?useSSL=false","root","1234");
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace ();
            throw new IllegalStateException ();
        }
    }

    public static SessionFactory getSessionFactory(){
       try{
           Configuration configuration = new Configuration ();
           configuration.addAnnotatedClass (User.class);
           configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
           configuration.setProperty("hibernate.connection.url",
                   "jdbc:mysql://127.0.0.1:3306/db_example?useSSL=false");
           configuration.setProperty("hibernate.connection.username", "root");
           configuration.setProperty("hibernate.connection.password", "1234");
           configuration.setProperty("hibernate.show_sql", "true");
           StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
           builder.applySettings(configuration.getProperties());
           ServiceRegistry serviceRegistry = builder.build();
           return configuration.buildSessionFactory(serviceRegistry);
       }catch (Exception e){
           e.printStackTrace ();
           throw new IllegalStateException ();
       }
    }
}



