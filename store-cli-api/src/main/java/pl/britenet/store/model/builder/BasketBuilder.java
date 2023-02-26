package pl.britenet.store.model.builder;

import pl.britenet.store.model.*;

public final class BasketBuilder {

    private final Basket basket;

    public BasketBuilder() {
        this.basket=new Basket();
    }

    public BasketBuilder setBasket_id(int basket_id){
        this.basket.setBasket_id(basket_id);
        return this;
    }
    public BasketBuilder setPayment(String payment){
        this.basket.setPayment(payment);
        return this;
    }
    public BasketBuilder setCreated(String created){
        this.basket.setCreated(created);
        return this;
    }

    public BasketBuilder setCommodity(Commodity commodity){
        this.basket.setCommodity(commodity);
        return this;
    }
    public BasketBuilder setBook(Book book){
        this.basket.setBook(book);
        return this;
    }

    public Basket getBasket(){
        return this.basket;
    }
}
