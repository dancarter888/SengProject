import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public void buyItems(Crew crew) {
        Scanner scanner = new Scanner(System.in);
        int action;
        //ArrayList<Item> itemsForSale = this.getOutPostItems();
        System.out.println("Which Item Would You Like?");
        viewItemsForSale();
        action = scanner.nextInt();
        Item itemBought = this.outPostItems.get(action - 1);
        if (crew.getMoney() > itemBought.getCost() || crew.getMoney() == itemBought.getCost()) { //If they can afford item
            crew.removeMoney(itemBought.getCost());
            crew.addOwnedItems(itemBought);
            System.out.println("Item Purchased: " + itemBought.getName() + "\nYou have $" + crew.getMoney() + " remaining.");
        } else { //Can't afford item
            System.out.println("Not enough money! This item costs $" + itemBought.getCost() + ", you only have $" + crew.getMoney());
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
