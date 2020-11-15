package stages;

import gameobjects.Player;
import java.awt.Graphics2D;

/**
 *
 * @author Charlie
 */
public class RoomHandler {
    public Room room;
    public Player player;
    
    public RoomHandler(Room r, Player p){
        room = r;
        player = p;
        room.addObject(p);
        p.roomHandler = this;
    }
    
    public void setRoom(Room r){
        this.room = r;
        room.addObject(player);
    }
    
    public void tick(){
        room.tick();
    }
    
    public void render(Graphics2D g){
        room.render(g);
    }
}
