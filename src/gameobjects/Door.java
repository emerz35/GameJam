package gameobjects;

import animations.Animation;
import stages.SavedRoom;

/**
 *
 * @author Charlie
 */
public class Door extends GameObject{
    public SavedRoom goTo;
    public Door(int x, int y, int width, int height,SavedRoom goTo, Animation a){
        super(x,y,width,height,0,true,a);
        this.goTo = goTo;
    }

    @Override
    public void tick() {
    }

    
    
    @Override
    public void onCollision(Collision collideWith) {
        if(collideWith instanceof Player){
            Player p = (Player)collideWith;
            if(p.items.stream().anyMatch(x-> x.name.equalsIgnoreCase("key")))
                p.addMessage("You have a key, press enter to proceed!", 0);
            else p.addMessage("You need a key to proceed! Defeat all the enemies in the room to find one!", 0);
        }
    }
    
}
