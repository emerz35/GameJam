package gameobjects;

import animations.Animation;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Main;

/**
 *
 * @author Charlie
 */
public class Bullet extends GameObject{
    
    public static BufferedImage DEFAULT_BULLET_IMAGE = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);;
    static{
        try{
            DEFAULT_BULLET_IMAGE = ImageIO.read(Main.class.getResource("/images/bullet.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        
    }
    public static final Bullet DEFAULT_BULLET = new Bullet(0,0,0,0,20,0,new Animation(new Image[]{DEFAULT_BULLET_IMAGE}, new int[]{1}, false));
    
    public int velx,vely, speed, accuracy,damage;

    public Bullet(int x, int y, int velx, int vely,int speed,int damage, Animation a){
        super(x,y,4,4,20,true,a);
        this.velx = velx;
        this.vely = vely;
        this.speed = speed;
        this.accuracy = 20;
        this.damage = damage;
    }
    
    @Override
    public void tick() {
        setX(getX()+velx);
        setY(getY()+vely);
    }

    @Override
    public void onCollision(Collision collideWith) {
        if(!(collideWith instanceof Mobile))remove();
        else if(!(collideWith instanceof Player)&&((Mobile)collideWith).isAlive())remove();
    }
    
    public Bullet clone(int x, int y, int velx, int vely, int damage){
        return new Bullet(x,y,velx,vely,speed,damage,currentAnimation);
    }
    
}
