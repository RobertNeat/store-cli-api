package pl.britenet.store.model;

public final class Basket {
    private int basket_id;
    private String payment;
    private String created;

    Commodity commodity;
    Book book;

    public Basket() {}

    public int getBasket_id() {
        return basket_id;
    }
    public String getPayment() {
        return payment;
    }
    public String getCreated() {
        return created;
    }
    public Commodity getCommodity() {
        return commodity;
    }
    public Book getBook() {
        return book;
    }

    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
    public void setBook(Book book) {
        this.book = book;
    }

}
