import java.util.Scanner;

public class CrewMember {

    private String name;
    private int hunger = 100;
    private int tiredness = 0; // maybe change tiredness to energy cause makes a bit more sense
    private boolean hasPlague = false;
    private int actionsRemaining = 2;
    private int health;
    private String searchingSkill;

    public CrewMember(int health, String searchingSkill) {
        this.health = health;
        this.searchingSkill = searchingSkill;
        this.setName();
    }

//    public void getStatus(){
//        System.out.println("Hunger is" + this.getHunger());
//        System.out.println("Tiredness is" + this.getTiredness());
//        System.out.println("Health is" + this.getHealth());
//    }

    public void sleep() {
        this.tiredness += 50;
        if(this.tiredness > 100) {
            this.tiredness = 100;
        }
        System.out.println(String.format("%s is sleeping...", this.name));
        System.out.println(String.format("They now have %d tiredness\n", this.tiredness));

        this.performAction();
    }

    public void performAction() {
        this.actionsRemaining --;
        System.out.println(String.format("%s now has %d actions remaining", this.name, this.actionsRemaining));
    }

    public String getName() {
        return name;
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter crew member name:");
        this.name = scanner.nextLine();
    }

    public int getHunger() {
        return hunger;
    }

    public void increaseHunger(int num) {
        if((this.hunger + num) >= 100) {
            this.hunger = 100;
        } else {
            this.hunger += num;
        }

        System.out.println(String.format("%s is eating...", this.name));
        System.out.println(String.format("They now have %d hunger\n", this.hunger));
    }

    public void increaseHealth(int num) {
        if((this.health + num) >= 100) {
            this.health = 100;
        } else {
            this.health += num;
        }
        System.out.println(String.format("%s is healing...", this.name));
        System.out.println(String.format("They now have %d health\n", this.health));

    }

    public int getTiredness() {
        return tiredness;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getSearchingSkill() {
        return searchingSkill;
    }

    public void setSearchingSkill(String searchingSkill) {
        this.searchingSkill = searchingSkill;
    }

    public boolean getHasPlague() {
        return hasPlague;
    }

    public void setHasPlague(boolean hasPlague) {
        this.hasPlague = hasPlague;
    }

    public int getActionsRemaining() {
        return actionsRemaining;
    }

    public void setActionsRemaining(int actionsRemaining) {
        this.actionsRemaining = actionsRemaining;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Hunger: %d, Tiredness: %d, Health: %d", this.name, this.hunger,
                            this.tiredness, this.health);
    }
}