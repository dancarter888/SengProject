import java.util.ArrayList;

public class OutPost {
    private ArrayList<Item> allItems = new ArrayList<>();
    private ArrayList<MedicalItem> allMedicalItems = new ArrayList<>();
    private ArrayList<MedicalItem> allFoodItems = new ArrayList<>();

    OutPost() {
        setAllItems();
    }

    public void setAllItems() {
        Item apple = new Apple();
        Item lesserHealing = new LesserHealing();
        SpacePlagueCure spacePlagueCure = new SpacePlagueCure();
        allItems.add(apple);
        allItems.add(lesserHealing);
        allItems.add(spacePlagueCure);
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }
}
