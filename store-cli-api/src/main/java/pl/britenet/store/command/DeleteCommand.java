package pl.britenet.store.command;

import pl.britenet.store.Constants;
import pl.britenet.store.database.DatabaseService;
import pl.britenet.store.model.*;
import pl.britenet.store.service.*;

import java.util.Scanner;

public class DeleteCommand extends Command{
    public DeleteCommand(){
        super(Constants.COMMAND_NAME_DELETE);
    }

    @Override
    public void execute(){
        Scanner scanner=new Scanner(System.in);
        DatabaseService databaseService= new DatabaseService();

        switch(scanner.next().toString()){
            case "archive":
                ArchiveService archiveService=new ArchiveService(databaseService);
                System.out.println("\t-commodity_id");
                archiveService.delete(scanner.nextInt());
                System.out.println("- Record deleted");
                break;
            case "basket":
                BasketService basketService=new BasketService(databaseService);
                System.out.println("\t-basket_id");
                basketService.delete(scanner.nextInt());
                System.out.println("- Record deleted");
                break;
            case "book":
                BookService bookService=new BookService(databaseService);
                System.out.println("\t-book_id");
                bookService.delete(scanner.nextInt());
                System.out.println("- Record deleted");
                break;
            case "commodity":
                CommodityService commodityService=new CommodityService(databaseService);
                System.out.println("\t-commodity_id");
                commodityService.delete(scanner.nextInt());
                System.out.println("- Record deleted");
                break;
            case "customer":
                CustomerService customerService=new CustomerService(databaseService);
                System.out.println("\t-customer_id");
                customerService.delete(scanner.nextInt());
                System.out.println("- Record deleted");
                break;
        }
    }
}
