import java.util.ArrayList;

public class Wumpus extends Creature {

    public Wumpus(Level.Room currentRoom, Player p) {
        super(currentRoom);
        trackPlayer(p);
    }

    public void act(){
        ArrayList<Level.Room> availableRooms = new ArrayList<>();


        if (!getCurrentRoom().getNeighbors().isEmpty()) {
            if (playerIsNextDoor()){         //if wumpus is next door to player
                availableRooms = findAvailableRooms();
            }
        }
        if (!availableRooms.isEmpty()){
            setCurrentRoom(this.getRandomAdjacentRoom());
        }
    }

    private ArrayList<Level.Room> findAvailableRooms() {
        ArrayList<Level.Room> roomsToRunIn = new ArrayList<>();

        for (Level.Room r : getCurrentRoom().getNeighbors().values()) {                 // create a list of room to run in, not including player's room

            if (!r.equals(getP().getCurrentRoom())) {
                roomsToRunIn.add(r);
            }
        }
        return roomsToRunIn;
    }

    private boolean playerIsNextDoor() {
       return getP().getCurrentRoom().getNeighbors().containsValue(getCurrentRoom());
    }
}
