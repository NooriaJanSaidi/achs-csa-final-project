import java.time.LocalDate;
import java.util.ArrayList;

/* GroceryList Class
@author Primary Author Saron Yewondwossen
@author Assisted Author Nooria Jan Saidi
Purpose: Represents a govery list containing items that need to be bought(items that have reached count 0)
Items are added when:
- an item(with potentially more than 1 quantity) expires and there are no more of that item
- when a item runs out
- user manually adds a item that should be in fridge, but currently is not 
*/
public class GroceryList 
{
    private ArrayList<Item> groceryList=new ArrayList<>();
    
    /**
     * Creates a new GroceryList object with an empty list.
     *
     * Postcondition:
     * The grocery list contains no items.
     */
    public GroceryList(){
        
    }
    
    /**Generates grocery list entries from items currently stored in the fridge.
     *
     * @param fridge the Fridge object being checked
     * @fridgeArr the Array of Item objects that belong to fridge
     *
     * Postcondition:
     * Appropriate items from the fridge are added to the grocery list.
     */
    public String generateGroceryList(Fridge fridge, ArrayList<Item> fridgeArr){
        String groceryMessage= "\t----------------- Grocery List ------------------\n";
        fridge.removeExpiredItems();
        
        for (int i=0;i<fridgeArr.size();i++){
            groceryList.add(fridgeArr.get(i));
        }
        
        for (int i=0;i<groceryList.size();i++){
            int count=groceryList.get(i).getCount();
            for (int k=i+1;k<groceryList.size();k++){
                if (groceryList.get(i).getName().equalsIgnoreCase(groceryList.get(k).getName())){
                    count+=groceryList.get(k).getCount();
                    groceryList.remove(k);
                }
            }
            if (count==0){
                groceryMessage+= "\t- "+ groceryList.get(i).getName()+" has run out; buy more\n";
            }
            
        }
        if (groceryMessage.equals("\t----------------- Grocery List ------------------\n")){
            groceryMessage+="\tThere are no items that have run out\n\t-------------------------------------------------";
            return groceryMessage;
        }
        groceryMessage+="\n\tYou can buy a maximum of "+ (fridge.getTotalSpace()-fridge.getCurrentCount())+" more items\n";
        groceryMessage+= "\t--------------------------------------------------\n";
        
        return groceryMessage;
    }
    
}