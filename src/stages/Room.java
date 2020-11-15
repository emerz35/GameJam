package stages;

import gameobjects.Collision;
import gameobjects.GameObject;
import gameobjects.Mobile;
import gameobjects.Player;
import gameobjects.factories.PickUpFactory;
import java.awt.Graphics2D;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import main.Main;
import static stages.Tile.TILE_HEIGHT;
import static stages.Tile.TILE_WIDTH;

/**
 *
 * @author Charlie
 */
public class Room {
    public final RoomBackground background;
    public final Player player;
    private List<GameObject> objects = new LinkedList();
    private final PickUpFactory pickUpFactory;
    private boolean completed = false;
    
    public Room(Player p, RoomBackground bg){
        player = p;
        background = bg;
        pickUpFactory = new PickUpFactory();
    }
    
    public List<GameObject> getObjects(){
        return objects;
    }
    
    public void render(Graphics2D g){
        background.render(g);
        objects.stream().filter(x->isVisible(x))
                .forEach(x->x.render(g));
    }
    public void tick(){
        objects.forEach(x -> x.tick());
        
        objects.forEach(x -> collision(x));
        
        objects.forEach(o->{
            for(int y = o.getY()-TILE_HEIGHT; y<o.getY()+o.getHeight()+TILE_HEIGHT;y+=TILE_HEIGHT){
                for(int x = o.getX()-TILE_WIDTH; x<o.getX()+o.getWidth()+TILE_WIDTH;x+=TILE_WIDTH){
                    int relX = background.coordToRelX(x), relY = background.coordToRelY(y);
                    if(relY>=0&&relY<background.getTilesHeight()&&relX>=0&&relX<background.getTilesWidth()){
                        Collision c = background.tileToCollision(relX, relY);
                        if(c.isColliding(o)){
                            c.onCollision(o);
                        }
                    }
                }
            }
        });
        
        if(!completed && !objects.stream().anyMatch(x-> {
            if(x instanceof Mobile&&!(x instanceof Player)){
                return ((Mobile)x).isAlive();
            }
            return false;
        })) {
            addObject(pickUpFactory.createKeyPickUp(player, this));
            completed = true;
            player.addMessage("Room Complete!", 300);
        }
        
        objects = objects.stream()
                .filter(x -> x.doesExist())
                .collect(Collectors.toList());
        
        changeY(-player.vely-player.kvely);
        changeX(-player.velx-player.kvelx);
    }
    private void collision(GameObject o){
        objects.stream().filter(x -> x.isColliding(o)&&!x.equals(o)).forEach(x -> {
            o.onCollision(x);
            if(!o.canWalk &&x instanceof Mobile)if(((Mobile)x).isAlive())((Mobile)x).zeroVelocityWith(o);
            //System.out.println(o + " " + o.getX() + ", " + (o.getX() + o.getWidth()) + "; " + o.getY() + ", " + (o.getY() + o.getHeight()));
            });
    }
    
    public void addObject(GameObject o){
        objects.add(o);
    }
    /**
     * Removes any GameObject from the stage 
     * @param o the object to remove
     */
    public void removeObject(GameObject o){
        o.remove();
    }
    public void changeY(int dy){
        if(player.getY()-dy<Main.FHEIGHT/4||player.getY()+player.getHeight()-dy>3*Main.FHEIGHT/4) {
            background.y+=dy;
            objects.stream().filter(x -> !(x instanceof Player)).forEach(x -> x.setY(x.getY() + dy));
        }
        else player.setY(player.getY()-dy);
    }
    public void changeX(int dx){
        if(player.getX()-dx<Main.FWIDTH/4||player.getX()+player.getWidth()-dx>3*Main.FWIDTH/4) {
            background.x += dx;
            objects.stream().filter(x -> !(x instanceof Player)).forEach(x -> x.setX(x.getX() + dx));
        }
        else player.setX(player.getX()-dx);
    }
    public RoomBackground getBackground(){
        return background;
    }
    public boolean isVisible(Collision x){
        return x.getX()<Main.FWIDTH&&x.getX()+x.getWidth()>0&&x.getY()<Main.FHEIGHT &&x.getY()+x.getHeight()>0;
    }
}

