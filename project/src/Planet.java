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
        this.allItems = GameEnvironment.allItems; //outPost.getOutPostItems();
        generateItems();
    }

    public void generateItems() { // This needs to randomise
        System.out.println("Generating Items For " +  this.name + "...");
        for (Item item : this.allItems) {
            itemsOnPlanet.add(item);
            System.out.println(item + "\n");
        }
    }

    public void searchPlanet(Crew crew, CrewMember member) {
        // Change when get items working
        //returns item
        Random rand = new Random();
        int randomNumber = rand.nextInt(6);
        while (partFound == true && randomNumber == 4) {
            randomNumber = rand.nextInt(5);
        }
        System.out.println(member.getName() + " is searching planet " + name + "...");
        System.out.println("Random Num " + randomNumber);
        switch (randomNumber) {
            case 0:
            case 1:
            case 2://Item Found
                System.out.println("Item Found: " + allItems.get(randomNumber).getName());
                crew.addOwnedItems(allItems.get(randomNumber));
                break;
            case 3://Money Found
                findMoney(crew);
                break;
            case 4://Part Found
                partFound = true; // Could use observer
                crew.addPiecesFound();
                System.out.println("Transporter Found");
                break;
            case 5://Nothing Found
                System.out.println("Nothing found");
                break;
            default:
                System.out.println("No actions");
                break;
        }
    }

    public void findMoney(Crew crew) {
        Random rand = new Random();
        int randomMoney = rand.nextInt(3);
        System.out.println("Random Mon " + randomMoney  );
        switch (randomMoney) {
            case 0:
                addMoney(crew, 25);
                break;
            case 1:
                addMoney(crew, 50);
                break;
            case 2:
                addMoney(crew, 75);
                break;
            case 3:
                addMoney(crew, 100);
                break;
        }
    }

    public void addMoney(Crew crew, int amount) {
        crew.addMoney(amount);
        System.out.println("$" + amount + " Found! You now have $" + crew.getMoney());
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