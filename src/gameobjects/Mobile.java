package gameobjects;

import animations.Animation;
import main.Utils;

/**
 *
 * @author Charlie
 */
public abstract class Mobile extends GameObject{
    
    public enum Direction{
        LEFT(1,0),RIGHT(-1,0);
        
        int dx,dy;
        
        Direction(int x, int y){
            dx = x;
            dy = y;
        }
    }
    
    public static final float KNOCKBACK_DECAY = 0.95f;
    public int speed, health,velx,vely,damage,kvelx,kvely;
    public float maxCrit,knockbackTaken, knockbackGiven;
    
    
    public Direction dir = Direction.RIGHT;
    
    public Mobile(int x, int y, int w, int h,int p, int speed, int health, int damage, float maxCrit,float knockbackT, float knockbackG){
        super(x,y,w,h,p,false);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.maxCrit = maxCrit;
        knockbackTaken = knockbackT;
        knockbackGiven = knockbackG;
    }
    public Mobile(int x, int y, int w, int h,int p, int speed, int health, int damage, float maxCrit,float knockbackT, float knockbackG, Animation a){
        super(x,y,w,h,p,false,a);
        this.speed = speed;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.maxCrit = maxCrit;
        knockbackTaken = knockbackT;
        knockbackGiven = knockbackG;
    }
    
    public void changeDirection(Direction d){
        dir = d;
    }
    
    public void changeHealth(int change){
        health -= change;
        if(health<=0)die();
    }
    
    public abstract void die();
    
    public int getDamage(){
        return (int)((float)damage *maxCrit *(float)Utils.getUtils().getRandInt(0, 100)/100f);
    }
    
    public void knockback(float dx, float dy, float knockbackG){
        kvelx=(int)(dx*knockbackTaken*knockbackG);
        kvely=(int)(dy*knockbackTaken*knockbackG);
    }
    
    public boolean isAlive(){
        return health>0;
    }
    
    public void zeroVelocityWith(Collision col){
        if(collisionPrecedence<col.collisionPrecedence) return;
        //System.out.println("Yes");
        if(x<col.x){
            if(y<col.y){
                if(x+width-col.x<y+height-col.y){
                    setX(col.x-width);
                    if(velx>0) velx = 0;
                }else{
                    setY(col.y-height);
                    if(vely>0) vely = 0;
                }
            }else{
                if(x+width-col.x<col.y+col.height-y){
                    setX(col.x-width);
                    if(velx>0) velx = 0;
                }else{
                    setY(col.y+col.height);
                    if(vely<0) vely = 0;
                }
            }
        }else{
            if(y<col.y){
                if(col.x+col.width-x<y+height-col.y){
                    setX(col.x+col.width);
                    if(velx<0) velx = 0;
                }else{
                    setY(col.y-height);
                    if(vely>0) vely = 0;
                }
            }else{
                if(col.x+col.width-x<col.y+col.height-y){
                    setX(col.x+col.width);
                    if(velx<0) velx = 0;
                }else{
                    setY(col.y+col.height);
                    if(vely<0) vely = 0;
                }
            }
        }
    }
}
