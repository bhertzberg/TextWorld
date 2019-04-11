public class TakeCommand implements Command{
    Level level;
    String itemName;

    public TakeCommand(Level level) {
        this.level = level;
    }

    @Override
    public void init(String userString) {

    }

    @Override
    public boolean execute() {
        return false;
    }
}
