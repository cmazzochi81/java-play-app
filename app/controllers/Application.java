package controllers;
//import static play.modules.pdf.PDF.*;
import com.google.gson.JsonObject;
import play.*;
import play.mvc.*;
import java.util.*;
import java.util.List;
import models.*;
import play.data.validation.*;
import java.util.Date;

public class Application extends Controller {

    public static void index() {
        List<AuctionItem> mostPopular = AuctionItem.getMostPopular(5);
        List<AuctionItem> endingSoon =  AuctionItem.getEndingSoon(5);
        render(mostPopular, endingSoon);
    }

    public static void createAuctionItem(){
        if(session.get("user") == null){
            Authenticate.login();
        }
    	render();
    }

    public static void doCreateItem(@Valid AuctionItem item){
        if (validation.hasErrors()){
        params.flash();
        validation.keep();
        createAuctionItem();    
        }
    	item.createdBy = Authenticate.getLoggedInUser();
    	item.save();
    	show(item.id);
    }

    public static void show(Long id){
    	AuctionItem item = AuctionItem.findById(id);
    	item.viewCount++;
    	item.save();
    	render(item);
    }

    public static void showImage(Long id){
        AuctionItem item = AuctionItem.findById(id);
        renderBinary(item.photo.get());
    }

    public static void search(String search, Integer page){
        validation.required(search).message("You must enter something to search for.");
        if (validation.hasErrors()){
            render();
        }
        if (page == null) page = 1;
        SearchResults results = AuctionItem.search(search, page);
        render(results, page, search);
    }
}