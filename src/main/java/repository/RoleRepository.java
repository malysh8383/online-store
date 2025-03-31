package repository;

import org.example.exception.ConnectionException;
import org.example.models.Order;
import org.example.models.Role;
import org.example.models.User;
import org.example.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoleRepository {
    public static void findAll() throws SQLException, ConnectionException {
        String sql = "SELECT * FROM roles";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Role> roles = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Role role = new Role(id, name);
            roles.add(role);


        }
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    public static void findById(Integer id) throws SQLException, ConnectionException {
        String sql = "SELECT * FROM roles WHERE id = ? ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Integer id1 = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Role role = new Role(id1, name);
            System.out.println(role);
        }
    }
    public static void createRole(Role role) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "INSERT INTO roles (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement (sql);
        preparedStatement.setString(1, role.getName());
        preparedStatement.executeUpdate();
    }

    public static void updateRole(Role role) throws SQLException, ConnectionException {
        Connection connection = (new ConnectionProvider()).getConnection();
        String sql = "UPDATE roles SET name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, role.getName());
        preparedStatement.setInt(2, role.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException, ConnectionException {
        String sql = "DELETE FROM roles WHERE id = ? ";
        Connection connection = (new ConnectionProvider()).getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

}
