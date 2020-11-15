package gameobjects;

import java.io.Serializable;

/**
 *
 * @author Charlie
 */
public abstract class Collision implements Serializable{
    protected int x,y;
    protected final int width,height,collisionPrecedence;
    
    public Collision(int x, int y, int w, int h, int cp){
        this.x=x;
        this.y = y;
        width = w;
        height = h;
        collisionPrecedence = cp;
    }
    
    public boolean isColliding(Collision c){
        return x < c.getX() + c.getWidth() &&
                x + width > c.getX() &&
                y < c.getY() + c.getHeight() &&
                y + height >c.getY();
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

   
    public int getHeight() {
        return height;
    }

    public abstract void onCollision(Collision collideWith);
}
