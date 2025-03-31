package org.example;

import org.example.exception.ConnectionException;
import org.example.models.Good;
import org.example.models.Order;
import org.example.models.Role;
import org.example.models.User;
import org.example.util.ConnectionProvider;
import repository.GoodRepository;
import repository.OrderRepository;
import repository.RoleRepository;
import repository.UserRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, ConnectionException {
        RoleRepository roleRepository = new RoleRepository();
        Role role = new Role(2,"eieiieeieie");
        RoleRepository.updateRole(role);




    }
}