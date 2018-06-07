/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbergame;

import java.util.Timer;

/**
 *
 * @author Yongcong
 */
public class GameTimer {
    public static final int HEIGHT = 100;
    
    //seconds
    private int totalTime=30;
    public GameTimer(){
    }
    
    public GameTimer(int leftOverTime){
        this.totalTime += leftOverTime;
    }
    
    public int getSecond(){
        return totalTime%60;
    }
    public int getMinute(){
        return (totalTime/60)%60;
    }
    public int getHour(){
        return (totalTime/3600)%60;
    }
    public void removeTime(){
        totalTime--;
    }
    public String timeString(){
        return timeFormat(getHour())+":"+timeFormat(getMinute())+":"+timeFormat(getSecond());
    }
    
    public String timeFormat(int x){
        if(x<10){
            return "0"+x;
        }
        else return ""+x;
    }
    
    public boolean lost(){
        return (totalTime==0);
    }
    
    public int getTime(){
        return totalTime;
    }
}
