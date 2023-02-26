package pl.britenet.store.command;
import pl.britenet.store.Constants;

import java.lang.reflect.Field;

public class HelpCommand extends Command{

    public HelpCommand() {
        super(Constants.COMMAND_NAME_HELP);
    }

    @Override
    public void execute(){

        Field[] fields = Constants.class.getFields();

        int a=0;
        for(Field field:fields){
            String commandNameField=fields[a].getName().toLowerCase();
            String segments[]=commandNameField.split("_");
            System.out.println("- "+segments[segments.length-1]);
            a++;
        }
    }
}
