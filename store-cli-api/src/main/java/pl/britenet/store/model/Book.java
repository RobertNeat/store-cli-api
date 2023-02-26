package pl.britenet.store.model;

public final class Book {
    private int book_id;
    private String name;
    private String author;
    private String category;
    private double  price;
    private String publisher;
    private String released;
    private String description;


    public Book() {}

    public int getBook_id() {
        return book_id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getReleased() {
        return released;
    }
    public String getDescription() {
        return description;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setReleased(String released) {
        this.released = released;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
