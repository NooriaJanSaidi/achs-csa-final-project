import java.time.LocalDate;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        Fridge[] users=new Fridge[3];
        int userIndex=0;
        while (userIndex<3){
            System.out.println("----------------------------- Welcome User -----------------------------\n");
            
            System.out.print("Enter name: ");
            String userName=scan.nextLine();
            
            System.out.print("Enter maximum number of items you want in your fridge: ");
            int userTotalCount= scan.nextInt();
            users[userIndex]= new Fridge(userName, userTotalCount);
            
            System.out.println("\nInput the items currently in your fridge----");
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
                System.out.print("\nAre there any more?: ");
                moreItems2=scan.nextLine();
            }
            System.out.println("\n"+ users[userIndex].getUserName()+ "'s Fridge is now set!");
                
            boolean openActions=true;
            System.out.println("To see actions --> a \nTo close application --> c");
            String userAction1Selection=scan.nextLine();
            if (userAction1Selection.equalsIgnoreCase("c")){
                openActions=false;
                System.out.println("Application has closed!");
            }
            
            while(openActions){
                System.out.println("\n\tTo see expired items --> 1\n\tTo find an Item in your fridge --> 2\n\tTo see all contents of your fridge --> 3\n\tTo remove a finished item from your fridge --> 4 \n\tTo generate a grocery list --> 5");
                System.out.print("\n\tEnter number: ");
                int userAction2Selection=scan.nextInt();
                if (userAction2Selection==1){
                    System.out.print(users[userIndex].expiredItemsRemovalMessage());
                }
                else if(userAction2Selection==2){
                    System.out.println("What Item would you like to find");
                    String itemSearch=scan.nextLine();
                    scan.nextLine();
                    users[userIndex].searchForItem(itemSearch);
                }
                else if (userAction2Selection==3){
                    users[userIndex].seeContents();
                }
                else if (userAction2Selection==4){
                    String moreRemovalItems="yes";
                    while (moreRemovalItems.equalsIgnoreCase("yes")){
                        System.out.print("\n\tEnter item name: ");
                        String itemName2=scan.nextLine();
                        System.out.print("\tEnter this item's expire YEAR(4 digit):");
                        int itemExpYear2=scan.nextInt();
                        System.out.print("\tEnter this item's expire MONTH(1-12):");
                        int itemExpMonth2=scan.nextInt();
                        System.out.print("\tEnter this item's expire DAY(1-31):");
                        int itemExpDay2=scan.nextInt();
                        users[userIndex].removeUsedItem(itemName2,LocalDate.of(itemExpYear2, itemExpMonth2, itemExpDay2) );
                        System.out.print("More items to remove?: ");
                        moreRemovalItems=scan.nextLine();
                    }
                }
                else if (userAction2Selection==5){
                    GroceryList gList= new GroceryList(users[userIndex].getFridgeArray());
                    gList.generateGroceryList(users[userIndex],users[userIndex].getFridgeArray());
                }
                
                System.out.println("\nWould you like to select another action?: ");
                String userAction3Selection=scan.nextLine();
                if (userAction3Selection.equalsIgnoreCase("yes")){
                    openActions=true;
                }
                else if (userAction3Selection.equalsIgnoreCase("no")){
                    openActions=false;
                    System.out.println("Application has closed!");
                    
                }
            }
        userIndex++;
        }
        System.out.println("User count has exceeded maximum system use amount\nApplication will close...");
    }
}