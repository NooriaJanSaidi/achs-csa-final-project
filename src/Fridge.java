import java.time.LocalDate;
import java.util.ArrayList;
public class Fridge 
{
    //name of the User who's Fridge this belongs to in application
    private String username;
    
    //The total count of items that a Fridge object can contain
    private int totalSpace;
    
    //The current count of all Items in a Fridge object
    private int currentCount;
    
    //Where all Items are stored
    private ArrayList<Item> allItems=new ArrayList<>();
    
    //creates a new Fridge object with no Items within it
    //takes user input of max number of items that can fit in a fridge and sets it equal to totalSpace
    public Fridge(String userName, int setTotalSpace){
        username=userName;
        totalSpace=setTotalSpace;
        currentCount=0;
    }
    //return the name of the User who's Fridge this belongs to in application's
    public String getUserName(){
        return username;
    }
    
    
    //returns the max space a Fridge object can contain
    public int getTotalSpace(){
        return totalSpace;
    }
    
    //returns the current number of items in Fridge object
    public int getCurrentCount(){
        if (allItems.size()==0){
            return 0;
        }
        for (int i=0;i<allItems.size();i++){
            currentCount+=allItems.get(i).getCount();
            
        }
        return currentCount;
    }
    
    //returns a list of all items with an existing count in Fridge object
    public String seeContents(){
        String allContents= ("\t---------------- Items In Fridge----------------\n"); 
        if (allItems.size()==0){
            allContents="There are no items currently in your fridge";
        }
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getCount()>0){
                allContents+="\t" +allItems.get(i).toString()+"\n";
            }
        }
        allContents+="\t-------------------------------";
        return allContents;
    } 
    
    //returns name, count, and exp data of all items with the same name as parameter 
    public String searchForItem(String itemName){
        String requestedItem="";
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getName().equalsIgnoreCase(itemName)){
                requestedItem+=allItems.get(i).toString();
            }
        }
        return requestedItem;
    }
    
    //adds Item object to Fridge Object arrayList
    public void addItem(Item newItem){
            allItems.add(newItem);
        
    }
    
    //removes Item object from Fridge Object arrayList
    public void removeItem(Item newItem){
            allItems.remove(newItem);
    }
    
    //returns arrayList of Items in Fridge object
    public ArrayList<Item> getFridgeArray(){
        return allItems;
    }
    
    //removes Items with expireDates before the current day when method is called
    //it will not remove items that have no expire date set
    //more usefull when generating grocery list
    public void removeExpiredItems(){
        LocalDate today = LocalDate.now();
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getExpireDate().isBefore(today)&& !allItems.get(i).getExpireDate().equals(null)){
                allItems.remove(i);
            }
        }
    }
    
    public String expiredItemsRemovalMessage(){
        String removalMessage="";
        LocalDate today = LocalDate.now();
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getExpireDate().isBefore(today)&& !allItems.get(i).getExpireDate().equals(null)){
                allItems.remove(i);
                removalMessage+= "Please remove " + allItems.get(i).getCount()+" "+ allItems.get(i).getName()+" from your fridge; they have expired\n";
            }
        }
    return removalMessage;
    }

}