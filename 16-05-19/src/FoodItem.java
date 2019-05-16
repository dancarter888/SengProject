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
    public String useItem(CrewMember member) {
        String text = member.increaseHunger(hungerRestored);
        text = String.format("%s item used...\n", name) + text;
        return text;
    }

    public String toString() {
        String returnString = String.format("Item: %s, Cost: %d", this.name, this.cost);
        return returnString;
    }
}
