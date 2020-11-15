package message;

import gameobjects.Player;

/**
 *
 * @author Charlie
 */
public class Message {
    private final String str;
    private int duration;
    private final Player player;
    public Message(String str, int frames, Player p){
        this.str = str;
        duration = frames;
        player = p;
    }
    
    public void tick(){
        duration--;
        if(duration<=0)player.messages.remove(this);
    }
    
    public String getMessage(){
        return str;
    }
}
