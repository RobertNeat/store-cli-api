package pl.britenet.store.model;

public final class Archive {

    private int commodity_id;
    private int customer_id;
    private int book_id;
    private int number;
    private double price;
    private String created;


    Book book;

    public Archive() {}


    public int getCommodity_id() {
        return commodity_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public String getCreated() {
        return created;
    }

    public Book getBook() {
        return book;
    }


    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
