package main;

import gameobjects.Player;
import gameobjects.factories.PlayerFactory;
import inputs.KeyHandler;
import inputs.MouseHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import stages.Room;
import stages.RoomHandler;
import stages.SavedRoom;
import stages.generators.BasicRoomGenerator;
import stages.generators.RoomGenerator;

/**
 *
 * @author charl
 */
public class Renderer implements Runnable{
    private final Main main;
    private boolean running = true;
    private final RoomHandler roomHandler;
    private final KeyHandler keyHandler;
    private final MouseHandler mouseHandler;
    private final RoomGenerator gen = new BasicRoomGenerator();
    private final PlayerFactory pf = new PlayerFactory();
    
    public Renderer(Main m){
        main = m;
        Player player = pf.createPlayer();
        SavedRoom sRoom = SavedRoom.getSavedRoom("room1");
        if(sRoom == null)sRoom = gen.createRoom(player,1);
        Room firstRoom = sRoom.buildRoom(player);
        roomHandler = new RoomHandler(firstRoom,player);
        keyHandler = new KeyHandler(roomHandler);
        mouseHandler = new MouseHandler(player);
        m.addKeyListener(keyHandler);
        m.addMouseListener(mouseHandler);
        m.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void run() {
        float delay = 1f/60f;
        long before = System.currentTimeMillis(), now, fcounter = System.currentTimeMillis();
        int frames = 0;
        while(running){
            now = System.currentTimeMillis();
            if(now-before>=delay*1000){
                tick();
                render();
                before = now;
                frames++;
            }
            if(System.currentTimeMillis() - fcounter >=1000){
                //System.out.println(frames);
                frames = 0;
                fcounter = System.currentTimeMillis();
            }
        }
        quit();
    }
    
    public void render(){
        BufferStrategy bs = main.getBufferStrategy();
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.FWIDTH, Main.FHEIGHT);
        
        roomHandler.render(g);
        g.dispose();
        bs.show();
    }
    
    public void tick(){
        roomHandler.tick();
        keyHandler.tick();
        mouseHandler.tick();
    }
    
    public void quit(){
        try{
            main.runThread.join();
            running = false;
        }catch(InterruptedException e){
            //e.printStackTrace(System.err);
            System.err.println("Fail in stop() method of Main");
        }
    }
}
