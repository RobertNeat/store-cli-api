package pl.britenet.store.model;

public final class Customer {
    private int customer_id;
    private String name;
    private String surname;
    private String adress;
    private String contact;
    private String username;
    private String password;


    public Customer() {}

    public int getCustomer_id() {
        return customer_id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getAdress() {
        return adress;
    }
    public String getContact() {
        return contact;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
