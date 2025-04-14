package org.example.repository;

import org.example.exception.ConnectionException;
import org.example.exception.UserNotFoundException;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private ConnectionProvider connectionProvider;

    final String FIND_ALL_QUERY = "SELECT * FROM users";

    final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    final String CREATE_QUERY = "INSERT INTO users (name,surname, birth_date, login, password, is_blocked) VALUES (?, ?, ?, ?, ?, ?)";

    final String UPDATE_BY_ID_QUERY = "UPDATE users SET name = ?, surname = ?, birth_date = ?, login = ?, password = ?, is_blocked = ? WHERE id = ?";

    final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer userId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Boolean isBlocked = resultSet.getBoolean("is_blocked");

        return new User(userId, name, surname, birthDate, login, password, isBlocked);
    }

    private void setUserParametrs(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getLogin());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setBoolean(6, user.getIsBlocked());
        preparedStatement.executeUpdate();
        }

    public UserRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<User> findAll() throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        List <User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(createUserFromResultSet(resultSet));
        }

        return users;
    }

    public User findById(Integer id) throws SQLException, ConnectionException, UserNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new UserNotFoundException( "пользователь не найден");
        }

        return createUserFromResultSet(resultSet);
    }

    public User create(User user) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
        setUserParametrs(preparedStatement, user);

        return user;
    }

    public User update(User user) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
        preparedStatement.setInt(7, user.getId());
        setUserParametrs(preparedStatement, user);

        return user;
    }

    public void deleteById(Integer id) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
