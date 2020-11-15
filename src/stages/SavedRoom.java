package stages;

import animations.Animation;
import gameobjects.Door;
import gameobjects.GameObject;
import gameobjects.Player;
import static gameobjects.factories.PlayerFactory.BLANK_PLAYER_IMAGE;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.imageio.ImageIO;
import main.Main;
import main.Utils;
import stages.generators.BasicRoomGenerator;
import stages.generators.RoomGenerator;

/**
 *
 * @author Charlie
 */
public class SavedRoom implements Serializable{
    public static final int DOOR_WIDTH = 32,DOOR_HEIGHT = 32;
    private final List<GameObject> startObjects;
    private String name;
    private RoomBackground background;
    private transient RoomGenerator generator = new BasicRoomGenerator();
    public int level;
    private String nextRoom;
    
    public SavedRoom(String name, Room room){
        startObjects = room.getObjects();
        background = room.getBackground();
    }
    
    public Room buildRoom(Player p){
        Utils u = Utils.getUtils();
        Room ret = new Room(p,background);
        startObjects.forEach(x->ret.addObject(x));
        int choice1 = u.getRandInt(0, 2);
        
        Image doorImage = BLANK_PLAYER_IMAGE;
        try{
            doorImage = ImageIO.read(Main.class.getResource("/images/door.png"));
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
        SavedRoom next;
        if(nextRoom == null){
            next = generator.createRoom(p, level+1);
            nextRoom = next.name;
        }
        else next = getSavedRoom(nextRoom);
        Door door = new Door(background.tileToAbsX(u.getRandInt(0,background.getTilesWidth())*choice1),
                background.tileToAbsY(u.getRandInt(0,background.getTilesHeight())*(1-choice1)),DOOR_WIDTH,DOOR_HEIGHT,
                next,new Animation(new Image[]{doorImage}, new int[]{1},false));
        ret.addObject(door);
        return ret;
    }
    
    public void save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
            new File(System.getProperty("user.home")+"/noturningback/rooms/"+name+".room")))){
                File newFile = new File(System.getProperty("user.home")+"/game/rooms");
                newFile.createNewFile();
                oos.writeObject(this);
                
        }   catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public static SavedRoom getSavedRoom(String name){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File(System.getProperty("user.home")+"/noturningback/rooms/"+name+".room")))){
                
                    return (SavedRoom) ois.readObject();
                
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
}
