package pl.britenet.store.command;

import pl.britenet.store.Constants;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SelectAllCommand extends Command{
    public SelectAllCommand(){
        super(Constants.COMMAND_NAME_SELECTALL);
    }

    @Override
    public void execute(){
        Scanner scanner=new Scanner(System.in);
        DatabaseService databaseService=new DatabaseService();

        switch(scanner.next().toString()){
            case "archive":
                ArchiveService archiveService=new ArchiveService(databaseService);
                List<Archive> archiveResult=archiveService.getAllArchive();
                System.out.println("commodity_id\tcustomer_id\tbook_id\tnumber\tprice\tcreated");
                for(Archive archive:archiveResult){
                    System.out.println(archive.getCommodity_id() + "\t" +
                            archive.getCustomer_id() + "\t" +
                            archive.getBook_id() + "\t" +
                            archive.getNumber() + "\t" +
                            archive.getPrice() + "\t" +
                            archive.getCreated());
                }
                break;
            case "basket":
                BasketService basketService=new BasketService(databaseService);
                List<Basket> basketResult=basketService.getAllBasket();
                System.out.println("basket_id\tpayment\tcreated");
                for(Basket basket: basketResult){
                    System.out.println(basket.getBasket_id()+"\t"+
                            basket.getPayment()+"\t"+
                            basket.getCreated());
                }
                break;
            case "book":
                BookService bookService=new BookService(databaseService);
                List<Book> bookResult=bookService.getAllBook();
                System.out.println("book_id\tname\tauthor\tcategory\tprice\tpublisher\treleased\tdescription");
                for(Book book:bookResult){
                    System.out.println(book.getBook_id()+"\t"+
                            book.getName()+"\t"+
                            book.getAuthor()+"\t"+
                            book.getCategory()+"\t"+
                            book.getPrice()+"\t"+
                            book.getPublisher()+"\t"+
                            book.getReleased()+"\t"+
                            book.getDescription());
                }
                break;
            case "commodity":
                CommodityService commodityService=new CommodityService(databaseService);
                List<Commodity> commodityResult=commodityService.getAllCommodity();
                System.out.println("commodity_id\tbasket_id\tbook_id\tnumber");
                for(Commodity commodity:commodityResult){
                    System.out.println(commodity.getCommodity_id()+"\t"+
                            commodity.getBasket_id()+"\t"+
                            commodity.getBook_id()+"\t"+
                            commodity.getNumber());
                }
                break;
            case "customer":
                CustomerService customerService=new CustomerService(databaseService);
                List<Customer> customerResult=customerService.getAllCustomer();
                System.out.println("customer_id\tname\tsurname\tadress\tcontact\tusername\tpassword");
                for(Customer customer:customerResult){
                    System.out.println(customer.getCustomer_id()+"\t"+
                            customer.getName()+"\t"+
                            customer.getSurname()+"\t"+
                            customer.getAdress()+"\t"+
                            customer.getContact()+"\t"+
                            customer.getUsername()+"\t"+
                            customer.getPassword());
                }
                break;
        }
    }
}
