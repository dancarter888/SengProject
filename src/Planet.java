import java.util.ArrayList;
import java.util.Random;

public class Planet {

    String name = "Earth";
    private boolean partFound = false;
    private ArrayList<String> itemsOnPlanet = new ArrayList();

    public Planet() {
    }

    public void generateItems() {
        System.out.println("Generating Items...");
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

    public ArrayList<String> getItemsOnPlanet() {
        return itemsOnPlanet;
    }

    public void setItemsOnPlanet(ArrayList<String> itemsOnPlanet) {
        this.itemsOnPlanet = itemsOnPlanet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}