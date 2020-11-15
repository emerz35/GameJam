package stages;

import gameobjects.Mobile;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.Main;

/**
 *
 * @author Charlie
 */
public class Tile implements Serializable{
    public static transient final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    public static transient final Tile FLOOR_TILE;
    public static transient final Tile LAVA_TILE;
    public static transient final Tile WALL_TILE;
    public static transient final Tile SAFE_TILE;
    public static transient final Tile EMPTY_TILE;
    
    static{
        BufferedImage floorImage = new BufferedImage(TILE_WIDTH,TILE_HEIGHT,BufferedImage.TYPE_INT_ARGB), 
                lavaImage = new BufferedImage(TILE_WIDTH,TILE_HEIGHT,BufferedImage.TYPE_INT_ARGB), 
                wallImage = new BufferedImage(TILE_WIDTH,TILE_HEIGHT,BufferedImage.TYPE_INT_ARGB),
                emptyImage = new BufferedImage(TILE_WIDTH,TILE_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        
        try {
            floorImage = ImageIO.read(Main.class.getResource("/images/floor_tile.png"));
            lavaImage = ImageIO.read(Main.class.getResource("/images/lava_tile.png"));
            wallImage = ImageIO.read(Main.class.getResource("/images/wall_tile.png"));
            emptyImage = ImageIO.read(Main.class.getResource("/images/empty_tile.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        FLOOR_TILE = new Tile((floorImage),true,true,0);
        SAFE_TILE = new Tile(floorImage,true,false,0);
        LAVA_TILE = new Tile((lavaImage),true,false,1);
        WALL_TILE = new Tile((wallImage),false,false,0);
        EMPTY_TILE = new Tile(emptyImage,false,false,0);
    }
    
    private final BufferedImage image;
    public boolean canWalk, enemyCanSpawn;
    private final int dpf;
    
    public Tile(BufferedImage i, boolean canWalk,boolean canSpawn, int damage){
        image = i;
        this.canWalk = canWalk;
        enemyCanSpawn = canSpawn;
        this.dpf = damage;
    }
    
    public void render(Graphics2D g, int x, int y){
        g.drawImage(image, x, y, null);
    }
    
    public void steppedOn(Mobile o){
        o.changeHealth(dpf);
    }
}
