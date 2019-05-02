import java.util.ArrayList;
import java.util.Random;

public class Planet {
    String name;
    private boolean partFound = false;
    private ArrayList<Item> itemsOnPlanet = new ArrayList<>();
    private OutPost outPost = new OutPost();

    public Planet(String name) {
        this.name = name;
        itemsOnPlanet = GameEnvironment.allItems;
        System.out.println("Generating Items For " +  this.name + "...\n" + itemsOnPlanet);
    }

    public void searchPlanet(Crew crew, CrewMember member) {
        // Change when get items working
        //returns item
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        while (partFound == true && randomNumber == 3) {
            randomNumber = rand.nextInt(3);
        }
        System.out.println(member.getName() + " is searching planet " + name + "...");
        System.out.println("Random Num " + randomNumber);
        switch (randomNumber) {
            case 0://Item Found
                Item itemFound = chooseItem();
                System.out.println("Item Found: " + itemFound.getName());
                crew.addOwnedItems(itemFound);
                break;
            case 1://Money Found
                findMoney(crew);
                break;
            case 2://Nothing Found
                System.out.println("Nothing found");
                break;
            case 3://Part Found
                partFound = true; // Could use observer
                crew.addPiecesFound();
                System.out.println("Transporter Found");
                break;
            default:
                System.out.println("No actions");
                break;
        }
    }

    public Item chooseItem() {
        int itemsAmount = GameEnvironment.allItems.size();
        Random rand = new Random();
        int randomNumber = rand.nextInt(itemsAmount);
        return GameEnvironment.allItems.get(randomNumber);
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