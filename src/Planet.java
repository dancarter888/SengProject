import java.util.ArrayList;
import java.util.Random;

public class Planet {

    String name;
    private boolean partFound = false;
    private ArrayList<Item> itemsOnPlanet = new ArrayList<>();
    private ArrayList<Item> allItems = new ArrayList<>();
    private OutPost outPost = new OutPost();

    public Planet(String name) {
        this.name = name;
        this.allItems = outPost.getAllItems();
        generateItems();
    }

    public void generateItems() { // This needs to randomise
        System.out.println("Generating Items...");
        for (Item item : this.allItems) {
            itemsOnPlanet.add(item);
            System.out.println(item);
        }
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

    public boolean getPartFound() {
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

    public OutPost getOutPost() {
        return this.outPost;
    }

    public String toString() {
        String returnString = this.name;
        return returnString;
    }
}