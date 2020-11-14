package main;

import java.awt.Canvas;

/**
 *
 * @author Charlie
 */
public class Main extends Canvas{
    public Thread runThread;
    
    public static final int FWIDTH = 800,FHEIGHT = 600;
    
    public void start(){
        if(this.getBufferStrategy() == null){
            this.createBufferStrategy(4);
        }
        Renderer r = new Renderer(this);
        runThread = new Thread(r);
        runThread.setDaemon(true);
        runThread.start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Window("Game",Main.FWIDTH, Main.FHEIGHT,new Main());
    }
    
}
