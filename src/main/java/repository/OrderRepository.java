package repository;

import org.example.exception.ConnectionException;
import org.example.models.Order;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderRepository {
    public static void findAll() throws SQLException, ConnectionException {
        String sql = "SELECT * FROM orders";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer number = resultSet.getInt("number");
            String status = resultSet.getString("status");
            Integer userId = resultSet.getInt("user_id");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp updatedAt = resultSet.getTimestamp("updated_at");
            Order order = new Order(id, number, status, userId, createdAt, updatedAt);
            orders.add(order);


        }

        for (Order order : orders) {
            System.out.println(order);
        }

    }
    public static void findById(Integer id) throws SQLException, ConnectionException {
        String sql = "SELECT * FROM orders WHERE id = ? ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Integer id1 = resultSet.getInt("id");
            Integer number = resultSet.getInt("number");
            String status = resultSet.getString("status");
            Integer userId = resultSet.getInt("user_id");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp updatedAt = resultSet.getTimestamp("updated_at");
            Order order = new Order(id1, number, status, userId, createdAt, updatedAt);
            System.out.println(order);
        }

    }
    public static void createOrder(Order order) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "INSERT INTO orders (number, status, user_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setInt(1, order.getNumber());
        preparedStatement.setString(2, order.getStatus());
        preparedStatement.setInt(3, order.getUserId());
        preparedStatement.setTimestamp(4, order.getCreatedAt());
        preparedStatement.setTimestamp(5, order.getUpdatedAt());
        preparedStatement.executeUpdate();
    }

    public static void updateOrder(Order order) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "UPDATE orders SET number = ?, status = ?, user_id = ?, updated_at = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, order.getNumber());
        preparedStatement.setString(2, order.getStatus());
        preparedStatement.setInt(3, order.getUserId());
        preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setInt(5, order.getId());
        preparedStatement.executeUpdate();
    }
    public static void deleteById(Integer id) throws SQLException, ConnectionException {
        String sql = "DELETE FROM orders WHERE id = ? ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();


    }


}
