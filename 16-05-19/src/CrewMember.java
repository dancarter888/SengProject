import java.util.ArrayList;

//import java.util.Scanner;

public class CrewMember {

    private String name;
    private int hunger = 100;
    private int tiredness = 50; // maybe change tiredness to energy cause makes a bit more sense
    private boolean hasPlague = false;
    private int actionsRemaining = 2;
    private int health;
    private String searchingSkill;
    private String classType;

    public CrewMember(int health, String searchingSkill, String name, String classType) {
        this.health = health;
        this.searchingSkill = searchingSkill;
        this.name = name;
        this.classType = classType;
        //this.setName(name);
    }

    public ArrayList<String> sleep() {
        this.tiredness += 50;
        if(this.tiredness > 100) {
            this.tiredness = 100;
        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add(String.format("%s is sleeping...", this.name));
        strings.add(String.format("They now have %d tiredness\n", this.tiredness));

        this.performAction();
        
        return strings;
    }

    public void performAction() {
        this.actionsRemaining --;
        if((this.tiredness - 20) <= 0) {
        	this.tiredness = 0;
        } else {
        	this.tiredness -= 20;
        }
        
        if((this.hunger - 30) <= 0) {
        	this.hunger = 0;
        } else {
        	this.hunger -= 30;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    
    public String getClassType() {
    	return this.classType;
    }

    public int getHunger() {
        return hunger;
    }

    public String increaseHunger(int num) {
        if((this.hunger + num) >= 100) {
            this.hunger = 100;
        } else {
            this.hunger += num;
        }

        //System.out.println(String.format("%s is eating...", this.name));
        return (String.format("%s now has %d hunger\n", this.name, this.hunger));
    }

    public String increaseHealth(int num) {
        if((this.health + num) >= 100) {
            this.health = 100;
        } else {
            this.health += num;
        }
        
        //System.out.println(String.format("%s is healing...", this.name));
        return (String.format("%s now have %d health\n", this.name, this.health));
    }

    public String spacePlagueDamage() {
        this.health -= 20;
        String string = (String.format("%s is sick and lost 20 health\n%s now has %d health", name, name, health));
        
        return string;
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