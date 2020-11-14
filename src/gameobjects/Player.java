package gameobjects;

import java.awt.Graphics2D;

/**
 *
 * @author Charlie
 */
public class Player extends GameObject{
    
    public Player(int x, int y, int w, int h){
        super(x,y,w,h);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public void onCollision(Collision collideWith) {
    }
    
}
