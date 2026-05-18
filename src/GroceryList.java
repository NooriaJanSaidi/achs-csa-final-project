import java.time.LocalDate;
import java.util.ArrayList;
public class GroceryList 
{
    //contains a list of Items object, derived from the given Firdge array in constructor
    private ArrayList<Item> groceryList=new ArrayList<>();

    // creates a new Grocery Object
    //the groceryList array is filled with a given's Fridge obj's arrayList
    public GroceryList(ArrayList<Item> fridgeArr){
        for (int i=0;i<fridgeArr.size();i++){
            groceryList.add(fridgeArr.get(i));
        }
    }
    
    //Returns a formatted list of Items that have count 0, across all expire dates
    public String generateGroceryList(Fridge fridge, ArrayList<Item> fridgeArr){
        String GroceryMessage= "";
        fridge.removeExpiredItems();
        
        for (int i=0;i<groceryList.size()-1;i++){
            int count=groceryList.get(i).getCount();
            for (int k=i+1;k<groceryList.size();k++){
                if (groceryList.get(i).getName().equalsIgnoreCase(groceryList.get(k).getName())){
                    count+=groceryList.get(k).getCount();
                    groceryList.remove(k);
                }
            }
            if (count==0){
                GroceryMessage+= groceryList.get(i).getName()+" has run out; buy more\n";
            }
            
        }
        GroceryMessage+="\n You can buy a maximum of "+ (fridge.getTotalSpace()-fridge.getCurrentCount())+" items";
        return GroceryMessage;
    }
    
    //adds given Item object to Fridge obj's arrayList
    public void ItemsChosenInGeneratedGroceryList(Fridge fridge, Item item){
        fridge.addItem(item);
    }
    
}