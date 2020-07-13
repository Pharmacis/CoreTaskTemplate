package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl (  new UserDaoJDBCImpl ());
        userService.createUsersTable ();
        userService.saveUser ("Пётр","Сидоров", (byte) 18);
        System.out.println ("User с именем – Пётр добавлен в базу данных");
        userService.saveUser ("Вася","Пупкин", (byte) 22);
        System.out.println ("User с именем – Вася добавлен в базу данных");
        userService.saveUser ("Анна","Коренина", (byte) 33);
        System.out.println ("User с именем – Анна добавлен в базу данных");
        userService.saveUser ("Лев","Толстой", (byte) 80);
        System.out.println ("User с именем – Лев добавлен в базу данных");
        for(User user:userService.getAllUsers ()){
            System.out.println (user.toString ());
        }

        userService.cleanUsersTable ();
        userService.dropUsersTable ();
    }
}
