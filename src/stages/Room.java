package stages;

import gameobjects.GameObject;
import gameobjects.Player;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 *
 * @author Charlie
 */
public class Room {
    private Image background;
    private List<GameObject> objects = new LinkedList();
    
    public List<GameObject> getObjects(){
        return objects;
    }
    
    public void render(Graphics2D g){
        objects.forEach(x->x.render(g));
    }
    public void tick(){
        objects.forEach(x -> x.tick());
        objects = objects.stream()
                .filter(x -> x.isAlive())
                .collect(Collectors.toList());
        objects.forEach(x -> collision(x));
    }
    private void collision(GameObject o){
        objects.stream().filter(x -> x.isColliding(o)).forEach(x -> {
            o.onCollision(x);
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
        objects.stream().filter(x -> !(x instanceof Player)).forEach(x -> x.setY(x.getY() + dy));
    }
    public void changeX(int dx){
        objects.stream().filter(x -> !(x instanceof Player)).forEach(x -> x.setX(x.getX() + dx));
    }
    public Image getImage(){
        return background;
    }
}

