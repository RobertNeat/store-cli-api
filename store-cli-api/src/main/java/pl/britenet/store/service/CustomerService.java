package pl.britenet.store.service;

import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final DatabaseService databaseService;

    public CustomerService(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    String sql="SELECT customer.customer_id AS customer_id," +
            " customer.name AS customer_name," +
            " customer.surname AS customer_surname," +
            " customer.adress AS customer_adress," +
            " customer.contact AS customer_contact," +
            " customer.username AS customer_username," +
            " customer.passwrd AS customer_password" +
            " FROM customer";

    public Optional<Customer> getCustomer(int customer_id){
        String sqlWhere=String.format(sql+" WHERE customer.customer_id=%d",customer_id);
        Customer customer=this.databaseService.performSQL(sqlWhere,resultSet -> {
            try{
                if(resultSet.next()){
                    return new CustomerBuilder()
                            .setCustomerId(resultSet.getInt("customer_id"))
                            .setName(resultSet.getString("customer_name"))
                            .setSurname(resultSet.getString("customer_surname"))
                            .setAdress(resultSet.getString("customer_adress"))
                            .setContact(resultSet.getString("customer_contact"))
                            .setUsername(resultSet.getString("customer_username"))
                            .setPassword(resultSet.getString("customer_password"))
                            .getCustomer();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(customer);
    }

    public Optional<Customer> getCustomer(String username,String password){
        String sql=String.format("SELECT * FROM customer WHERE customer.username=\"%s\" AND customer.passwrd=\"%s\"",username,password);
        Customer customer=this.databaseService.performSQL(sql,resultSet -> {
            try{
                if(resultSet.next()){
                    return new CustomerBuilder()
                            .setCustomerId(resultSet.getInt("customer_id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setAdress(resultSet.getString("adress"))
                            .setContact(resultSet.getString("contact"))
                            .setUsername(resultSet.getString("username"))
                            .setPassword(resultSet.getString("passwrd"))
                            .getCustomer();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(customer);
    }

    public List<Customer> getAllCustomer(){
        List<Customer> customers=this.databaseService.performSQL(sql,resultSet -> {
            List<Customer> result = new ArrayList<>();
            try{
                while(resultSet.next()){
                    result.add(
                            new CustomerBuilder()
                                    .setCustomerId(resultSet.getInt("customer_id"))
                                    .setName(resultSet.getString("customer_name"))
                                    .setSurname(resultSet.getString("customer_surname"))
                                    .setAdress(resultSet.getString("customer_adress"))
                                    .setContact(resultSet.getString("customer_contact"))
                                    .setUsername(resultSet.getString("customer_username"))
                                    .setPassword(resultSet.getString("customer_password"))
                                    .getCustomer()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return customers;
    }

    public Optional<Integer> getMaxId(){
        String sql="SELECT MAX(customer.customer_id) AS max FROM customer";
        Customer customer=this.databaseService.performSQL(sql,resultSet -> {
            try {
                if (resultSet.next()) {
                    return new CustomerBuilder().setCustomerId(resultSet.getInt("max")).getCustomer();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(customer.getCustomer_id());
    }

    public void insert(Customer customer){
        String dml=String.format("INSERT INTO customer (name,surname,adress,contact,username,passwrd) " +
                        "VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",
                customer.getName(),
                customer.getSurname(),
                customer.getAdress(),
                customer.getContact(),
                customer.getUsername(),
                customer.getPassword()
        );
        this.databaseService.performDML(dml);
    }

    public void update(Customer customer){
        String dml=String.format("UPDATE customer SET adress=\"%s\"" +
                        " WHERE customer_id=%d",
                customer.getAdress(),
                customer.getCustomer_id()
        );
        this.databaseService.performDML(dml);
    }

    /*
    public void update(Customer customer){
        String dml=String.format("UPDATE customer SET name=\"%s\", surname=\"%s\", adress=\"%s\"," +
                        " contact=\"%s\", username=\"%s\", passwrd=\"%s\" " +
                        "WHERE customer_id=%d",
                customer.getName(),
                customer.getSurname(),
                customer.getAdress(),
                customer.getContact(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getCustomer_id()
        );
        this.databaseService.performDML(dml);
    }
    */

    public void delete(int customer_id){
        String dml=String.format("DELETE FROM customer WHERE customer_id=%d",customer_id);
        this.databaseService.performDML(dml);
    }

}
