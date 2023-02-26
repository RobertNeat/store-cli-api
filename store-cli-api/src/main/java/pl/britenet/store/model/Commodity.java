package pl.britenet.store.model;

public final class Commodity {
    private int commodity_id;
    private int basket_id;
    private int book_id;
    private int number;

    Basket basket;
    Book book;

    public Commodity() {}

    public int getCommodity_id() {
        return commodity_id;
    }
    public int getBasket_id() {
        return basket_id;
    }
    public int getBook_id() {
        return book_id;
    }
    public int getNumber() {
        return number;
    }
    public Basket getBasket() {
        return basket;
    }
    public Book getBook() {
        return book;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }
    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setBasket(Basket basket) {
        this.basket = basket;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
