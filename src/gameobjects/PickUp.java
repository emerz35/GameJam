package gameobjects;

import animations.Animation;
import items.Item;

/**
 *
 * @author Charlie
 */
public class PickUp extends GameObject{
    
    private Item item;
    public PickUp(int x, int y, int width, int height, Item i){
        super(x,y,width,height,10,true);
        item = i;
    }
    public PickUp(int x, int y, int width, int height, Item i,Animation a){
        super(x,y,width,height,10,true,a);
        item = i;
    }
    

    @Override
    public void tick() {
    }

    @Override
    public void onCollision(Collision collideWith) {
        if(collideWith instanceof Player){
            Player p = (Player) collideWith;
            p.items.add(item);
            p.addMessage(item.name + " collected!", 120);
            remove();
        }
    }
    
}
