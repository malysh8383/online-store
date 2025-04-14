package org.example.repository;

import org.example.exception.ConnectionException;
import org.example.exception.GoodNotFoundException;
import org.example.models.Good;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoodRepository {

    private ConnectionProvider connectionProvider;

    final String FIND_ALL_QUERY = "SELECT * FROM goods";

    final String FIND_BY_ID_QUERY = "SELECT * FROM goods WHERE id = ?";

    final String CREATE_QUERY = "INSERT INTO goods (name, description, price) VALUES (?, ?, ?)";

    final String UPDATE_BY_ID_QUERY = "UPDATE goods SET name = ?, description = ?, price = ? WHERE id = ?";

    final String DELETE_BY_ID_QUERY = "DELETE FROM goods WHERE id = ?";

    private Good createGoodFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        return new Good(id, name, description, price);
    }

    private void setGoodParametrs(PreparedStatement preparedStatement, Good good) throws SQLException {
        preparedStatement.setString(1, good.getName());
        preparedStatement.setString(2, good.getDescription());
        preparedStatement.setBigDecimal(3, good.getPrice());
        preparedStatement.executeUpdate();
    }

    public GoodRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Good> findAll() throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        List<Good> goods = new ArrayList<>();

        while (resultSet.next()) {
            goods.add(createGoodFromResultSet(resultSet));
        }

        return goods;
    }

    public Good findById(Integer id) throws SQLException, ConnectionException, GoodNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            throw new GoodNotFoundException("товар не найден");
        }

        return createGoodFromResultSet(resultSet);
    }

    public Good create(Good good) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
        setGoodParametrs(preparedStatement, good);

        return good;
    }

    public Good update(Good good) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
        preparedStatement.setInt(4, good.getId());
        setGoodParametrs(preparedStatement, good);

        return good;
    }

    public void deleteById(Integer id) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}

