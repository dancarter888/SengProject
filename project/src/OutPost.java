import java.util.ArrayList;
import java.util.Random;

public class OutPost {
    private ArrayList<Item> allItems = new ArrayList<>();
    private ArrayList<MedicalItem> allMedicalItems = new ArrayList<>();
    private ArrayList<MedicalItem> allFoodItems = new ArrayList<>();

    OutPost() {
        setAllItems();
    }

    public void setAllItems() {
        // Gets random number and creates items
        int itemsSet = 0;
        for(int i=0; i<4; i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            switch (randomNumber) {
                case 0:
                    Apple apple = new Apple();
                    allItems.add(apple);
                    break;
                case 1:
                    LesserHealing lesserHealing = new LesserHealing();
                    allItems.add(lesserHealing);
                    break;
                case 2:
                    SpacePlagueCure spacePlagueCure = new SpacePlagueCure();
                    allItems.add(spacePlagueCure);
                    break;
                default:
                    System.out.println("Didn't work");
                    break;
            }
        }




//        Item apple = new Apple();
//        Item lesserHealing = new LesserHealing();
//        SpacePlagueCure spacePlagueCure = new SpacePlagueCure();
//        allItems.add(apple);
//        allItems.add(lesserHealing);
//        allItems.add(spacePlagueCure);
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }
}
