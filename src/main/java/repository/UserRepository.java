package repository;

import org.example.exception.ConnectionException;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserRepository {
    public static void findAll() throws SQLException, ConnectionException {
        String sql = "SELECT * FROM users";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<User> users = new ArrayList<>();

        while (resultSet.next()) {
            Integer userId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            User user = new User( userId, name, surname, birthDate, login, password, isBlocked);
            users.add(user);


        }

        for (User user : users) {
            System.out.println(user);
        }


    }

    public static void findById(Integer id) throws SQLException, ConnectionException {
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Integer userId1 = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Boolean isBlocked = resultSet.getBoolean("is_blocked");
            User user = new User( userId1, name, surname, birthDate, login, password, isBlocked);
            System.out.println(user);
        }

    }

    public static void createUser(User user) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "INSERT INTO users (name,surname, birth_date, login, password, is_blocked) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setBoolean(6,user.getIsBlocked());
        preparedStatement.executeUpdate();



    }

    public static void updateUser(User user) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "UPDATE users SET name = ?, surname = ?, birth_date = ?, login = ?, password = ?, is_blocked = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setBoolean(6, user.getIsBlocked());
        preparedStatement.setInt(7, user.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException, ConnectionException {
        String sql = "DELETE FROM users WHERE id = ? ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();


        }
        
    }
