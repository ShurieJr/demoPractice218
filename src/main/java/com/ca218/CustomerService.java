package com.ca218;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    //JdbcTemplate
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJDBC;

    public CustomerService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJDBC) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedJDBC = namedJDBC;
    }

    public List<Customer> getAllCustomers() {
        String sqlQuery = "SELECT * from customer";
        return jdbcTemplate.query(sqlQuery,
                new BeanPropertyRowMapper<>(Customer.class));
    }

//    public Customer getCustomerById(int id) {
//        String sqlQuery = "SELECT * from customer where id = ?";
//        return jdbcTemplate.queryForObject(sqlQuery,
//                new Object[]{id},
//                new BeanPropertyRowMapper<>(Customer.class));
//    }

    public Customer getCustomerById(int id) {
        String sqlQuery = "SELECT * from customer where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        return namedJDBC.queryForObject(sqlQuery,
                parameters,
                new BeanPropertyRowMapper<>(Customer.class));
    }

    //    public void deleteCustomer(int id) {
//        String sqlQuery = "DELETE FROM customer WHERE id = ?";
//        jdbcTemplate.update(sqlQuery , id);
//    }
    public void deleteCustomer(int id) {
        String sqlQuery = "DELETE FROM customer WHERE id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedJDBC.update(sqlQuery, parameters);
    }
//
//    public void updateCustomer(Customer customer) {
//        String sqlQuery = "UPDATE customer SET name = ? WHERE id = ?";
//        jdbcTemplate.update(sqlQuery, customer.getName(), customer.getId());
//    }
public void updateCustomer(Customer customer) {
    String sqlQuery = "UPDATE customer SET name = :name WHERE id = :id";
    MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", customer.getId())
            .addValue("name", customer.getName());
    namedJDBC.update(sqlQuery, parameters);
}

//    public void insertCustomer(Customer customer) {
//        String sqlQuery = "INSERT INTO customer  VALUES (?,?)";
//        jdbcTemplate.update(sqlQuery, customer.getId(), customer.getName());
//    }

    public void insertCustomer(Customer customer) {
        String sqlQuery = "INSERT INTO customer  VALUES (:id,:name)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", customer.getId())
                .addValue("name", customer.getName());
        namedJDBC.update(sqlQuery, parameters);
    }
}
