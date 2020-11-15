package inputs;

import static gameobjects.Bullet.DEFAULT_BULLET;
import gameobjects.Mobile.Direction;
import gameobjects.Player;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Charlie
 */
public class MouseHandler extends MouseAdapter{
    
    
    private Player player;
    
    private int mx = 0,my = 0, shootCooldown = 0;
    public MouseHandler(Player p){
        player = p;
    }
    
    public void tick(){
        if(player.shooting && shootCooldown <= 0){
            player.shoot(DEFAULT_BULLET, mx, my);
            shootCooldown = player.shootCooldown;
        }
        if(shootCooldown>0)shootCooldown--;
        if(mx>player.getX()+player.getWidth()/2)player.changeDirection(Direction.RIGHT);
        else player.changeDirection(Direction.LEFT);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1)player.shooting = true;
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1)player.shooting = false;
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }
}
