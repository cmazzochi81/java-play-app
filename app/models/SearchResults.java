package models;
import java.util.List;

public class SearchResults {
    
    public List<AuctionItem> items;
    public Long count;
    
    public SearchResults(List<AuctionItem> items, Long count){
        this.items = items;
        this.count = count;
    }
    
}
