package gameobjects;

import animations.Animation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Charlie
 */
public abstract class GameObject extends Collision{
    
    private boolean exists = true; 
    public boolean canWalk;
    
    protected Animation currentAnimation;
    
    public GameObject(int x, int y, int w, int h, int cp,boolean canWalk, Animation a){
        super(x,y,w,h,cp);
        currentAnimation = a;
        this.canWalk = canWalk;
    }
    public GameObject(int x, int y, int w, int h, int cp, boolean canWalk){
        super(x,y,w,h,cp);
        currentAnimation = new Animation(new BufferedImage[]{new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB)}, new int[]{0},false);
        this.canWalk = canWalk;
    }
    
    public void remove(){
        exists = false;
    }
    
    public boolean doesExist(){
        return exists;
    }
    
    public abstract void tick();
    public void render(Graphics2D g){
        currentAnimation.render(g, x, y);
    }
    
    public void setAnimation(Animation a){
        if(!currentAnimation.equals(a)){
            currentAnimation.reset();
            currentAnimation = a;
        }
    }
}
