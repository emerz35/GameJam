package animations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Charlie
 */
public class Animation implements Serializable{
    private final Image images[];
    private final int frameLengths[];
    private final boolean repeats;
    
    private int num = 0, animationNum = 0;
    
    private boolean ended = false;
    
    public Animation(Image[] images, int[] frameLengths, boolean repeats){
        this.images = images;
        this.frameLengths = frameLengths;
        this.repeats = repeats;
    }
    
    public void render(Graphics2D g, int x, int y){
        g.drawImage(images[animationNum],x,y,null);
        num++;
        if(num > frameLengths[animationNum]){
            num = 0;
            animationNum++;
        }
        if(repeats)animationNum %= images.length;
        else if(animationNum >= images.length) {
            animationNum = images.length -1;
            ended = true;
        }
    }
    public boolean hasEnded(){
        return ended;
    }
    public void reset(){
        num = 0;
        animationNum = 0;
        ended = false;
    }
}
