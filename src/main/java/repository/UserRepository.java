package repository;

import org.example.exception.ConnectionException;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserRepository {
    public static void findAll() throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            User user = new User(id, name, surname, birthDate, login, password, isBlocked);
            users.add(user);


        }

        for (User user : users) {
            System.out.println(user);
        }


    }

    public static void findById(Integer id) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM users WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            User user = new User(id, name, surname, birthDate, login, password, isBlocked);
            System.out.println(user);
        }

    }

    public static void createUser(User user) throws SQLException, ConnectionException {
        String sql = "INSERT INTO users (name, surname, birth_date, login, password, is_blocked) VALUES ()";
        Connection connection = new ConnectionProvider().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setBoolean(6, user.getIsBlocked());
        preparedStatement.executeUpdate();


    }

    public static void updateUser(User user) throws SQLException, ConnectionException {
        String sql = "UPDATE users SET name, surname, birth_date, login, password, is_blocked WHERE id = ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setBoolean(6, user.getIsBlocked());
        preparedStatement.executeUpdate();

    }

    public static void deleteById(Integer id) throws SQLException, ConnectionException {
        String sql = "DELETE FROM users WHERE id = " + id;
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();


        }
    }
