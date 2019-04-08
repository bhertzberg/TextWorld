import java.util.ArrayList;

public class Wumpus extends Creature {

    public Wumpus(Level.Room currentRoom, Player p) {
        super(currentRoom);
        trackPlayer(p);
    }

    public void runFromPlayer(){
        ArrayList<Level.Room> roomsToRunIn = new ArrayList<>();

        if (!getCurrentRoom().getNeighbors().isEmpty()) {
            if (getP().getCurrentNode().getNeighbors().containsValue(getCurrentRoom())){         //if wumpus is next door to player
                for (Level.Room r : getCurrentRoom().getNeighbors().values()) {                 // create a list of room to run in, not including player's room


                    if (!r.equals(getP().getCurrentNode())) {
                        roomsToRunIn.add(r);
                    }
                }
            }
        }
        if (!roomsToRunIn.isEmpty()){
            setCurrentRoom(roomsToRunIn.get((int)(Math.random()*roomsToRunIn.size())));
        }
    }
}
