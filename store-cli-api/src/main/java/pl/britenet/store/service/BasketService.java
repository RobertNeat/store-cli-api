package pl.britenet.store.service;

import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketService {
    private final DatabaseService databaseService;

    public BasketService(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    String sql="SELECT  basket.basket_id AS basket_id," +
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
            " book.description AS book_description" +
            " FROM basket LEFT JOIN commodity ON basket.basket_id=commodity.basket_id" +
            " LEFT JOIN book ON commodity.book_id=book.book_id" ;

    public Optional<Basket> getBasket(int basket_id){
        String sqlWhere=String.format(sql+" WHERE basket.basket_id=%d",basket_id);
        Basket basket= this.databaseService.performSQL(sqlWhere,resultSet -> {
            try{
                if(resultSet.next()){
                    Commodity commodity=new CommodityBuilder()
                            .setCommodity_id(resultSet.getInt("commodity_id"))
                            .setBasket_id(resultSet.getInt("commodity_basket"))
                            .setBook_id(resultSet.getInt("commodity_book"))
                            .setNumber(resultSet.getInt("commodity_number"))
                            .getCommodity();

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

                    return new BasketBuilder()
                            .setBasket_id(resultSet.getInt("basket_id"))
                            .setPayment(resultSet.getString("basket_payment"))
                            .setCreated(resultSet.getString("basket_created"))
                            .setCommodity(commodity)
                            .setBook(book)
                            .getBasket();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(basket);
    }


    public List<Basket> getAllBasket(){
        List<Basket> baskets= this.databaseService.performSQL(sql,resultSet -> {
            List<Basket> result = new ArrayList<>();
            try{
                while(resultSet.next()){
                    Commodity commodity=new CommodityBuilder()
                            .setCommodity_id(resultSet.getInt("commodity_id"))
                            .setBasket_id(resultSet.getInt("commodity_basket"))
                            .setBook_id(resultSet.getInt("commodity_book"))
                            .setNumber(resultSet.getInt("commodity_number"))
                            .getCommodity();

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
                            new BasketBuilder()
                                    .setBasket_id(resultSet.getInt("basket_id"))
                                    .setPayment(resultSet.getString("basket_payment"))
                                    .setCreated(resultSet.getString("basket_created"))
                                    .setCommodity(commodity)
                                    .setBook(book)
                                    .getBasket()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return baskets;
    }

    public Optional<Integer> getMaxId(){
        String sql="SELECT MAX(basket.basket_id) AS max FROM basket";
        Basket basket=this.databaseService.performSQL(sql,resultSet -> {
            try{
                if(resultSet.next()){
                    return new BasketBuilder().setBasket_id(resultSet.getInt("max")).getBasket();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(basket.getBasket_id());
    }

    public void insert(Basket basket){
        String dml=String.format("INSERT INTO basket (basket_id) " +
                        "VALUES (%d)",
                basket.getBasket_id());
        this.databaseService.performDML(dml);
    }

    public void update(Basket basket){
        String dml=String.format("UPDATE basket SET payment=\"%s\" " +
                        "WHERE basket_id=%d",
                basket.getPayment(),
                basket.getBasket_id());
        this.databaseService.performDML(dml);
    }

    public void delete(int basket_id){
        String dml= String.format("DELETE FROM basket WHERE basket_id=%d",basket_id);
        this.databaseService.performDML(dml);
    }
}
