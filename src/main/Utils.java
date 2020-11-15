package main;

import java.util.Random;

/**
 *
 * @author Charlie
 */
public class Utils {
    
    private static Utils utils;
    
    private Random r = new Random();
    
    private Utils(){}
    
    public int getRandInt(int lowBound, int upBound){
        return r.nextInt(upBound-lowBound) + lowBound;
    }
    
    public static Utils getUtils(){
        if(utils==null) utils = new Utils();
        return utils;
    }
}
