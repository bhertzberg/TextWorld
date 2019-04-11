public class TakeCommand implements Command{
    Level level;
    String itemName;

    public TakeCommand(Level level) {
        this.level = level;
    }

    @Override
    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private String getLastWordIn(String userString) {
        String[] words = userString.split(" ");
        return words[words.length-1];
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
        boolean success = p.containsItem( itemName);
        p.removeItem(itemName);
        return success;
    }
}
