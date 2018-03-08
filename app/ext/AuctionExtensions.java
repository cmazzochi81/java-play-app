package ext;

import play.templates.JavaExtensions;
import java.util.Date;

public class AuctionExtensions extends JavaExtensions {
    
    public static String dotChop(String s, int maxLength){
        if(s.length() > maxLength){
            return s.substring(0, maxLength) + "...";
        }else{
            return s;
        }
    }
    
    public static String until(Date date) {
        Date now = new Date();
        if (now.after(date)){
            return "";
        }
        long delta = (date.getTime() - now.getTime()) / 1000;
        long seconds = delta % 60;
        long minutes = (delta / 60) % 60;
        
        //Should have two options, ifless than 1 hour, 
        //then show minutes and seconds
        if (delta < 60 * 60){
            return minutes + "minutes " + seconds + "seconds";
        }
        //Otherwise show days hours minutes 
        else {
            long hours = (delta / (60 * 60)) % 24;
            long days = delta / (24 * 60 * 60);
            return days + "d " + hours + "h " + minutes + "m";
        }
    }
}
