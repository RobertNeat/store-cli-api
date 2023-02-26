package pl.britenet.store;


import pl.britenet.store.command.*;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        boolean isRunning=true;
        CommandService commandService= new CommandService();

        //cli commands
        commandService.registerCommnad(new ExitCommand());
        commandService.registerCommnad(new HelpCommand());

        commandService.registerCommnad(new InsertCommand());
        commandService.registerCommnad(new UpdateCommand());
        commandService.registerCommnad(new DeleteCommand());

        commandService.registerCommnad(new SelectCommand());
        commandService.registerCommnad(new SelectAllCommand());


        Scanner scanner= new Scanner(System.in);
        while(isRunning){
            String commandName = scanner.nextLine();
            Optional<Command> command = commandService.getCommand(commandName);
            if(command.isPresent()){
                command.get().execute();
            }
            else{
                System.out.println("Unknown Command");
            }
        }
    }
}
