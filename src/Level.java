import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> rooms;
    private Player p;

    public Level() {
        rooms = new HashMap<String, Room>();
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        rooms.put(name, n);
    }

    public void addPlayer(Player p){
        this.p = p;
    }

    public Player getPlayer(){
        return this.p;
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getRoom(String name){
        if (rooms.get(name).getName().equals(name)){
            return rooms.get(name);
        }

        return null;
    }


    public class Room {

        private String name;
        private HashMap<String, Room> neighbors;
        private String description;
        private ArrayList<Item> items;

        private Room(String name, String description) {
            neighbors = new HashMap<String, Room>();
            items = new ArrayList<>();
            this.name = name;
            this.description = description;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            for (Item i:items) {
                if (i.getName().equals(name)){
                    Item removed = i;
                    items.remove(i);
                    return removed;
                }
            }
            return null;
        }


        public void displayItems(){
            if (!items.isEmpty()) {
                for (Item i : items) {
                    System.out.println(i.getName() + " ");
                }
            } else {
                System.out.println("no items");
            }
        }

        public boolean containsItem(String itemName){
            for (Item i: items) {
                if (i.getName().equals(itemName)){
                    return true;
                }
            }
            return false;
        }

        private void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);

        }

        public String getNeighborNames() {
            String allNeighbors = "";
            for (String neighborName: neighbors.keySet()) {
                allNeighbors += neighborName + " ";
            }
            return allNeighbors;
        }

        public Room getNeighbor(String name) {
            if (neighbors.containsKey(name)){
                return neighbors.get(name);
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Room getRandomNeigbor() {
            ArrayList<Level.Room> n = new ArrayList<>();
            for (Level.Room r: neighbors.values()) {
                n.add(r);
            }

            if (!n.isEmpty()) {
                return n.get((int) (Math.random() * n.size()));
            }

            return null;
        }
    }
}
