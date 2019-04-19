public class FoodItem implements Item {

    int cost;
    String name;
    int hungerRestored;

    public FoodItem(int cost, String name, int hungerRestored) {
        this.cost = cost;
        this.name = name;
        this.hungerRestored = hungerRestored;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return String.format("Restores %d hunger", hungerRestored);
    }

    @Override
    public void useItem(CrewMember member) {
        member.increaseHunger(hungerRestored);
        System.out.println(String.format("%s item used...", name));
    }

    public String toString() {
        String returnString = String.format("Item: %s, Cost: %d", this.name, this.cost);
        return returnString;
    }
}
