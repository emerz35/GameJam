package gameobjects.factories;

import animations.Animation;
import gameobjects.Player;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Main;

/**
 *
 * @author Charlie
 */
public class PlayerFactory {
    
    private static final int PLAYER_WIDTH = 32, PLAYER_HEIGHT = 22;
    public static final BufferedImage BLANK_PLAYER_IMAGE = new BufferedImage(PLAYER_WIDTH,PLAYER_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    
    public Player createPlayer(){
        Image walk1 = BLANK_PLAYER_IMAGE,walk2 = BLANK_PLAYER_IMAGE,
                walk3= BLANK_PLAYER_IMAGE,pistol1= BLANK_PLAYER_IMAGE,
                pistol2= BLANK_PLAYER_IMAGE,pistol3= BLANK_PLAYER_IMAGE,
                dead1= BLANK_PLAYER_IMAGE,dead2= BLANK_PLAYER_IMAGE,
                lwalk1 = BLANK_PLAYER_IMAGE,lwalk2 = BLANK_PLAYER_IMAGE,lwalk3 = BLANK_PLAYER_IMAGE,
                lpistol1= BLANK_PLAYER_IMAGE,lpistol2= BLANK_PLAYER_IMAGE,lpistol3= BLANK_PLAYER_IMAGE,
                ldead1= BLANK_PLAYER_IMAGE,ldead2= BLANK_PLAYER_IMAGE;
        try{
            walk1 = ImageIO.read(Main.class.getResource("/images/walk1.png"));
            walk1 = walk1.getScaledInstance(walk1.getWidth(null)*2, walk1.getHeight(null)*2, Image.SCALE_SMOOTH);
            lwalk1 = ImageIO.read(Main.class.getResource("/images/lwalk1.png"));
            lwalk1 = lwalk1.getScaledInstance(lwalk1.getWidth(null)*2, lwalk1.getHeight(null)*2, Image.SCALE_SMOOTH);
            walk2 = ImageIO.read(Main.class.getResource("/images/walk2.png"));
            walk2 = walk2.getScaledInstance(walk2.getWidth(null)*2, walk2.getHeight(null)*2, Image.SCALE_SMOOTH);
            lwalk2 = ImageIO.read(Main.class.getResource("/images/lwalk2.png"));
            lwalk2 = lwalk2.getScaledInstance(lwalk2.getWidth(null)*2, lwalk2.getHeight(null)*2, Image.SCALE_SMOOTH);
            walk3 = ImageIO.read(Main.class.getResource("/images/walk3.png"));
            walk3 = walk3.getScaledInstance(walk3.getWidth(null)*2, walk3.getHeight(null)*2, Image.SCALE_SMOOTH);
            lwalk3 = ImageIO.read(Main.class.getResource("/images/lwalk3.png"));
            lwalk3 = lwalk3.getScaledInstance(lwalk3.getWidth(null)*2, lwalk3.getHeight(null)*2, Image.SCALE_SMOOTH);
            pistol1 = ImageIO.read(Main.class.getResource("/images/pistol1.png"));
            pistol1 = pistol1.getScaledInstance(pistol1.getWidth(null)*2, pistol1.getHeight(null)*2, Image.SCALE_SMOOTH);
            lpistol1 = ImageIO.read(Main.class.getResource("/images/lpistol1.png"));
            lpistol1 = lpistol1.getScaledInstance(lpistol1.getWidth(null)*2, lpistol1.getHeight(null)*2, Image.SCALE_SMOOTH);
            pistol2 = ImageIO.read(Main.class.getResource("/images/pistol2.png"));
            pistol2 = pistol2.getScaledInstance(pistol2.getWidth(null)*2, pistol2.getHeight(null)*2, Image.SCALE_SMOOTH);
            lpistol2 = ImageIO.read(Main.class.getResource("/images/lpistol2.png"));
            lpistol2 = lpistol2.getScaledInstance(lpistol2.getWidth(null)*2, lpistol2.getHeight(null)*2, Image.SCALE_SMOOTH);
            pistol3 = ImageIO.read(Main.class.getResource("/images/pistol3.png"));
            pistol3 = pistol3.getScaledInstance(pistol3.getWidth(null)*2, pistol3.getHeight(null)*2, Image.SCALE_SMOOTH);
            lpistol3 = ImageIO.read(Main.class.getResource("/images/lpistol3.png"));
            lpistol3 = lpistol3.getScaledInstance(lpistol3.getWidth(null)*2, lpistol3.getHeight(null)*2, Image.SCALE_SMOOTH);
            dead1 = ImageIO.read(Main.class.getResource("/images/dead1.png"));
            dead1 = dead1.getScaledInstance(dead1.getWidth(null)*2, dead1.getHeight(null)*2, Image.SCALE_SMOOTH);
            ldead1 = ImageIO.read(Main.class.getResource("/images/ldead1.png"));
            ldead1 = ldead1.getScaledInstance(ldead1.getWidth(null)*2, ldead1.getHeight(null)*2, Image.SCALE_SMOOTH);
            dead2 = ImageIO.read(Main.class.getResource("/images/dead2.png"));
            dead2 = dead2.getScaledInstance(dead2.getWidth(null)*2, dead2.getHeight(null)*2, Image.SCALE_SMOOTH);
            ldead2 = ImageIO.read(Main.class.getResource("/images/ldead2.png"));
            ldead2 = ldead2.getScaledInstance(ldead2.getWidth(null)*2, ldead2.getHeight(null)*2, Image.SCALE_SMOOTH);
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
        
        
        Animation rightstationary = new Animation(new Image[]{walk1},new int[]{0},false),
                leftstationary = new Animation(new Image[]{lwalk1},new int[]{0},false),
                rightwalk = new Animation(new Image[]{walk2,walk3}, new int[]{20,20},true),
                leftwalk = new Animation(new Image[]{lwalk2,lwalk3}, new int[]{20,20},true),
                rightattack = new Animation(new Image[]{pistol1,pistol2,pistol3}, new int[]{10,10,40}, false),
                leftattack = new Animation(new Image[]{lpistol1,lpistol2,lpistol3}, new int[]{10,10,40}, false),
                dead = new Animation(new Image[]{dead1,dead2}, new int[]{60,60}, false),
                leftdead = new Animation(new Image[]{ldead1,ldead2}, new int[]{60,60}, false);
        return new Player(Main.FWIDTH/2, Main.FHEIGHT/2,PLAYER_WIDTH,PLAYER_HEIGHT,rightstationary,leftstationary,
                rightwalk,leftwalk,rightattack,leftattack,dead,leftdead);
    }
}
