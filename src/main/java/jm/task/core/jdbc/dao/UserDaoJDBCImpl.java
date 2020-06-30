package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
   private static Connection connection = Util.getMysqlConnection ();


    public void createUsersTable()  {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute ("create table if not exists users (id bigint auto_increment," + " name varchar(256), last_name varchar(256), age bigint, primary key (id))");
        }catch (Exception ex){
            ex.printStackTrace ();
        }
    }

    public void dropUsersTable() {
        try(Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users ");
        }catch (Exception ex){
            ex.printStackTrace ();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists users (id bigint auto_increment," + " name varchar(256), last_name varchar(256), age bigint, primary key (id))");
            stmt.execute ("INSERT INTO users SET name = '"+ name + "',"+"  last_name = "+"'"+ lastName +"', " +" age = "+ age);
        }catch (Exception ex){
            ex.printStackTrace ();
        }
    }

    public void removeUserById(long id) {
        try(Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM users WHERE id =" + id +"");
        }catch (Exception ex){
            ex.printStackTrace ();
        }

    }

    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<> ();
        try (Statement stmt = connection.createStatement ()) {
            stmt.execute ("create table if not exists users (id bigint auto_increment, name varchar(256), last_name varchar(256), age bigint, primary key (id))");
            ResultSet resultSet = stmt.executeQuery ("SELECT * FROM users");
            while (resultSet.next ()){
                User user = new User ();
                user.setId (resultSet.getLong (1));
                user.setName (resultSet.getString (2));
                user.setLastName (resultSet.getString (3));
                user.setAge (resultSet.getByte (4));
                userList.add (user);
            }
        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), last_name varchar(256), age bigint, primary key (id))");
            stmt.executeUpdate("TRUNCATE TABLE users");
        }catch (Exception ex){
            ex.printStackTrace ();
        }

    }
}
