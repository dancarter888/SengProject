import java.util.ArrayList;
import java.util.Random;

public class Planet {

    String name;
    private boolean partFound = false;
    private ArrayList<Item> itemsOnPlanet = new ArrayList<>();

    public Planet(String name) {
        this.name = name;
        generateItems();
    }

    public void generateItems() {
        System.out.println("Generating Items...");
        Item apple = new Apple();
        Item lesserHealing = new LesserHealing();
        itemsOnPlanet.add(apple);
        itemsOnPlanet.add(lesserHealing);
        String printString = "";
        for (Item item : itemsOnPlanet) {
            printString += item.toString();
            printString += "\n";
        }
        System.out.println(printString);
    }

    public String searchPlanet(CrewMember member) {
        // Change when ge items working
        //returns item
        Random rand = new Random();
        int number = rand.nextInt(2);
        System.out.println("Searching planet...");
        String itemFound = null;
        if(number == 1) {
            itemFound = "Found item";
        }

        return itemFound;
    }

    public boolean isPartFound() {
        return partFound;
    }

    public void setPartFound(boolean partFound) {
        this.partFound = partFound;
    }

    public ArrayList<Item> getItemsOnPlanet() {
        return itemsOnPlanet;
    }

    public void setItemsOnPlanet(ArrayList<Item> itemsOnPlanet) {
        this.itemsOnPlanet = itemsOnPlanet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String returnString = this.name;
        return returnString;
    }
}