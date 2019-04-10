import java.util.ArrayList;
import java.util.HashMap;

public class Chicken extends Creature {

    public Chicken(Level.Room currentRoom) {
        super(currentRoom);
    }


    public void act() {

        Level.Room newRoom = getCurrentRoom().getRandomNeigbor();
        if (newRoom != null) {
            setCurrentRoom(newRoom);
        }
    }
}
