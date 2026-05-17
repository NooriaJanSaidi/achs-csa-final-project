//allows dates to be set as an object
import java.time.LocalDate;
public class Item {
    
    //the number of items under the same name and expire date
    private int count;
    
    //the expire date of that item
    private LocalDate expireDate;
    
    //the name of the item
    private String name;
    
    //creates a new Item object under a certain name, expire date, and count
    public Item(String itemName,int currentCountInFridge, int expYear, int expMonth, int expDay){
        count=currentCountInFridge;
        name=itemName;
        expireDate=LocalDate.of(expYear,expMonth,expDay);
    }
    //creates a new Item under only a certain name
    //useful for when user needs an item to be in fridge, but none is currently within in
    public Item(String itemName){
        name=itemName;
        count=0;
        expireDate=null;
    }
    //returns the number of items under the same name and expire date
    public int getCount(){
        return count;
    }
    
    //returns the name of that item
    public String getName(){
        return name;
    }
    //returns the expire date of that item in year, month, day format
    public LocalDate getExpireDate(){
        return expireDate;
    }
    
    //set the current number of items under the same name and expire date to a certain count
    public void setCount(int newCount){
        count=newCount;
    }
    
    //increased the count of the item by a given amount
    public void addCount(int additionalCount){
        count+=additionalCount;
    }
    
    //reduces the count of item by a given amount
    public void reduceCount(int removalCount){
        count-=removalCount;
    }
    
    //when an Item is printed via a S.O.P call, this method formats it in a specific way
    public String toString(){
        return name + ": \t Count: "+ count + "\t Expire Date: " + expireDate;
    }
}