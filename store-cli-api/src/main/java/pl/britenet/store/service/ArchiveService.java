package pl.britenet.store.service;

import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArchiveService {
    private final DatabaseService databaseService;

    public ArchiveService(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    String sql="SELECT book.book_id AS book_id," +
            " book.name AS book_name," +
            " book.author AS book_author," +
            " book.category AS book_category," +
            " book.price AS book_price," +
            " book.publisher AS book_publisher," +
            " book.released AS book_released," +
            " book.description AS book_description," +
            " archive.commodity_id AS commodity_id," +
            " archive.customer_id AS archive_customer," +
            " archive.book_id AS archive_book," +
            " archive.number AS archive_number," +
            " archive.price AS archive_price," +
            " archive.created AS archive_created" +
            " FROM archive LEFT JOIN book ON archive.book_id=book.book_id";


    public Optional<Archive> getArchive(int commodity_id){
        String sqlWhere=String.format(sql+" WHERE archive.commodity_id=%d",commodity_id);
        Archive archive=this.databaseService.performSQL(sqlWhere,resultSet -> {
            try{
                if(resultSet.next()){
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

                    return new ArchiveBuilder()
                            .setCommodity_id(resultSet.getInt("commodity_id"))
                            .setCustomer_id(resultSet.getInt("archive_customer"))
                            .setBook_id(resultSet.getInt("archive_book"))
                            .setNumber(resultSet.getInt("archive_number"))
                            .setPrice(resultSet.getDouble("archive_price"))
                            .setCreated(resultSet.getString("archive_created"))
                            .setBook(book)
                            .getArchive();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(archive);
    }

    public List<Archive> getAllArchive(){
        List<Archive> archives=this.databaseService.performSQL(sql,resultSet -> {
            List<Archive> result = new ArrayList<>();
            try{
                while(resultSet.next()){
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
                            new ArchiveBuilder()
                                    .setCommodity_id(resultSet.getInt("commodity_id"))
                                    .setCustomer_id(resultSet.getInt("archive_customer"))
                                    .setBook_id(resultSet.getInt("archive_book"))
                                    .setNumber(resultSet.getInt("archive_number"))
                                    .setPrice(resultSet.getDouble("archive_price"))
                                    .setCreated(resultSet.getString("archive_created"))
                                    .setBook(book)
                                    .getArchive()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }


            return result;
        });
        return archives;
    }

    public List<Archive> getAllArchiveWhereCustomerId(int customer_id){
        String sqlWhere=String.format(sql+" WHERE archive.customer_id=%d",customer_id);
        List<Archive> archives=this.databaseService.performSQL(sqlWhere,resultSet -> {
            List<Archive> result = new ArrayList<>();
            try{
                while(resultSet.next()){
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
                            new ArchiveBuilder()
                                    .setCommodity_id(resultSet.getInt("commodity_id"))
                                    .setCustomer_id(resultSet.getInt("archive_customer"))
                                    .setBook_id(resultSet.getInt("archive_book"))
                                    .setNumber(resultSet.getInt("archive_number"))
                                    .setPrice(resultSet.getDouble("archive_price"))
                                    .setCreated(resultSet.getString("archive_created"))
                                    .setBook(book)
                                    .getArchive()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return archives;
    }

    public Optional<Integer> getMaxId(){
        String sql="SELECT MAX(archive.commodity_id) AS max FROM archive";
        Archive archive=this.databaseService.performSQL(sql,resultSet -> {
            try{
                if(resultSet.next()){
                    return new ArchiveBuilder().setCommodity_id(resultSet.getInt("max")).getArchive();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(archive.getCommodity_id());
    }

    public void insert(Archive archive){
        String dml=String.format("INSERT INTO archive (customer_id,book_id,number,price) VALUES (%d,%d,%d,%s)",
                archive.getCustomer_id(),
                archive.getBook_id(),
                archive.getNumber(),
                archive.getPrice());
        this.databaseService.performDML(dml);
    }

    public void update(Archive archive){
        String dml=String.format("UPDATE archive SET customer_id=%d,book_id=%d,number=%d,price=%f WHERE commodity_id=%d",
                archive.getCustomer_id(),
                archive.getBook_id(),
                archive.getNumber(),
                archive.getPrice());
        this.databaseService.performDML(dml);
    }

    public void delete(int commodity_id){
        String dml=String.format("DELETE FROM archive WHERE commodity_id=%d",commodity_id);
        this.databaseService.performDML(dml);
    }
}
