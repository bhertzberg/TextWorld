import java.util.HashMap;

public class Popstar extends Creature {

    public Popstar(Level.Room currentRoom, Player p) {
        super(currentRoom);
        trackPlayer(p);
    }

    @Override
    protected void act() {
        Level.Room nextRoom = null;


        if (!getCurrentRoom().getNeighbors().isEmpty() && !getCurrentRoom().equals(getP().getCurrentRoom())) {
            if (playerIsNearby()) {         //if popstar is next door or 2 doors away from player
                nextRoom = chasePlayer();
            }
        }
        if (nextRoom != null) {
            setCurrentRoom(nextRoom);
        }
    }

    private Level.Room chasePlayer() {
        Level.Room nextRoom = null;

        for (Level.Room r : getCurrentRoom().getNeighbors().values()) {                 // create a list of room to run in, not including player's room

            if (r.equals(getP().getCurrentRoom())) {
                return r;
            } else {
                for (Level.Room r2 : r.getNeighbors().values()) {
                    if (r2.equals(getP().getCurrentRoom())) {
                        return r;
                    }
                }
            }
        }
        return null;
    }

    public String getName(){
        return "popstar";
    }
}

