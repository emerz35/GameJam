package stages.generators;

import gameobjects.Player;
import gameobjects.factories.EnemyFactory;
import java.util.Random;
import main.Main;
import main.Utils;
import stages.Room;
import stages.RoomBackground;
import stages.SavedRoom;
import stages.Tile;
import static stages.Tile.FLOOR_TILE;
import static stages.Tile.TILE_HEIGHT;
import static stages.Tile.TILE_WIDTH;
import static stages.Tile.WALL_TILE;

/**
 *
 * @author Charlie
 */
public class BasicRoomGenerator implements RoomGenerator{
    
    
    
    private Random r = new Random();
    
    @Override
    public SavedRoom createRoom(Player p, int level) {
        Utils u = Utils.getUtils();
        int width = u.getRandInt(Main.FWIDTH, 2*Main.FWIDTH)/TILE_WIDTH;
        //System.out.println(width);
        int height = u.getRandInt(Main.FWIDTH, 2*Main.FWIDTH)/TILE_HEIGHT;
        Tile tiles[][] = new Tile[height][width];
        
        
        
        for(int y = 0;y < height;y++){
            for(int x = 0; x < width; x++){
                if(y==0||y==height-1||x == 0||x==width-1) tiles[y][x] = WALL_TILE;
                else tiles[y][x] = FLOOR_TILE;
            }
        }
        RoomBackground rbg = new RoomBackground(tiles);
        
        Room room = new Room(p,rbg);
        
        EnemyFactory ef = new EnemyFactory();
        
        for(int i  = 0; i<u.getRandInt(2*level, 2*level+4);i++){
            room.addObject(ef.createStoneEnemy(level, room));
        }
        SavedRoom ret = new SavedRoom("room"+ level,room);
        ret.level = level;
        ret.save();
        return ret;
    }
    
}
