import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    private Level.Room currentRoom;
    private Player p;
    private HashMap<String, Level.Room> neighbors;

    protected Creature(Level.Room currentRoom){
        this.currentRoom = currentRoom;
        neighbors = currentRoom.getNeighbors();
    }

    protected abstract void act();

    protected abstract String getName();

    protected void trackPlayer(Player p ){
        this.p = p;
    }


    protected Level.Room getRandomAdjacentRoom(HashMap<String, Level.Room> map){
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room>(map.values());
        int randomRoomIndex = (int)(rooms.size()*Math.random());
        return rooms.get(randomRoomIndex);
    }

    protected boolean moveToRoom(Level.Room r){
        if (neighbors.containsValue(r))     return true;
        return false;
    }

    protected void moveRandom(){
        currentRoom = getRandomAdjacentRoom(neighbors);
    }

    protected Level.Room getCurrentRoom() {
        return currentRoom;
    }

    protected void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    protected Player getP() {
        return p;
    }

    protected void setP(Player p) {
        this.p = p;
    }


    protected boolean playerIsNearby() {
        if (playerOneStepAway() || playerTwoStepsAway()){
            return true;
        }
        return false;
    }

    protected boolean playerOneStepAway() {
        return getCurrentRoom().getNeighbors().containsValue(getP().getCurrentRoom());
    }

    protected boolean playerTwoStepsAway(){
        HashMap<String, Level.Room> allNeighbors = getCurrentRoom().getNeighbors();
        for (Level.Room r: allNeighbors.values()) {
            if (r.getNeighbors().containsValue(getP().getCurrentRoom())){
                return true;
            }
        }
        return false;
    }
}
