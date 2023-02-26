package pl.britenet.store.model.builder;

import pl.britenet.store.model.*;

public final class BookBuilder {

    private final Book book;

    public BookBuilder() {
        this.book=new Book();
    }

    public BookBuilder setBook_id(int book_id){
        this.book.setBook_id(book_id);
        return this;
    }
    public BookBuilder setName(String name){
        this.book.setName(name);
        return this;
    }
    public BookBuilder setAuthor(String author){
        this.book.setAuthor(author);
        return this;
    }
    public BookBuilder setCategory(String category){
        this.book.setCategory(category);
        return this;
    }
    public BookBuilder setPrice(double price){
        this.book.setPrice(price);
        return this;
    }
    public BookBuilder setPublisher(String publisher){
        this.book.setPublisher(publisher);
        return this;
    }
    public BookBuilder setReleased(String released){
        this.book.setReleased(released);
        return this;
    }
    public BookBuilder setDescription(String description){
        this.book.setDescription(description);
        return this;
    }

    public Book getBook(){
        return this.book;
    }

}
