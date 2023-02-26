package pl.britenet.store.model.builder;

import pl.britenet.store.model.*;

public final class CustomerBuilder {
    private final Customer customer;

    public CustomerBuilder() {
        this.customer=new Customer();
    }

    public CustomerBuilder setCustomerId(int id){
        this.customer.setCustomer_id(id);
        return this;
    }
    public CustomerBuilder setName(String name){
        this.customer.setName(name);
        return this;
    }
    public CustomerBuilder setSurname(String surname){
        this.customer.setSurname(surname);
        return this;
    }
    public CustomerBuilder setAdress(String adress){
        this.customer.setAdress(adress);
        return this;
    }
    public CustomerBuilder setContact(String contact){
        this.customer.setContact(contact);
        return this;
    }
    public CustomerBuilder setUsername(String username){
        this.customer.setUsername(username);
        return this;
    }
    public CustomerBuilder setPassword(String password){
        this.customer.setPassword(password);
        return this;
    }

    public Customer getCustomer(){
        return this.customer;
    }
}
