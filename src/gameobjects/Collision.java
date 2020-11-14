package gameobjects;

/**
 *
 * @author Charlie
 */
public abstract class Collision {
    private int x,y,width,height;
    
    public Collision(int x, int y, int w, int h){
        this.x=x;
        this.y = y;
        width = w;
        height = h;
    }
    
    public boolean isColliding(Collision c){
        return (c.getX()>=x&&c.getX()<=x+width&&c.getY()>=y&&c.getY()<=y+height)
                || (x>=c.getX()&&x<=c.getX()+c.getWidth()&&y>=c.getY()&&y<=c.getY()+c.getHeight());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public abstract void onCollision(Collision collideWith);
}
