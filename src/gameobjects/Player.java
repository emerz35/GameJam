package gameobjects;

import animations.Animation;
import items.Item;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import main.Main;
import main.Utils;
import message.Message;
import stages.RoomHandler;

/**
 *
 * @author Charlie
 */
public class Player extends Mobile{
    
    List<Item> items = new LinkedList<>();
    public List<Message> messages = new LinkedList<>();
    
    public boolean isInteracting = false;
    
    public RoomHandler roomHandler;
    
    public int shootCooldown = 40, invinc = 0, maxInvinc = 30;
    
    public boolean shooting = false;
    
    public final Animation rightStationary,leftStationary,left, right, leftAttack,
                   rightAttack,leftdeath,  rightdeath;
    
    public Player(int x, int y, int w, int h, Animation rightStationary, Animation leftStationary,
            Animation right, Animation left, Animation rightAttack,Animation leftAttack, Animation rightdeath, Animation leftdeath){
        super(x,y,w,h,5,5,10,5,1.5f,2f,1,rightStationary);
        this.rightStationary = rightStationary;
        this.leftStationary = leftStationary;
        this.left = left;
        this.right = right;
        this.leftAttack = leftAttack;
        this.rightAttack = rightAttack;
        this.leftdeath = leftdeath;
        this.rightdeath = rightdeath;
    }

    @Override
    public void tick() {
        messages.forEach(x->x.tick());
        kvelx*=KNOCKBACK_DECAY;
        kvely*=KNOCKBACK_DECAY;
        
        if(!isAlive()) return;
        if(invinc>0)invinc--;
        
        if(currentAnimation.hasEnded()&&currentAnimation.equals(leftAttack)||
                currentAnimation.hasEnded()&&currentAnimation.equals(rightAttack)||
                (!currentAnimation.equals(rightAttack)&&!currentAnimation.equals(leftAttack))){
           
            if(velx == 0&&vely==0){
                if(dir == Direction.RIGHT) setAnimation(rightStationary);
                else setAnimation(leftStationary);
            }
            else{
                if(dir == Direction.RIGHT)setAnimation(right);
                else setAnimation(left);
            }
        }
        
    }

    @Override
    public void onCollision(Collision c) {
        if(c instanceof Mobile && invinc<=0&&isAlive()) {
            
            Mobile mob = (Mobile)c;
            if(mob.isAlive()){
                int healthLost = mob.getDamage();
                changeHealth(healthLost);
                invinc = maxInvinc;
                int dx = getX()-c.getX();
                int dy = getY()-c.getY();
                float kx = (float)((double)dx/Math.sqrt(dx*dx+dy*dy));
                float ky = (float)((double)dy/Math.sqrt(dx*dx+dy*dy));
                knockback(kx,ky,mob.knockbackGiven);
            }
        }
        if(c instanceof Door&&isInteracting){
            Door door = (Door) c;
            setX(Main.FWIDTH/2);
            setY(Main.FHEIGHT/2);
            roomHandler.setRoom(door.goTo.buildRoom(this));
        }
    }
    
    @Override
    public void render(Graphics2D g){
        currentAnimation.render(g, x, y-28);
        renderMessages(g);
    }
    
    @Override
    public void die() {
        if(dir==Direction.LEFT)setAnimation(leftdeath);
        else setAnimation(rightdeath);
        health = 0;
    }
    
    public void shoot(Bullet bullet,int mx, int my){
        if(dir == Direction.RIGHT){
            rightAttack.reset();
            setAnimation(rightAttack);
        }
        else {
            leftAttack.reset();
            setAnimation(leftAttack);
        }
        int bx = getX()+getWidth()/2;
        int by = getY();
        int dx = mx - bx+Utils.getUtils().getRandInt(-bullet.accuracy, bullet.accuracy);
        int dy = my - by+Utils.getUtils().getRandInt(-bullet.accuracy, bullet.accuracy);
        int vy = (int)((double)bullet.speed*(double)dy/Math.sqrt(dx*dx+dy*dy));
        int vx = (int)((double)bullet.speed*(double)dx/Math.sqrt(dx*dx+dy*dy));
        Bullet b = bullet.clone(bx,by,vx,vy,getDamage());
        roomHandler.room.addObject(b);
    }
    
    public void renderMessages(Graphics2D g){
        g.setFont(new Font("Arial",Font.ITALIC,15));
        g.setColor(Color.cyan);
        
        for(int i = 0; i < messages.size();i++){
            int length = g.getFontMetrics().stringWidth(messages.get(i).getMessage());
            int mHeight = g.getFontMetrics().getHeight();
            g.drawString(messages.get(i).getMessage(),getX()+getWidth()/2-length/2,getY()-28-mHeight*i);
        }
        
    }
    public void addMessage(String str, int duration){
        messages.add(new Message(str,duration,this));
    }
    
}
