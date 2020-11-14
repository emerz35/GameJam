package gameobjects;

import java.awt.Graphics2D;

/**
 *
 * @author Charlie
 */
public abstract class GameObject extends Collision{
    private int velx,vely;
    private boolean isAlive = true;
    
    public GameObject(int x, int y, int w, int h){
        super(x,y,w,h);
    }
    
    public void remove(){
        isAlive = false;
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public abstract void tick();
    public abstract void render(Graphics2D g);
}
