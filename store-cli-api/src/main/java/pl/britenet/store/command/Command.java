package pl.britenet.store.command;

public abstract class Command {
    private final String name;

    protected Command(String name) {
        this.name = name;
    }

    public abstract void execute();

    public String getName(){
        return name;
    }
}
