package items;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author Charlie
 */
public class Item {
    
    public String name;
    private final Image image;
    
    public Item(String name, Image i){
        this.name = name;
        this.image = i;
    }
    
    public void render(Graphics2D g, int x, int y){
        g.drawImage(image, x, y, null);
    }
}
