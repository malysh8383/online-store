package org.example.repository;

import org.example.exception.ConnectionException;
import org.example.models.Order;
import org.example.models.Role;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import javax.management.relation.RoleNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    private ConnectionProvider connectionProvider;

    public RoleRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    private Role createRoleFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Role role = new Role(id, name);

        return new Role(id, name);
    }

    final String FIND_ALL_QUERY = "SELECT * FROM roles";

    final String FIND_BY_ID_QUERY = "SELECT * FROM roles WHERE id = ?";

    final String CREATE_QUERY = "INSERT INTO roles (name) VALUES (?)";

    final String UPDATE_BY_ID_QUERY = "UPDATE roles SET name = ? WHERE id = ?";

    final String DELETE_BY_ID_QUERY = "DELETE FROM roles WHERE id = ?";

    private void setRoleParametrs(PreparedStatement preparedStatement, Role role) throws SQLException {
        preparedStatement.setString(1, role.getName());
        preparedStatement.executeUpdate();
    }

    public List<Role> findAll() throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        List<Role> roles = new ArrayList<>();

        while (resultSet.next()) {
            roles.add(createRoleFromResultSet(resultSet));
        }

        return roles;
    }

    public Role findById(Integer id) throws SQLException, ConnectionException, RoleNotFoundException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            throw new RoleNotFoundException();
        }

        return createRoleFromResultSet(resultSet);
    }

    public Role create(Role role) throws SQLException, ConnectionException {
        Connection connection = connectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement (CREATE_QUERY);
        setRoleParametrs(preparedStatement, role);

        return role;
    }

    public Role updateRole(Role role) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
        preparedStatement.setInt(2, role.getId());
        setRoleParametrs(preparedStatement, role);

        return role;
    }

    public void deleteById(Integer id) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
