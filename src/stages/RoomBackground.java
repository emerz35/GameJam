package stages;

import gameobjects.Collision;
import gameobjects.Mobile;
import java.awt.Graphics2D;
import java.io.Serializable;
import main.Main;
import static stages.Tile.TILE_HEIGHT;
import static stages.Tile.TILE_WIDTH;

/**
 *
 * @author Charlie Hands
 */
public class RoomBackground implements Serializable{

    public int x = 10,y = 10;
    private final Tile[][] tiles;
    
    public RoomBackground(Tile[][] tiles){
        this.tiles = tiles;
    }
    
    public RoomBackground(int width, int height){
        this.tiles = new Tile[height][width];
    }
    
    public Tile coordToTile(int winX, int winY){
        int tx = coordToRelX(winX);
        int ty = coordToRelY(winY);
        
        return tiles[ty][tx];
    }
    
    public int coordToRelX(int winX){
        return Math.floorDiv((winX-x),TILE_WIDTH);
    }
    
    public int coordToRelY(int winY){
        return Math.floorDiv((winY-y),TILE_HEIGHT);
    }
    
    public Collision tileToCollision(int x, int y){
        //System.out.println(coordToRelX(tileToAbsX(x)));
        return new Collision(tileToAbsX(x), tileToAbsY(y), TILE_WIDTH, TILE_HEIGHT, 0){
            @Override
            public void onCollision(Collision collideWith) {
                //System.out.println(coordToRelX(this.getX()));
                Tile t = coordToTile(this.getX(), this.getY());
                if(collideWith instanceof Mobile) {
                    
                    t.steppedOn((Mobile)collideWith);
                    if(!t.canWalk){
                        
                        ((Mobile)collideWith).zeroVelocityWith(this);
                    }
                }
            }
        };
    }
    
    public int tileToAbsX(int tx){
        return x+tx * TILE_WIDTH;
    }
    
    public int tileToAbsY(int ty){
        return ty * TILE_HEIGHT + y;
    }
    
    public void render(Graphics2D g){
        for(int y = 0; y < tiles.length;y++){
            for(int x = 0; x < tiles[y].length;x++){
                int absX = tileToAbsX(x), absY = tileToAbsY(y);
                if(absX<Main.FWIDTH&&absX+TILE_WIDTH>0
                        &&absY<Main.FHEIGHT &&absY+TILE_HEIGHT>0) {
                    tiles[y][x].render(g,absX,absY);
                }
            }
        }
    }
    
    public void setTile(int tx, int ty, Tile tile){
        tiles[y][x] = tile;
    }
    
    public Tile getTile(int x, int y){
        return tiles[y][x];
    }
    
    public int getTilesWidth(){
        return tiles[0].length;
    }
    public int getTilesHeight(){
        return tiles.length;
    }
}
