package pl.britenet.store.command;

import pl.britenet.store.Constants;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.service.*;

import java.util.Optional;
import java.util.Scanner;

public class SelectCommand extends Command{
    public SelectCommand(){
        super(Constants.COMMAND_NAME_SELECT);
    }

    @Override
    public void execute(){
        Scanner scanner=new Scanner(System.in);
        DatabaseService databaseService=new DatabaseService();

        switch(scanner.next().toString()){
            case "archive":
                ArchiveService archiveService=new ArchiveService(databaseService);
                Optional<Archive> archive=archiveService.getArchive(scanner.nextInt());
                if(archive.isPresent()){
                    System.out.println("commodity_id\tcustomer_id\tbook_id\tnumber\tprice\tcreated");
                    System.out.println(archive.get().getCommodity_id()+"\t" +
                            archive.get().getCustomer_id()+"\t"+
                            archive.get().getBook_id()+"\t"+
                            archive.get().getNumber()+"\t"+
                            archive.get().getPrice()+"\t"+
                            archive.get().getCreated());
                }
                else{
                    System.out.println("Position do not exists");
                }
                break;
            case "basket":
                BasketService basketService=new BasketService(databaseService);
                Optional<Basket> basket=basketService.getBasket(scanner.nextInt());
                if(basket.isPresent()){
                    System.out.println("basket_id\tpayment\tcreated");
                    System.out.println(basket.get().getBasket_id()+"\t"+
                            basket.get().getPayment()+"\t"+
                            basket.get().getCreated());
                }
                else{
                    System.out.println("Position do not exists");
                }
                break;
            case "book":
                BookService bookService=new BookService(databaseService);
                Optional<Book> book=bookService.getBook(scanner.nextInt());
                if(book.isPresent()){
                    System.out.println("book_id\tname\tauthor\tcategory\tprice\tpublisher\treleased\tdescription");
                    System.out.println(book.get().getBook_id()+"\t"+
                            book.get().getName()+"\t"+
                            book.get().getAuthor()+"\t"+
                            book.get().getCategory()+"\t"+
                            book.get().getPrice()+"\t"+
                            book.get().getPublisher()+"\t"+
                            book.get().getReleased()+"\t"+
                            book.get().getDescription());
                }
                else{
                    System.out.println("Position do not exists");
                }
                break;
            case "commodity":
                CommodityService commodityService=new CommodityService(databaseService);
                Optional<Commodity> commodity=commodityService.getCommodity(scanner.nextInt());
                if(commodity.isPresent()){
                    System.out.println("commodity_id\tbasket_id\tbook_id\tnumber");
                    System.out.println(commodity.get().getCommodity_id()+"\t"+
                            commodity.get().getBasket_id()+"\t"+
                            commodity.get().getBook_id()+"\t"+
                            commodity.get().getNumber());
                }
                else{
                    System.out.println("Position do not exists");
                }
                break;
            case "customer":
                CustomerService customerService=new CustomerService(databaseService);
                Optional<Customer> customer=customerService.getCustomer(scanner.nextInt());
                if(customer.isPresent()){
                    System.out.println("customer_id\tname\tsurname\tadress\tcontact\tusername\tpassword");
                    System.out.println(customer.get().getCustomer_id()+"\t"+
                            customer.get().getName()+"\t"+
                            customer.get().getSurname()+"\t"+
                            customer.get().getAdress()+"\t"+
                            customer.get().getContact()+"\t"+
                            customer.get().getUsername()+"\t"+
                            customer.get().getPassword());
                }
                else{
                    System.out.println("Position do not exists");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scanner.next().toString());
        }
    }
}
