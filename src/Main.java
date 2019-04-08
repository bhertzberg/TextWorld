import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Level g = new Level();
        g.addRoom("hall", "hall of doub");
        g.addRoom("closet", "closet of doob");
        g.addRoom("dungeon", "doubie dungeon");
        g.addRoom("ladder", "rickety laddoubie");

        g.addDirectedEdge("hall", "dungeon");
        g.addDirectedEdge("dungeon", "ladder");
        g.addDirectedEdge("ladder", "hall");
        g.addUndirectedEdge("hall", "closet");

        Player p = new Player("Ben", "1 unit of doub");
        p.setCurrentNode(g.getRoom("hall"));
        Level.Room current = p.getCurrentNode();

        Item ball = new Item("ball", "doubie's ball");
        g.getRoom("closet").addItem(ball);

        Chicken c = new Chicken(g.getRoom("dungeon"));
        Wumpus w = new Wumpus(g.getRoom("ladder"), p);




        String response = "";
        Scanner s = new Scanner(System.in);

        do{
            System.out.println("You are currently in the " + current.getName());
            System.out.println("What do you want to do?");
            response = s.nextLine();

            if (response.contains("go")){
                String room = response.substring(3);
                if (current.getNeighbor(room) != null){
                    current = current.getNeighbor(room);
                    p.setCurrentNode(current);
                } else {
                    System.out.println("You can't go to " + response + ". Try typing 'look' to see where you can go.");
                }
            } else if (response.equals("look")) {
                System.out.println("You are in the " + current.getDescription());
                System.out.println("This room contains: ");
                current.displayItems();
                System.out.println("You can go to the " + current.getNeighborNames());
            } else if (response.contains("add room")){
                String newRoom = response.substring(9);
                g.addRoom(newRoom, "new room");
                g.addUndirectedEdge(current.getName(), newRoom);
            } else if (response.contains("take")) {
                String itemName = response.substring(5);
                if (current.containsItem(itemName)) {
                    p.addItem(current.removeItem(itemName));
                    System.out.println("You have taken the " + itemName + " from the " + current.getName());
                }
            } else if (response.contains("drop")) {
                String itemName = response.substring(5);
                if (p.containsItem(itemName)) {
                    current.addItem(p.removeItem(itemName));
                    System.out.println("You have dropped the " + itemName + " in the " + current.getName());
                }
            } else if (response.equals("quit")){
                System.out.println("You have left the game");
            } else {
                System.out.println("You can type 'go <room name>', 'look', 'add room <room name>', 'take <item name>', 'drop <item name>', or \"quit\"");

            }
            System.out.println("The chicken is in the " + c.getCurrentRoom().getName());
            if (!c.getCurrentRoom().getNeighbors().isEmpty()) {
                c.randomMove();
            }
            w.runFromPlayer();
            System.out.println("The wumpus is in the " + w.getCurrentRoom().getName());



        }while (!response.equals("quit"));
    }
}
