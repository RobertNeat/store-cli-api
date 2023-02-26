package pl.britenet.store.model.builder;

import pl.britenet.store.model.*;

public final class CommodityBuilder {
    private final Commodity commodity;

    public CommodityBuilder() {
        this.commodity=new Commodity();
    }

    public CommodityBuilder setCommodity_id(int commodity_id){
        this.commodity.setCommodity_id(commodity_id);
        return this;
    }
    public CommodityBuilder setBasket_id(int basket_id){
        this.commodity.setBasket_id(basket_id);
        return this;
    }
    public CommodityBuilder setBook_id(int book_id){
        this.commodity.setBook_id(book_id);
        return this;
    }
    public CommodityBuilder setNumber(int number){
        this.commodity.setNumber(number);
        return this;
    }

    public CommodityBuilder setBasket(Basket basket){
        this.commodity.setBasket(basket);
        return this;
    }
    public CommodityBuilder setBook(Book book){
        this.commodity.setBook(book);
        return this;
    }

    public Commodity getCommodity(){
        return this.commodity;
    }
}
