package repository;

import org.example.exception.ConnectionException;
import org.example.models.Good;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GoodRepository {
    public static void findAll() throws SQLException, ConnectionException {
        String sql = "SELECT * FROM goods";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Good> goods = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            BigDecimal price = resultSet.getBigDecimal("price");
            Good good = new Good( id, name, description, price);
            goods.add(good);


            }

        for (Good good : goods) {
            System.out.println(good);
            }


        }
        public static void findById(Integer id) throws SQLException, ConnectionException {
            String sql = "SELECT * FROM goods WHERE id = ? ";
            Connection connection = (new ConnectionProvider()).getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                Good good = new Good(id1, name, description, price);
                System.out.println(good);
            }

        }

        public static void createGood(Good good) throws SQLException, ConnectionException {
            Connection connection = (new ConnectionProvider()).getConnection();
            String sql = "INSERT INTO goods (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement (sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setBigDecimal(3, good.getPrice());
            preparedStatement.executeUpdate();



        }

        public static void updateGood(Good good) throws SQLException, ConnectionException {
            Connection connection = (new ConnectionProvider()).getConnection();
            String sql = "UPDATE goods SET name = ?, description = ?, price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setBigDecimal(3, good.getPrice());
            preparedStatement.setInt(4, good.getId());
            preparedStatement.executeUpdate();
        }

        public static void deleteById(Integer id) throws SQLException, ConnectionException {
            String sql = "DELETE FROM users WHERE id = ? ";
            Connection connection = (new ConnectionProvider()).getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();


        }

    }

