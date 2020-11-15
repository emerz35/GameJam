package gameobjects.factories;

import animations.Animation;
import gameobjects.Collision;
import gameobjects.StoneEnemy;
import static gameobjects.factories.PlayerFactory.BLANK_PLAYER_IMAGE;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Main;
import main.Utils;
import stages.Room;
import static stages.Tile.TILE_HEIGHT;
import static stages.Tile.TILE_WIDTH;

/**
 *
 * @author Charlie
 */
public class EnemyFactory {
    
    public static final int VARIANCE = 5;
    public static final int STONE_HEALTH = 10;
    public static final int STONE_SPEED = 2;
    public static final int STONE_DAMAGE = 2;
    public static final float STONE_CRIT = 1.2f;
    public static final float STONE_KNOCKBACK_T = 0.5f;
    public static final float STONE_KNOCKBACK_G = 5;
    public static final int STONE_WIDTH = 38;
    public static final int STONE_HEIGHT = 24;
   
    public StoneEnemy createStoneEnemy(int level, Room room){
        Image stationary = BLANK_PLAYER_IMAGE, lstationary = BLANK_PLAYER_IMAGE,
                walk1 = BLANK_PLAYER_IMAGE, lwalk1 = BLANK_PLAYER_IMAGE,
                walk2 = BLANK_PLAYER_IMAGE, lwalk2 = BLANK_PLAYER_IMAGE,
                attack1 = BLANK_PLAYER_IMAGE,lattack1 = BLANK_PLAYER_IMAGE,
                attack2 = BLANK_PLAYER_IMAGE, lattack2 = BLANK_PLAYER_IMAGE,
                dead1 = BLANK_PLAYER_IMAGE, ldead1 = BLANK_PLAYER_IMAGE,
                dead2 = BLANK_PLAYER_IMAGE, ldead2 = BLANK_PLAYER_IMAGE;
        
        try{
            stationary = ImageIO.read(Main.class.getResource("/images/stone1.png"));
            lstationary = ImageIO.read(Main.class.getResource("/images/lstone1.png"));
            walk1 = ImageIO.read(Main.class.getResource("/images/stone move1.png"));
            lwalk1 = ImageIO.read(Main.class.getResource("/images/lstone move1.png"));
            walk2 = ImageIO.read(Main.class.getResource("/images/stone move2.png"));
            lwalk2 = ImageIO.read(Main.class.getResource("/images/lstone move2.png"));
            attack1 = ImageIO.read(Main.class.getResource("/images/stone attack.png"));
            lattack1 = ImageIO.read(Main.class.getResource("/images/lstone attack.png"));
            attack2 = ImageIO.read(Main.class.getResource("/images/stone attack2.png"));
            lattack2 = ImageIO.read(Main.class.getResource("/images/lstone attack2.png"));
            dead1 = ImageIO.read(Main.class.getResource("/images/stone dead1.png"));
            ldead1 = ImageIO.read(Main.class.getResource("/images/lstone dead1.png"));
            dead2 = ImageIO.read(Main.class.getResource("/images/stone dead2.png"));
            ldead2 = ImageIO.read(Main.class.getResource("/images/lstone dead2.png"));
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
        Animation rStationary = new Animation(new Image[]{stationary},new int[]{1},false),
                lStationary = new Animation(new Image[]{lstationary}, new int[]{1},false),
                rMove = new Animation(new Image[]{walk1,walk2}, new int[]{60,60},true),
                lMove = new Animation(new Image[]{lwalk1,lwalk2}, new int[]{60,60},true),
                rAttack1 = new Animation(new Image[]{attack1}, new int[]{1},false),
                lAttack1 = new Animation(new Image[]{lattack1}, new int[]{1},false),
                rAttack2 = new Animation(new Image[]{attack2}, new int[]{1},false),
                lAttack2 = new Animation(new Image[]{lattack2}, new int[]{1},false),
                dead = new Animation(new Image[]{dead1,dead2}, new int[]{120,120},false),
                ldead = new Animation(new Image[]{ldead1,ldead2},new int[]{120,120},false);
        
        Utils u = Utils.getUtils();
        int roomX = room.background.x,roomY = room.background.y;
        int x = u.getRandInt(roomX, roomX+room.background.getTilesWidth()*TILE_WIDTH), 
                y = u.getRandInt(roomY, roomY+room.background.getTilesHeight()*TILE_HEIGHT);
        while(checkColliding(x,y,STONE_WIDTH,STONE_HEIGHT,room)){
            x = u.getRandInt(roomX, roomX+room.background.getTilesWidth()*TILE_WIDTH);
            y = u.getRandInt(roomY, roomY+room.background.getTilesHeight()*TILE_HEIGHT);
        }
        return new StoneEnemy(x,y,STONE_WIDTH,STONE_HEIGHT,STONE_SPEED,
                STONE_HEALTH*(level+1)+u.getRandInt(-VARIANCE, VARIANCE),STONE_DAMAGE*(level+1),
                STONE_CRIT,STONE_KNOCKBACK_T,STONE_KNOCKBACK_G, new Animation[]{rStationary,lStationary,
                rMove,lMove,rAttack1,lAttack1,rAttack2,lAttack2,dead,ldead},room.player);
    }
    
    private boolean checkColliding(int x, int y, int width, int height,Room room){
        for(int ty = y-TILE_HEIGHT; ty<y+height+TILE_HEIGHT;ty+=TILE_HEIGHT){
            for(int tx = x-TILE_WIDTH; tx<x+width+TILE_WIDTH;tx+=TILE_WIDTH){
                int relX = room.background.coordToRelX(x), relY = room.background.coordToRelY(y);
                if(relY>=0&&relY<room.background.getTilesHeight()&&relX>=0&&relX<room.background.getTilesWidth()){
                    Collision c = room.background.tileToCollision(relX, relY);
                    if(isColliding(c,x,y,width,height)){
                        return !room.background.getTile(relX,relY).enemyCanSpawn;
                    }
                }
            }
        }
        return false;
    }
    public boolean isColliding(Collision c, int x, int y, int width, int height){
        return x < c.getX() + c.getWidth() &&
                x + width > c.getX() &&
                y < c.getY() + c.getHeight() &&
                y + height >c.getY();
    }
}
