import java.util.ArrayList;

public class Player {
    String name, description;
    ArrayList<Item> items;
    Level.Room currentRoom;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item removeItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)){
                return items.remove(i);
            }
        }
        return null;

    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public Level.Room getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom){
        currentRoom = newRoom;
    }

    public boolean containsItem(String itemName){
        for (Item i: items) {
            if (i.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }

}

