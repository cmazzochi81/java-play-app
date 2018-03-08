package models;
import play.db.jpa.Model;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;
import play.db.jpa.Blob;

@Entity
public class AuctionItem extends Model {
    
    @Required
    public String title;
    public Date startDate;
    public Date endDate;
    @Required
    public Float deliveryCost;
    @Required
    public Float startBid;
    @Required
    public Float buyNowPrice;
    public Boolean buyNowEnabled;
    @Column(length=4096)
    @Required
    @MinSize(20)
    public String description;
    public Integer viewCount = 0;
    @Transient 
    @Required 
    public Integer days;
    public Blob photo;
    @ManyToOne
    public User createdBy;

    public void setDays(Integer days){
        this.days = days;
        if (days != null){
            startDate = new Date();
            endDate = new Date(System.currentTimeMillis() + (days * 24*60*60*1000));
        }
    }
    
   
    public Float getCurrentTopBid(){
       
            return startBid;
        }  

    public static List<AuctionItem> getMostPopular(int maxItems){
        return find("endDate > ? order by viewCount DESC", new Date()).fetch(maxItems);
    }
    
   public static List<AuctionItem> getEndingSoon(int maxItems){
        return find("endDate > ? order by endDate ASC", new Date()).fetch(maxItems);
   }

   public static SearchResults search(String search, Integer page){
        String likeSearch ="%"+search+"%";
        long count =count("title like ? OR description like ? AND " + "endDate > ?",likeSearch,likeSearch, new Date());
        
        List<AuctionItem>items = find("title like ? OR description like ? AND endDate > ? " + "order by endDate ASC", likeSearch, likeSearch, new Date()).fetch(page, 20);
        
        return new SearchResults(items, count);
    }   
}