package pl.britenet.store.model.builder;

import pl.britenet.store.model.*;

public final class ArchiveBuilder {
    private final Archive archive;

    public ArchiveBuilder() {
        this.archive=new Archive();
    }

    public ArchiveBuilder setCommodity_id(int commodity_id){
        this.archive.setCommodity_id(commodity_id);
        return this;
    }

    public ArchiveBuilder setCustomer_id(int customer_id){
        this.archive.setCustomer_id(customer_id);
        return this;
    }

    public ArchiveBuilder setBook_id(int book_id){
        this.archive.setBook_id(book_id);
        return this;
    }

    public ArchiveBuilder setNumber(int number){
        this.archive.setNumber(number);
        return this;
    }

    public ArchiveBuilder setPrice(double price){
        this.archive.setPrice(price);
        return this;
    }

    public ArchiveBuilder setCreated(String created){
        this.archive.setCreated(created);
        return this;
    }

    public ArchiveBuilder setBook(Book book){
        this.archive.setBook(book);
        return this;
    }

    public Archive getArchive(){
        return  this.archive;
    }
}
