package pl.britenet.store.command;

import pl.britenet.store.Constants;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.service.*;

import java.util.Scanner;

public class InsertCommand extends Command{
    public InsertCommand(){
        super(Constants.COMMAND_NAME_INSERT);
    }

    @Override
    public void execute(){

        Scanner scanner=new Scanner(System.in);
        DatabaseService databaseService=new DatabaseService();

        switch(scanner.next().toString()){
            case "archive":
                Archive archive=new Archive();
                ArchiveService archiveService=new ArchiveService(databaseService);
                System.out.println("\t-customer_id" +
                        "\n\t-book_id" +
                        "\n\t-number" +
                        "\n\t-price");
                archive.setCustomer_id(scanner.nextInt());
                archive.setBook_id(scanner.nextInt());
                archive.setNumber(scanner.nextInt());
                archive.setPrice(scanner.nextDouble());
                archiveService.insert(archive);
                System.out.println("+ Record created");
                break;
            case "basket":
                Basket basket=new Basket();
                BasketService basketService=new BasketService(databaseService);
                System.out.println("\t-payment");
                basket.setPayment(scanner.next());
                basketService.insert(basket);
                System.out.println("+ Record created");
                break;
            case "book":
                Book book=new Book();
                BookService bookService=new BookService(databaseService);
                System.out.println("\t-name" +
                        "\n\t-author" +
                        "\n\t-category" +
                        "\n\t-price" +
                        "\n\t-publisher" +
                        "\n\t-description");
                book.setName(scanner.next());
                book.setAuthor(scanner.next());
                book.setCategory(scanner.next());
                book.setPrice(scanner.nextDouble());
                book.setPublisher(scanner.next());
                book.setDescription(scanner.next());
                bookService.insert(book);
                System.out.println("+ Record created");
                break;
            case "commodity":
                Commodity commodity=new Commodity();
                CommodityService commodityService=new CommodityService(databaseService);
                System.out.println("\t-basket_id" +
                        "\n\t-book_id" +
                        "\n\t-number");
                commodity.setBasket_id(scanner.nextInt());
                commodity.setBook_id(scanner.nextInt());
                commodity.setNumber(scanner.nextInt());
                commodityService.insert(commodity);
                System.out.println("+ Record created");
                break;
            case "customer":
                Customer customer=new Customer();
                CustomerService customerService=new CustomerService(databaseService);
                System.out.println("\t-name" +
                        "\n\t-surname" +
                        "\n\t-adress" +
                        "\n\t-contact" +
                        "\n\t-username" +
                        "\n\t-password");
                customer.setName(scanner.next());
                customer.setSurname(scanner.next());
                customer.setAdress(scanner.next());
                customer.setContact(scanner.next());
                customer.setUsername(scanner.next());
                customer.setPassword(scanner.next());
                customerService.insert(customer);
                System.out.println("+ Record created");
                break;
        }
    }
}
