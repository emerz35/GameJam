/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author charl
 */
public class Renderer implements Runnable{
    private final Main main;
    private boolean running = true;
    
    public Renderer(Main m){
        main = m;
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
                System.out.println(frames);
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
        
        g.dispose();
        bs.show();
    }
    
    public void tick(){
    
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
