import java.time.LocalDate;
import java.util.ArrayList;

/* Fridge Class
@author Primary Author Saron Yewondwossen
@author Assisted Author Nooria Jan Saidi
Purpose: Represents a user's fridge and stores all food items currently inside it.
The class manages:
- adding new items
- removing items
- tracking quanities and expire dates
- searching for items
- removing expire items
- displaying fridge contents 
*/
public class Fridge 
{
    private String username;
    private int totalSpace;
    private int currentCount;
    private ArrayList<Item> allItems=new ArrayList<>();
    
    /**creates a new Fridge object for a specific user
     * @param userName the given username of fridge owner
     * @param setTotalSpace the maximum number of items the fridge can store
     * 
     * Precondition:
     * setTotalSpace should be greater than 0.
     * 
     * Postcondition:
     * A fridge object is created with an empty/null item list.
    */
    public Fridge(String userName, int setTotalSpace){
        username=userName;
        totalSpace=setTotalSpace;
        currentCount=0;
    }
    
    /** Returns the username associated with this fridge object.
     * 
     * @return the fridge owner's username
     */
    public String getUserName(){
        return username;
    }
    
    
    /**Returns the maximum space a fridge can hold
     * 
     * @return the total number of items the fridge can hold
     */
    public int getTotalSpace(){
        return totalSpace;
    }
    
    /**Calculate the total quanityt of all items currently stored in this fridge object
     * 
     * @return the total number of stored items
     */
    public int getCurrentCount(){
        if (allItems.size()==0){
            return 0;
        }
        for (int i=0;i<allItems.size();i++){
            currentCount+=allItems.get(i).getCount();
            
        }
        return currentCount;
    }
    
    /**Creates and return a formatted String containing all items currently stored in this fridge
     * 
     * @return a formatted list of fridge contents
     */
    public String seeContents(){
        String allContents= "\n\t---------------- Items In Fridge ----------------\n"; 
        if (allItems.size()==0){
            allContents="There are no items currently in your fridge";
        }
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getCount()>0){
                allContents+="\t" +allItems.get(i).toString()+"\n";
            }
        }
        allContents+="\t-------------------------------------------------";
        return allContents;
    } 
    
    /**Searches the fridge object for all items with the specified name
     * 
     * @param itemName the name of the item being searched for
     * @return a formatted String containing matching items
     */
    public String searchForItem(String itemName){
        LocalDate matchNoExpDate=LocalDate.of(1111,11,1);
        String requestedItem="\n\t------------------ Requested Item --------------------\n";
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getName().equalsIgnoreCase(itemName)&& !allItems.get(i).getExpireDate().equals(matchNoExpDate)){
                requestedItem+="\t"+ allItems.get(i).toString()+"\n";
            }
        }
        requestedItem+="\t------------------------------------------------------\n";
        return requestedItem;
    }
    
    /**Adds an Item object to this fridge object
     * 
     * @param newItem the item to add to the fridge
     * 
     * Precondition:
     * The fridge must have enough remaining space.
     *
     * Postcondition:
     * The item is added to the fridge item list.
     */
    public void addItem(Item newItem){
        allItems.add(newItem);
        
    }
    
    /**Reduces the quantity of a matching item in the fridge.
     *
     * @param name the name of the item being removed
     * @param expireDate the expiration date of the item
     *
     * Precondition:
     * A matching item must exist in the fridge.
     *
     * Postcondition:
     * The quantity of the matching item is reduced by 1.
     */
    public void removeUsedItem(String name, LocalDate expDate){
        LocalDate matchNoExpDate=LocalDate.of(1111,11,1);
        String message="";
        for (int i=0; i<allItems.size();i++){
            if (allItems.get(i).getName().equalsIgnoreCase(name)&& allItems.get(i).getExpireDate().equals(expDate)&& !allItems.get(i).getExpireDate().equals(matchNoExpDate)){
                allItems.get(i).reduceCount(1);
            }
        }
    }
    
    /**Returns the ArrayList containing all items from this fridge object.
     *
     * @return the fridge item ArrayList
     */
    public ArrayList<Item> getFridgeArray(){
        return allItems;
    }
    
    /**Removes all expired items from the fridge.
     *
     * Postcondition:
     * All items with expiration dates before the current date are removed from the fridge.
     */
    public void removeExpiredItems(){
        LocalDate today = LocalDate.now();
        LocalDate matchNoExpDate=LocalDate.of(1111,11,1);
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getExpireDate().isBefore(today)&& !allItems.get(i).getExpireDate().equals(matchNoExpDate)){
                allItems.remove(i);
            }
        }
    }
    
    /**Removes all expired items from the fridge.
     *
     * Postcondition:
     * All items with expiration dates before the current date are removed from the fridge.
     * 
     * @return a String with a formatted list of expired items to remove
     */
    public String expiredItemsRemovalMessage(){
        String removalMessage="\n\t------------------- Expired Items -----------------------\n";
        LocalDate today = LocalDate.now();
        LocalDate matchNoExpDate=LocalDate.of(1111,11,1);
        for (int i=0;i<allItems.size();i++){
            if (allItems.get(i).getExpireDate().isBefore(today)&& !allItems.get(i).getExpireDate().equals(matchNoExpDate)){
                removalMessage+= "\tPlease remove " + allItems.get(i).getCount()+" "+ allItems.get(i).getName()+" from your fridge; they have expired\n";
                allItems.remove(i);
            }
        }
        removalMessage+="\t---------------------------------------------------------\n";
        return removalMessage;
    }

}