import java.util.ArrayList;
import java.util.HashMap;

public class Chicken extends Creature {

    public Chicken(Level.Room currentRoom) {
        super(currentRoom);
    }


    public void randomMove(){
        Level.Room newRoom = getCurrentRoom().getRandomNeigbor();
        moveToRoom(newRoom);
    }
}
