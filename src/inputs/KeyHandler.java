package inputs;

import gameobjects.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import stages.RoomHandler;

/**
 *
 * @author Charlie
 */
public class KeyHandler extends KeyAdapter{
    
    private final Player player;
    
    boolean keys[] = {false,false,false,false};
    
    public KeyHandler(RoomHandler rh){
        player = rh.player;
    }
    
    public void tick(){
        int velX = 0;
        int velY = 0;
        if(keys[0]){
            velY -= player.speed;
        }
        if(keys[1]){
            //player.changeDirection(Direction.LEFT);
            velX -= player.speed;
        }
        if(keys[2]){
            velY += player.speed;
        }
        if(keys[3]){
            //player.changeDirection(Direction.RIGHT);
            velX += player.speed;
        }
        player.velx = velX;
        player.vely = velY;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                keys[0] = true;
                break;
            case KeyEvent.VK_A:
                keys[1] = true;
                break;
            case KeyEvent.VK_S:
                keys[2] = true;
                break;
            case KeyEvent.VK_D:
                keys[3] = true;
                break;
            case KeyEvent.VK_ENTER:
                player.isInteracting = true;
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                keys[0] = false;
                break;
            case KeyEvent.VK_A:
                keys[1] = false;
                break;
            case KeyEvent.VK_S:
                keys[2] = false;
                break;
            case KeyEvent.VK_D:
                keys[3] = false;
                break;
            case KeyEvent.VK_ENTER:
                player.isInteracting = false;
                break;
            default:
                break;
        }
    }
}
