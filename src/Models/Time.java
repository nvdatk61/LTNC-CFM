/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author Datnv
 */
public class Time {
    public static void showTime(Calendar c){
    int Second=c.get(Calendar.SECOND);
    int Minute=c.get(Calendar.MINUTE);
    int Hour=c.get(Calendar.HOUR_OF_DAY);
    int Day=c.get(Calendar.DAY_OF_MONTH);
    int Month=c.get(Calendar.MONTH);
    int Year=c.get(Calendar.YEAR);
    
        System.out.println(""+Hour+":"+Minute+":"+Second);
}
    
}
