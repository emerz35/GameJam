package gameobjects.factories;

import animations.Animation;
import gameobjects.PickUp;
import gameobjects.Player;
import static gameobjects.factories.PlayerFactory.BLANK_PLAYER_IMAGE;
import items.Item;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Main;
import main.Utils;
import stages.Room;

/**
 *
 * @author Charlie
 */
public class PickUpFactory {
    
    public static final int KEY_WIDTH = 32;
    public static final int KEY_HEIGHT = 32;
    
    
    public PickUp createKeyPickUp(Player p, Room room){
        int tx = room.background.coordToRelX(p.getX())+Utils.getUtils().getRandInt(-1, 2), ty = room.background.coordToRelY(p.getY())+Utils.getUtils().getRandInt(-1, 2);
        if(tx<0||ty<0||tx>=room.background.getTilesWidth()||ty>=room.background.getTilesHeight()||!room.background.getTile(tx,ty).canWalk){
            tx = room.background.coordToRelX(p.getX())+Utils.getUtils().getRandInt(-1, 2);
            ty = room.background.coordToRelY(p.getY())+Utils.getUtils().getRandInt(-1, 2);
        }
        
        Image image1 = BLANK_PLAYER_IMAGE, image2 = BLANK_PLAYER_IMAGE;
        int randKey = Utils.getUtils().getRandInt(1, 5);
        try{
            image1 = ImageIO.read(Main.class.getResource("/images/key"+randKey+".png"));
            image2 = ImageIO.read(Main.class.getResource("/images/lkey"+randKey+".png"));
        }catch(IOException ex){
            ex.printStackTrace(System.out);
        }
        Item key = new Item("Key", image1);
        
        return new PickUp(room.background.tileToAbsX(tx),room.background.tileToAbsX(ty),KEY_WIDTH,KEY_HEIGHT,key,
                new Animation(new Image[]{image1,image2},new int[]{30,30},true));
    }
}
