package pl.britenet.store.command;

import pl.britenet.store.Constants;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.service.*;

import java.util.Scanner;

public class UpdateCommand extends Command{
    public UpdateCommand(){
        super(Constants.COMMAND_NAME_UPDATE);
    }

    @Override
    public void execute(){

        Scanner scanner= new Scanner(System.in);
        DatabaseService databaseService=new DatabaseService();

        switch(scanner.next().toString()){
            case "archive":
                Archive archive=new Archive();
                ArchiveService archiveService=new ArchiveService(databaseService);
                System.out.println("\t-customer_id" +
                        "\n\t-book_id" +
                        "\n\t-number" +
                        "\n\t-price" +
                        "\n\t-commodity_id [WHERE]");
                archive.setCustomer_id(scanner.nextInt());
                archive.setBook_id(scanner.nextInt());
                archive.setNumber(scanner.nextInt());
                archive.setPrice(scanner.nextDouble());
                archive.setCommodity_id(scanner.nextInt());
                archiveService.update(archive);
                System.out.println("& Record altered");
                break;
            case "basket":
                Basket basket=new Basket();
                BasketService basketService=new BasketService(databaseService);
                System.out.println("\t-payment" +
                        "\n\t-basket_id [WHERE]");
                basket.setPayment(scanner.next());
                basket.setBasket_id(scanner.nextInt());
                basketService.update(basket);
                System.out.println("& Record altered");
                break;
            case "book":
                Book book=new Book();
                BookService bookService=new BookService(databaseService);
                System.out.println("\t-name" +
                        "\n\t-author" +
                        "\n\t-category" +
                        "\n\t-price" +
                        "\n\t-publisher" +
                        "\n\t-description" +
                        "\n\t-book_id [WHERE]");
                book.setName(scanner.next());
                book.setAuthor(scanner.next());
                book.setCategory(scanner.next());
                book.setPrice(scanner.nextDouble());
                book.setPublisher(scanner.next());
                book.setDescription(scanner.next());
                book.setBook_id(scanner.nextInt());
                bookService.update(book);
                System.out.println("& Record altered");
                break;
            case "commodity":
                Commodity commodity=new Commodity();
                CommodityService commodityService=new CommodityService(databaseService);
                System.out.println("\t-basket_id" +
                        "\n\t-book_id" +
                        "\n\t-number" +
                        "\n\t-commodity_id [WHERE]");
                commodity.setBasket_id(scanner.nextInt());
                commodity.setBook_id(scanner.nextInt());
                commodity.setNumber(scanner.nextInt());
                commodity.setCommodity_id(scanner.nextInt());
                commodityService.update(commodity);
                System.out.println("& Record altered");
                break;
            case "customer":
                Customer customer=new Customer();
                CustomerService customerService=new CustomerService(databaseService);
                System.out.println("\t-name" +
                        "\n\t-surname" +
                        "\n\t-adress" +
                        "\n\t-contact" +
                        "\n\t-username" +
                        "\n\t-password" +
                        "\n\t-customer_id [WHERE]");
                customer.setName(scanner.next());
                customer.setSurname(scanner.next());
                customer.setAdress(scanner.next());
                customer.setContact(scanner.next());
                customer.setUsername(scanner.next());
                customer.setPassword(scanner.next());
                customer.setCustomer_id(scanner.nextInt());
                customerService.update(customer);
                System.out.println("& Record altered");
                break;
        }
    }
}
