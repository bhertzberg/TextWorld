import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature {

    public Wumpus(Level.Room currentRoom, Player p) {
        super(currentRoom);
        trackPlayer(p);
    }

    public void act(){
        HashMap<String, Level.Room> availableRooms = new HashMap<>();


        if (!getCurrentRoom().getNeighbors().isEmpty()) {
            if (playerIsNearby()){         //if wumpus is next door or 2 doors away from player
                availableRooms = findAvailableRooms();
            }
        }
        if (!availableRooms.isEmpty()){
            setCurrentRoom(this.getRandomAdjacentRoom(availableRooms));
        }
    }

    @Override
    public String getName() {
        return "wumpus";
    }

    private HashMap<String, Level.Room> findAvailableRooms() {
        HashMap<String, Level.Room> roomsToRunIn = new HashMap<>();

        for (Level.Room r : getCurrentRoom().getNeighbors().values()) {

            if (!r.equals(getP().getCurrentRoom()) && !r.getNeighbors().containsValue(getP().getCurrentRoom())) {           // create a list of room to run in, not including player's room or rooms next door to player
                roomsToRunIn.put(r.getName(), r);
            }
        }
        return roomsToRunIn;
    }

    private boolean playerIsNearby() {
        if (playerOneStepAway() || playerTwoStepsAway()){
            return true;
        }
        return false;
    }

    private boolean playerOneStepAway() {
      return getCurrentRoom().getNeighbors().containsValue(getP().getCurrentRoom());
    }

    private boolean playerTwoStepsAway(){
        HashMap<String, Level.Room> allNeighbors = getCurrentRoom().getNeighbors();
        for (Level.Room r: allNeighbors.values()) {
            if (r.getNeighbors().containsValue(getP().getCurrentRoom())){
                return true;
            }
        }
        return false;
    }
}
