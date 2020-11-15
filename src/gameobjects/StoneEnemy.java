package gameobjects;

import animations.Animation;
import main.Utils;

/**
 *
 * @author Charlie
 */
public class StoneEnemy extends Mobile{
    
    //0 - right, 1 - left, 2 - rMove, 3- lMove, 4 -rAttack1, 5 - lAttack1
    //6 - rAttack2, 7 - lAttack2, 8 - rDead, 9 - lDead
    private final Animation[] animations;
    
    public Player player;
    
    private boolean noticed = false;
    
    private int charge = 0,  recover = 0;
    private final int maxCharge = 60, maxRecover = 120, chargeSpeed = 4*speed, noticeDistSqu = 1000000, chargeChance = 1;
    
    public StoneEnemy(int x, int y, int w,int h,int speed, int health, int damage, float crit, float kT, float kG,
            Animation[] animations, Player p){
        super(x,y,w,h,10,speed,health,damage,crit,kT,kG);
        this.animations = animations;
        player = p;
    }

    @Override
    public void die() {
        if(dir==Direction.RIGHT)setAnimation(animations[8]);
        else setAnimation(animations[9]);
        canWalk = true;
    }
    
    
    @Override
    public void tick() {
        if(!isAlive())return;
        //System.out.println(kvelx);
        x+=velx+kvelx;
        y+=vely+kvely;
        kvely*=KNOCKBACK_DECAY;
        kvelx*=KNOCKBACK_DECAY;
        if(!noticed){
            int dx = player.getX()-getX();
            int dy = player.getY()-getY();
            if(dx*dx+dy*dy <= noticeDistSqu)noticed = true;
        }
        if(charge>0){
            if(dir==Direction.RIGHT)setAnimation(animations[4]);
            else setAnimation(animations[5]);
            charge--;
            if(charge <= 0){
                recover=maxRecover;
                velx = 0;
                vely = 0;
            }
        }else if(recover > 0){
            if(dir==Direction.RIGHT)setAnimation(animations[6]);
            else setAnimation(animations[7]);
            recover--;
        }
        else if(velx==0&&vely==0){
            if(dir==Direction.RIGHT)setAnimation(animations[0]);
            else setAnimation(animations[1]);
        }
        else {
            if(dir==Direction.RIGHT)setAnimation(animations[2]);
            else setAnimation(animations[3]);
        }
        if(charge<=0&&recover<=0&&noticed){
            int dx = player.getX()-getX();
            int dy = player.getY()-getY();
            velx = (int)((double) speed * (double)dx/Math.sqrt(dx*dx+dy*dy));
            vely = (int)((double) speed * (double)dy/Math.sqrt(dx*dx+dy*dy));
            if(Utils.getUtils().getRandInt(0, 1000)<chargeChance){
                charge = maxCharge;
                kvelx = 0;
                kvely = 0;
                velx = (int)((double) chargeSpeed * (double)dx/Math.sqrt(dx*dx+dy*dy));
                vely = (int)((double) chargeSpeed * (double)dy/Math.sqrt(dx*dx+dy*dy));
            }
            
        }
    }

    @Override
    public void onCollision(Collision collideWith) {
        if(collideWith instanceof Bullet&&isAlive()){
            Bullet b = (Bullet) collideWith;
            changeHealth(b.damage);
            if(charge<=0)knockback((float)b.velx/(float)b.speed,(float)b.vely/(float)b.speed, 10);
        }else if (charge>0){
            charge = 0;
            recover = maxRecover;
            velx = 0;
            vely = 0;
        }
   }
   
}
