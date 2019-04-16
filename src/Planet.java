import java.util.ArrayList;

public class Planet {

    String name = "Earth";
    private boolean partFound = false;
    private ArrayList<String> itemsOnPlanet = new ArrayList();

    public Planet() {
    }

    public void generateItems() {
        System.out.println("Generating Items...");
    }

    public void searchPlanet() {
        //returns item
        System.out.println("Searching planet...");
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