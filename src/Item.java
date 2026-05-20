import java.time.LocalDate;

/* Item Class
@author Primary Author Saron Yewondwossen
@author Assisted Author Nooria Jan Saidi
Purpose: Represents a food item, grouped by expiration date, stored inside a fridge
Each items contains:
- a name
- a quantity(count)
- an expiration date
The class manages:
- returning item names, counts, and expireDates
- formating item when called upon
*/
public class Item {
    
    private int count;
    private LocalDate expireDate;
    private String name;
    
    /**Creates a new Item object with a name, quantity,
     * and expiration date.
     *
     * @param itemName the item name
     * @param currentCountInFridge the quantity of the item
     * @param expYear the expiration year of the item
     * @param expMonth the expiration month of the item
     * @param expDay the expiration day of the item.
     * 
     * Precondition:
     * count should be greater than or equal to 0.
     * expYear, expMonth, expDay must all be valid date values.
     *
     * Postcondition:
     * A new Item object is initialized.
     */
    public Item(String itemName,int currentCountInFridge, int expYear, int expMonth, int expDay){
        count=currentCountInFridge;
        name=itemName;
        expireDate=LocalDate.of(expYear,expMonth,expDay);
    }
    
    /**Creates a new Item object with only a name, useful for items that should be in fridge but aren't
     *
     * @param itemName the item name
     *
     * Postcondition:
     * A new Item object is initialized.
     */
    public Item(String itemName){
        name=itemName;
        count=0;
        expireDate=LocalDate.of(1111,11,1);
    }
    
    /**Returns the quantity of the item.
     *
     * @return the item count
     */    
    public int getCount(){
        return count;
    }
    
    /**Returns the name of the item.
     *
     * @return the item name
     */
    public String getName(){
        return name;
    }
    
    /**Returns the expiration date of the item.
     *
     * @return the item's expiration date
     */
    public LocalDate getExpireDate(){
        return expireDate;
    }
    
    /**Increases the quantity of the item.
     *
     * @param additionalCount the amount to add to the count
     *
     * Postcondition:
     * The item count is increased by additionalCount.
     */
    public void addCount(int additionalCount){
        count+=additionalCount;
    }
    
    /**Decreases the quantity of the item.
     *
     * @param removalCount the amount to remove from the count
     *
     * Precondition:
     * removalCount should be less than or equal to the current count.
     *
     * Postcondition:
     * The item count is decreased by removalCount.
     */
    public void reduceCount(int removalCount){
        count-=removalCount;
    }
    
    /**Returns a formatted String representation of the item.
     *
     * @return a formatted item description of each Item, grouped by expiration date
     */
    public String toString(){
        return name + ": \t Count: "+ count + "\t Expire Date: " + expireDate;
    }
}