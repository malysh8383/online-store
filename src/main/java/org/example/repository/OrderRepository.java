package org.example.repository;

import org.example.exception.ConnectionException;
import org.example.exception.OrderNotFoundException;
import org.example.models.Order;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private ConnectionProvider connectionProvider;

    final String FIND_ALL_QUERY = "SELECT * FROM orders";

    final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";

    final String CREATE_QUERY = "INSERT INTO orders (number, status, user_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

    final String UPDATE_BY_ID_QUERY = "UPDATE orders SET number = ?, status = ?, user_id = ?, updated_at = ? WHERE id = ?";

    final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer number = resultSet.getInt("number");
        String status = resultSet.getString("status");
        Integer userId = resultSet.getInt("user_id");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp updatedAt = resultSet.getTimestamp("updated_at");

         return new Order(id, number, status, userId, createdAt, updatedAt);
    }

    private void setOrderParametrs(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setInt(1, order.getNumber());
        preparedStatement.setString(2, order.getStatus());
        preparedStatement.setInt(3, order.getUserId());
        preparedStatement.executeUpdate();
    }

    public OrderRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Order> findAll() throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            orders.add(createOrderFromResultSet(resultSet));
        }

        return orders;
    }

    public Order findById(Integer id) throws SQLException, ConnectionException, OrderNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new OrderNotFoundException("Order not found");
        }

        return createOrderFromResultSet(resultSet);
    }

    public Order create(Order order) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement (CREATE_QUERY);
        setOrderParametrs(preparedStatement, order);
        preparedStatement.setTimestamp(4, order.getCreatedAt());
        preparedStatement.setTimestamp(5, order.getUpdatedAt());

        return order;
    }

    public Order update(Order order) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
        preparedStatement.setInt(5, order.getId());
        preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
        setOrderParametrs(preparedStatement, order);

        return order;
    }

    public void deleteById(Integer id) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
