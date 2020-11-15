package stages.generators;

import gameobjects.Player;
import stages.SavedRoom;

/**
 *
 * @author Charlie
 */
public interface RoomGenerator {
    
    public SavedRoom createRoom(Player p, int level);
}
