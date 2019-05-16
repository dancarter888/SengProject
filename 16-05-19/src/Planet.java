import java.util.ArrayList;
import java.util.Random;

public class Planet {

    String name;
    private boolean partFound = false;
    private ArrayList<Item> itemsOnPlanet = new ArrayList<>();
    private ArrayList<Item> allItems;
    private OutPost outPost = new OutPost();

    public Planet(String name) {
        this.name = name;
        this.allItems = GameEnvironment.allItems;
        generateItems();
    }

    public void generateItems() { // This needs to randomise
        System.out.println("Generating Items For " +  this.name + "...");
        for (Item item : this.allItems) {
            itemsOnPlanet.add(item);
            System.out.println(item + "\n");
        }
    }

    public ArrayList<String> searchPlanet(Crew crew, CrewMember member) {
        // Change when get items working
        //returns item
    	ArrayList<String> strings = new ArrayList<>();
        Random rand = new Random();
        int randomNumber = rand.nextInt(6);
        while (partFound == true && randomNumber == 4) {
            randomNumber = rand.nextInt(5);
        }
        strings.add(member.getName() + " is searching planet " + name + "...");
        switch (randomNumber) {
            case 0:
            case 1:
            case 2://Item Found
                strings.add("Item Found: " + allItems.get(randomNumber).getName());
                crew.addOwnedItems(allItems.get(randomNumber));
                break;
            case 3://Money Found
                strings.add(findMoney(crew));
                break;
            case 4://Part Found
                partFound = true; // Could use observer
                crew.addPiecesFound();
                strings.add("Transporter Found");
                break;
            case 5://Nothing Found
                strings.add("Nothing found");
                break;
            default:
                System.out.println("No actions");
                break;
        }
        
        return strings;
    }

    public String findMoney(Crew crew) {
        Random rand = new Random();
        String found = "";
        int randomMoney = rand.nextInt(3);
        switch (randomMoney) {
            case 0:
                found = addMoney(crew, 25);
                break;
            case 1:
            	found = addMoney(crew, 50);
                break;
            case 2:
            	found = addMoney(crew, 75);
                break;
            case 3:
            	found = addMoney(crew, 100);
                break;
        }
        
        return found;
    }

    public String addMoney(Crew crew, int amount) {
        crew.addMoney(amount);
        return("$" + amount + " Found! You now have $" + crew.getMoney());
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