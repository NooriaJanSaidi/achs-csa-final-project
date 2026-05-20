import java.time.LocalDate;
import java.util.Scanner;

/* UserInterface Class
@author Primary Author Nooria Jan Saidi
@author Assisted Author Saron Yewondwossen
Overall Program Goal:
- help users organize fridge contents
- reduce food waste
- monitor expiration dates through a simple inventory management system.
Class Interactions:
- Creates Fridge objects to store and manage food items
- Creates Item objects based on user input
- Uses GroceryList objects to track items that need to be purchased again
- Uses Scanner objects to receive user input
*/
public class UserInterface
{
    
    /**
     * Runs the fridge inventory management application.
     *
     * The main method recieves user input to:
     * - create program objects
     * - display fridge contents
     * - allow item additions/removals
     * - track expiration dates
     * - update the grocery list
     */
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        Fridge[] users=new Fridge[3];
        int userIndex=0;
        while (userIndex<3){
            System.out.println("--------------------------------- Welcome User ---------------------------------\n");
            
            System.out.print("Enter user name: ");
            String userName=scan.nextLine();
            
            System.out.print("Enter maximum number of items you want in your fridge: ");
            int userTotalCount= scan.nextInt();
            users[userIndex]= new Fridge(userName, userTotalCount);
            
            System.out.println("\nInput the items currently in your fridge----------------------------------");
            System.out.println("* if you have items of varying expire dates, enter them as seperate items *");
            String moreItems="yes";
            scan.nextLine();
            while (moreItems.equalsIgnoreCase("yes")){
                System.out.print("\n\tEnter item name: ");
                String itemName=scan.nextLine();
                System.out.print("\tEnter this item's expire YEAR(4 digit):");
                int itemExpYear=scan.nextInt();
                System.out.print("\tEnter this item's expire MONTH(1-12):");
                int itemExpMonth=scan.nextInt();
                System.out.print("\tEnter this item's expire DAY(1-31):");
                int itemExpDay=scan.nextInt();
                System.out.print("\tEnter the number of "+ itemName+ " with this expire date: ");
                int itemCount=scan.nextInt();
                users[userIndex].addItem(new Item(itemName, itemCount, itemExpYear, itemExpMonth, itemExpDay));
                scan.nextLine();
                System.out.print("\nDo you have more items in your fridge?: ");
                moreItems=scan.nextLine();
            }
            System.out.print("Do you have items not currently in your fridge that you would like to have? ");
            String moreItems2=scan.nextLine();
            while (moreItems2.equalsIgnoreCase("yes")){
                System.out.print("\n\tEnter item name: ");
                String itemName2=scan.nextLine();
                users[userIndex].addItem(new Item(itemName2));
                System.out.print("\nAre there any more?: ");
                moreItems2=scan.nextLine();
            }
            System.out.println("\n"+ users[userIndex].getUserName()+ "'s Fridge is now set!\n");
                
            boolean openActions=true;
            System.out.print("\tTo see actions --> a \n\tTo close application --> c\n\tEnter action selection: ");
            String userAction1Selection=scan.nextLine();
            if (userAction1Selection.equalsIgnoreCase("c")){
                openActions=false;
                System.out.println("\nApplication has closed!\n\n");
            }
            
            while(openActions){
                System.out.println("\n\tTo see expired items --> 1\n\tTo find an Item in your fridge --> 2\n\tTo see all contents of your fridge --> 3\n\tTo remove a finished item from your fridge --> 4 \n\tTo generate a grocery list --> 5 \n\tTo add bought item(s) from grocery shopping --> 6");
                System.out.print("\n\tEnter number: ");
                int userAction2Selection=scan.nextInt();
                if (userAction2Selection==1){
                    System.out.print(users[userIndex].expiredItemsRemovalMessage());
                    scan.nextLine();
                }
                else if(userAction2Selection==2){
                    scan.nextLine();
                    System.out.print("\n\tWhat Item would you like to find?: ");
                    String itemSearch=scan.nextLine();
                    System.out.print(users[userIndex].searchForItem(itemSearch));
                }
                else if (userAction2Selection==3){
                    System.out.println(users[userIndex].seeContents());
                    scan.nextLine();
                }
                else if (userAction2Selection==4){
                    scan.nextLine();
                    String moreItemsToRemove="yes";
                    while (moreItemsToRemove.equalsIgnoreCase("yes")){
                        System.out.print("\n\tEnter item name: ");
                        String itemRemoveName=scan.nextLine();
                        System.out.print("\tEnter this item's expire YEAR(4 digit):");
                        int itemRemoveExpYear=scan.nextInt();
                        System.out.print("\tEnter this item's expire MONTH(1-12):");
                        int itemRemoveExpMonth=scan.nextInt();
                        System.out.print("\tEnter this item's expire DAY(1-31):");
                        int itemRemoveExpDay=scan.nextInt();
                        users[userIndex].removeUsedItem(itemRemoveName,LocalDate.of(itemRemoveExpYear, itemRemoveExpMonth, itemRemoveExpDay) );
                        System.out.println("\nOne count of this item has been removed!");
                        System.out.print("More items to remove?: ");
                        scan.nextLine();
                        moreItemsToRemove=scan.nextLine();
                    }
                }
                else if (userAction2Selection==5){
                    GroceryList gList= new GroceryList();
                    System.out.print("\n"+gList.generateGroceryList(users[userIndex],users[userIndex].getFridgeArray()));
                    scan.nextLine();
                }
                else if (userAction2Selection==6){
                    System.out.println("\n* if you have items of varying expire dates, enter them as seperate items *");
                    scan.nextLine();
                    String moreItemsToAdd="yes";
                    while (moreItemsToAdd.equalsIgnoreCase("yes")){
                        System.out.print("\n\tEnter item name: ");
                        String itemAddName=scan.nextLine();
                        System.out.print("\tEnter this item's expire YEAR(4 digit):");
                        int itemAddExpYear=scan.nextInt();
                        System.out.print("\tEnter this item's expire MONTH(1-12):");
                        int itemAddExpMonth=scan.nextInt();
                        System.out.print("\tEnter this item's expire DAY(1-31):");
                        int itemAddExpDay=scan.nextInt();
                        System.out.print("\tEnter the number of "+ itemAddName+ " did you buy with this same expire date: ");
                        int itemAddCount=scan.nextInt();
                        users[userIndex].addItem(new Item(itemAddName,itemAddCount, itemAddExpYear, itemAddExpMonth, itemAddExpDay));
                        System.out.println("\nItem has been added!");
                        System.out.print("More items to add?: ");
                        scan.nextLine();
                        moreItemsToAdd=scan.nextLine();
                    }
                }
                
                System.out.print("\nWould you like to select another action?: ");
                String userAction3Selection=scan.nextLine();
                if (userAction3Selection.equalsIgnoreCase("yes")){
                    openActions=true;
                }
                else if (userAction3Selection.equalsIgnoreCase("no")){
                    openActions=false;
                    System.out.println("Application has closed!\n\n");
                    
                }
            }
        userIndex++;
        }
        System.out.println("User count has exceeded maximum system use amount\nApplication will close...");
    }
}