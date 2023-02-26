package pl.britenet.store.service;

import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommodityService {
    private final DatabaseService databaseService;

    public CommodityService(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    String sql="SELECT basket.basket_id AS basket_id," +
            " basket.payment AS basket_payment," +
            " basket.created AS basket_created," +
            " commodity.commodity_id AS commodity_id," +
            " commodity.basket_id AS commodity_basket," +
            " commodity.book_id AS commodity_book," +
            " commodity.number AS commodity_number," +
            " book.book_id AS book_id," +
            " book.name AS book_name," +
            " book.author AS book_author," +
            " book.category AS book_category," +
            " book.price AS book_price," +
            " book.publisher AS book_publisher," +
            " book.released AS book_released," +
            " book.description AS book_description " +
            " FROM commodity LEFT JOIN basket ON commodity.basket_id=basket.basket_id" +
            " LEFT JOIN book ON commodity.book_id=book.book_id";

    public Optional<Commodity> getCommodity(int commodity_id){
        String sqlWhere=String.format(sql+" WHERE commodity.commodity_id=%d",commodity_id);
        Commodity commodity=this.databaseService.performSQL(sqlWhere,resultSet -> {
            try{
                if(resultSet.next()){
                    Basket basket = new BasketBuilder()
                            .setBasket_id(resultSet.getInt("basket_id"))
                            .setPayment(resultSet.getString("basket_payment"))
                            .setCreated(resultSet.getString("basket_created"))
                            .getBasket();

                    Book book= new BookBuilder()
                            .setBook_id(resultSet.getInt("book_id"))
                            .setName(resultSet.getString("book_name"))
                            .setAuthor(resultSet.getString("book_author"))
                            .setCategory(resultSet.getString("book_category"))
                            .setPrice(resultSet.getDouble("book_price"))
                            .setPublisher(resultSet.getString("book_publisher"))
                            .setReleased(resultSet.getString("book_released"))
                            .setDescription(resultSet.getString("book_description"))
                            .getBook();

                    return new CommodityBuilder()
                            .setCommodity_id(resultSet.getInt("commodity_id"))
                            .setBasket_id(resultSet.getInt("commodity_basket"))
                            .setBook_id(resultSet.getInt("commodity_book"))
                            .setNumber(resultSet.getInt("commodity_number"))
                            .setBasket(basket)
                            .setBook(book)
                            .getCommodity();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(commodity);
    }

    public List<Commodity> getAllCommodity(){
        List<Commodity> commodities = this.databaseService.performSQL(sql,resultSet -> {
            List<Commodity> result = new ArrayList<>();
            try{
                while(resultSet.next()){
                    Basket basket = new BasketBuilder()
                            .setBasket_id(resultSet.getInt("basket_id"))
                            .setPayment(resultSet.getString("basket_payment"))
                            .setCreated(resultSet.getString("basket_created"))
                            .getBasket();

                    Book book= new BookBuilder()
                            .setBook_id(resultSet.getInt("book_id"))
                            .setName(resultSet.getString("book_name"))
                            .setAuthor(resultSet.getString("book_author"))
                            .setCategory(resultSet.getString("book_category"))
                            .setPrice(resultSet.getDouble("book_price"))
                            .setPublisher(resultSet.getString("book_publisher"))
                            .setReleased(resultSet.getString("book_released"))
                            .setDescription(resultSet.getString("book_description"))
                            .getBook();

                    result.add(
                            new CommodityBuilder()
                                    .setCommodity_id(resultSet.getInt("commodity_id"))
                                    .setBasket_id(resultSet.getInt("commodity_basket"))
                                    .setBook_id(resultSet.getInt("commodity_book"))
                                    .setNumber(resultSet.getInt("commodity_number"))
                                    .setBasket(basket)
                                    .setBook(book)
                                    .getCommodity()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return commodities;
    }

    //For commodity grouping in basket (select basket the same book positions and update them into one position in basket)
    public List<Commodity> getAllCommodityWhereBasketId(int basket_id){
        String sqlWhere=String.format(sql+" WHERE commodity.basket_id=%d",basket_id);
        List<Commodity> commodities = this.databaseService.performSQL(sqlWhere, resultSet -> {
            List<Commodity> result = new ArrayList<>();
            try{
                while(resultSet.next()){
                    Basket basket = new BasketBuilder()
                            .setBasket_id(resultSet.getInt("basket_id"))
                            .setPayment(resultSet.getString("basket_payment"))
                            .setCreated(resultSet.getString("basket_created"))
                            .getBasket();

                    Book book= new BookBuilder()
                            .setBook_id(resultSet.getInt("book_id"))
                            .setName(resultSet.getString("book_name"))
                            .setAuthor(resultSet.getString("book_author"))
                            .setCategory(resultSet.getString("book_category"))
                            .setPrice(resultSet.getDouble("book_price"))
                            .setPublisher(resultSet.getString("book_publisher"))
                            .setReleased(resultSet.getString("book_released"))
                            .setDescription(resultSet.getString("book_description"))
                            .getBook();

                    result.add(
                            new CommodityBuilder()
                                    .setCommodity_id(resultSet.getInt("commodity_id"))
                                    .setBasket_id(resultSet.getInt("commodity_basket"))
                                    .setBook_id(resultSet.getInt("commodity_book"))
                                    .setNumber(resultSet.getInt("commodity_number"))
                                    .setBasket(basket)
                                    .setBook(book)
                                    .getCommodity()
                    );

                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return commodities;
    }

    public Optional<Integer> getMaxId(){
        String sql="SELECT MAX(commodity.commodity_id) AS max FROM commodity";
        Commodity commodity=this.databaseService.performSQL(sql,resultSet -> {
            try{
                if(resultSet.next()){
                    return new CommodityBuilder().setCommodity_id(resultSet.getInt("max")).getCommodity();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(commodity.getCommodity_id());
    }

    public void insert(Commodity commodity){
        String dml=String.format("INSERT INTO commodity (basket_id,book_id,number) " +
                        "VALUES (%d,%d,%d)",
                commodity.getBasket_id(),
                commodity.getBook_id(),
                commodity.getNumber());
        this.databaseService.performDML(dml);
    }

    public void update(Commodity commodity){
        String dml=String.format("UPDATE commodity SET number=%d " +
                        "WHERE commodity_id=%d",
                commodity.getNumber(),
                commodity.getCommodity_id());
        this.databaseService.performDML(dml);
    }

    /*
        public void update(Commodity commodity){
        String dml=String.format("UPDATE commodity SET basket_id=%d, book_id=%d, number=%d " +
                        "WHERE commodity_id=%d",
                commodity.getBasket_id(),
                commodity.getBook_id(),
                commodity.getNumber(),
                commodity.getCommodity_id());
        this.databaseService.performDML(dml);
    }
    */

    public void delete(int commodity_id){
        String dml=String.format("DELETE FROM commodity WHERE commodity_id=%d",commodity_id);
        this.databaseService.performDML(dml);
    }

}
