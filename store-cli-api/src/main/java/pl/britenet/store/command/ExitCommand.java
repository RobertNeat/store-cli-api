package pl.britenet.store.command;
import pl.britenet.store.Constants;

public class ExitCommand extends Command {
    public ExitCommand () {
        super(Constants.COMMAND_NAME_EXIT);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}