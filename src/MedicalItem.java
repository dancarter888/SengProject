public class MedicalItem implements Item {

    int cost;
    String name;
    int healthRestored;

    public MedicalItem(int cost, String name, int healthRestored) {
        this.cost = cost;
        this.name = name;
        this.healthRestored = healthRestored;
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
        String description = "Cures space plague";
        if(name != "Plague Cure") {
            description = String.format("Restores %d health", healthRestored);
        }

        return description;
    }

    @Override
    public void useItem(CrewMember member) {
        if(name == "Plague Cure") {
            member.setHasPlague(false);
        } else {
            member.increaseHealth(healthRestored);
        }

        System.out.println(String.format("%s item used...", name));
    }

    public String toString() {
        String returnString = String.format("Item: %s, Cost: %d", this.name, this.cost);
        return returnString;
    }
}
