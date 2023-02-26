package pl.britenet.store.service;

import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final DatabaseService databaseService;

    public BookService(DatabaseService databaseService) {
        this.databaseService=databaseService;
    }

    String sql="SELECT book.book_id AS book_id," +
            " book.name AS book_name," +
            " book.author AS book_author," +
            " book.category AS book_category," +
            " book.price AS book_price," +
            " book.publisher AS book_publisher," +
            " book.released AS book_released," +
            " book.description AS book_description" +
            " FROM book";

    public Optional<Book> getBook(int book_id){
        String sqlWhere=String.format(sql+" WHERE book.book_id=%d",book_id);
        Book book=this.databaseService.performSQL(sqlWhere,resultSet -> {
            try{
                if(resultSet.next()){
                    return new BookBuilder()
                            .setBook_id(resultSet.getInt("book_id"))
                            .setName(resultSet.getString("book_name"))
                            .setAuthor(resultSet.getString("book_author"))
                            .setCategory(resultSet.getString("book_category"))
                            .setPrice(resultSet.getDouble("book_price"))
                            .setPublisher(resultSet.getString("book_publisher"))
                            .setReleased(resultSet.getString("book_released"))
                            .setDescription(resultSet.getString("book_description"))
                            .getBook();

                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(book);
    }


    public List<Book> getAllBook(){
        List<Book> books=this.databaseService.performSQL(sql,resultSet -> {
            List<Book> result=new ArrayList<>();
            try{
                while(resultSet.next()){
                    result.add(
                            new BookBuilder()
                                    .setBook_id(resultSet.getInt("book_id"))
                                    .setName(resultSet.getString("book_name"))
                                    .setAuthor(resultSet.getString("book_author"))
                                    .setCategory(resultSet.getString("book_category"))
                                    .setPrice(resultSet.getDouble("book_price"))
                                    .setPublisher(resultSet.getString("book_publisher"))
                                    .setReleased(resultSet.getString("book_released"))
                                    .setDescription(resultSet.getString("book_description"))
                                    .getBook()
                    );
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return result;
        });
        return books;
    }

    public Optional<Integer> getMaxId(){
        String sql="SELECT MAX(book.book_id) AS max FROM book";
        Book book=this.databaseService.performSQL(sql,resultSet -> {
            try{
                if(resultSet.next()){
                    return new BookBuilder().setBook_id(resultSet.getInt("max")).getBook();
                }
            }
            catch(SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        });
        return Optional.of(book.getBook_id());
    }

    public void insert(Book book){
        String dml=String.format("INSERT INTO book (name,author,category,price,publisher,description)" +
                        " VALUES (\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\")",
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getPrice(),
                book.getPublisher(),
                book.getDescription());
        this.databaseService.performDML(dml);
    }

    public void update(Book book){
        String dml=String.format("UPDATE book SET name=\"%s\"," +
                        "author=\"%s\"," +
                        "category=\"%s\"," +
                        "price=%s," +
                        "publisher=\"%s\"," +
                        "description=\"%s\"" +
                        " WHERE book_id=%d",
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getPrice(),
                book.getPublisher(),
                book.getDescription(),
                book.getBook_id()
        );
        this.databaseService.performDML(dml);
    }

    public void delete(int book_id){
        String dml=String.format("DELETE FROM book WHERE book_id=\"%d\"",book_id);
        this.databaseService.performDML(dml);
    }
}
