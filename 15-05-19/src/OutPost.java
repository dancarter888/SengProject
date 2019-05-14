import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OutPost {
    private ArrayList<Item> outPostItems = new ArrayList<>();
    private ArrayList<MedicalItem> allMedicalItems = new ArrayList<>();
    private ArrayList<MedicalItem> allFoodItems = new ArrayList<>();

    OutPost() {
        setAllItems();
    }

    public void setAllItems() {
        // Gets random number and creates items
        for(int i=0; i<4; i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            Item item = GameEnvironment.allItems.get(randomNumber);
            outPostItems.add(item);
//            switch (randomNumber) {
//                case 0:
//                    //Apple apple = new Apple();
//                    Item apple = GameEnvironment.allItems.get(0)
//                    outPostItems.add(apple);
//                    break;
//                case 1:
//                    LesserHealing lesserHealing = new LesserHealing();
//                    outPostItems.add(lesserHealing);
//                    break;
//                case 2:
//                    SpacePlagueCure spacePlagueCure = new SpacePlagueCure();
//                    outPostItems.add(spacePlagueCure);
//                    break;
//                default:
//                    System.out.println("Didn't work");
//                    break;
//            }
        }




//        Item apple = new Apple();
//        Item lesserHealing = new LesserHealing();
//        SpacePlagueCure spacePlagueCure = new SpacePlagueCure();
//        outPostItems.add(apple);
//        outPostItems.add(lesserHealing);
//        outPostItems.add(spacePlagueCure);
    }
    
    public void visitShop(Crew crew) {
    	 Scanner scanner = new Scanner(System.in);
         //OutPost outpost = this.crew.getTheShip().getLocation().getOutPost();
         int actions= 0;
         while (actions != 3) {
             System.out.println("You Are At " + crew.getTheShip().getLocation().getName() + "'s OutPost.");
             System.out.println("You Have $" + crew.getMoney() + ".");
             System.out.println("Possible actions:\n1. View Items For Sale\n2. View My Items\n3. Leave OutPost");
             actions = scanner.nextInt();
             switch (actions) {
                 case 1:
                     //View Items For Sale
                     this.viewItemsForSale();
                     //Asks if you want to buy
                     this.askBuyItems(crew);
                     break;
                 case 2:
                     // View My Items
                     this.viewMyItems(crew);
                     break;
                 case 3:
                     break;
                 default:
                     System.out.println("No actions");
                     break;

             }
         }
    }
    
    public void askBuyItems(Crew crew) {
    	Scanner scanner = new Scanner(System.in);

        //OutPost outpost = crew.getTheShip().getLocation().getOutPost();
        int action = 0;
        while (action != 3) {
            System.out.println("What Would You Like To Do?\n1. Buy\n2. Get Description\n3. Leave");
            action = scanner.nextInt();
            switch (action) {
                case 1: //Buy
                    //this.buyItems(crew);
                    break;
                case 2: //Get Description
                    //getDescription();
                    break;
                case 3: //Leave
                    System.out.println("No Items Bought\n");
                    break;
                default:
                    System.out.println("No actions");
                    break;

            }
        }
    }
    
    public String viewMyItems(Crew crew) {
    	//ArrayList<Item> allItems = this.crew.getTheShip().getLocation().getOutPost().getOutPostItems();
        //System.out.println("My Items:");
        ArrayList<String> items = new ArrayList<>();
        
        for (Item item : this.outPostItems) {
            int frequency = 0;
            for (Item myItem : crew.getOwnedItems()) {
                if (item.getName() == myItem.getName()) {
                    frequency ++;
                }
            }
            items.add((String) (frequency + "x " + item));
        }
        
        String h = "<html>";
		for(String s : items) {
			h += s + "<br><br>";
		}
		h += "</html>";

        //System.out.println("\n");
        return h;
    }
    
    public void getDescription(int itemNum) {
    	//Scanner scanner = new Scanner(System.in);
        //int action;
        //System.out.println("Get Description Of Which Item?");
        //this.viewItemsForSale();
        //int action = scanner.nextInt();
        Item itemToDescribe = this.outPostItems.get(itemNum);
        System.out.println(itemToDescribe.getDescription() + "\n");
    }

    public void buyItems(Crew crew, int itemNum, JFrame frame) {
        //Scanner scanner = new Scanner(System.in);
        //int action;
        //ArrayList<Item> itemsForSale = this.getOutPostItems();
        //System.out.println("Which Item Would You Like?");
        //viewItemsForSale();
        //action = scanner.nextInt();
        Item itemBought = this.outPostItems.get(itemNum);
        if (crew.getMoney() > itemBought.getCost() || crew.getMoney() == itemBought.getCost()) { //If they can afford item
            crew.removeMoney(itemBought.getCost());
            crew.addOwnedItems(itemBought);
            JOptionPane.showMessageDialog(frame, "Item Purchased: " + itemBought.getName() + "\nYou have $" + crew.getMoney() + " remaining.");
            
        } else { //Can't afford item
            JOptionPane.showMessageDialog(frame, "Not enough money! This item costs $" + itemBought.getCost() + ", you only have $" + crew.getMoney());
            
        }
    }

    public void viewItemsForSale() {
        //ArrayList<Item> itemsForSale = this.crew.getTheShip().getLocation().getOutPost().getOutPostItems(); //change this to be randomized
        System.out.println("Items For Sale:");
        int i = 0;
        for (Item item : this.outPostItems) {
            i ++;
            System.out.println(i + ". " + item);
        }
        System.out.println("\n");
    }

    public ArrayList<Item> getOutPostItems() {
        return outPostItems;
    }
}
