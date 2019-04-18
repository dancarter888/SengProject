import java.util.Scanner;

public class Ship {

    private String name;
    private int shields = 100;
    private Planet location;

    public Ship() {
        this.name = this.setName();
        this.location = new StartPlanet();
    }

    public String getName() {
        return name;
    }

    private String setName() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Ship name?");
            name = scanner.nextLine();
        } while(name.length() == 0);

        return name;
    }

    public void repairShields(CrewMember member) {
        // maybe different amount depending on class
        if((this.shields + 20) >= 100) {
            this.shields = 100;
        } else {
            this.shields += 20;
        }

        System.out.println(String.format("%s repairing shields...", member.getName()));
        System.out.println(String.format("Ship now has %d shields", this.shields));
        member.performAction();
    }

    public String searchThePlanet(CrewMember member) {
        String item = this.location.searchPlanet(member);
        member.performAction();

        return item;
    }

    public void travelToNewPlanet(CrewMember memberOne, CrewMember memberTwo) {
        System.out.println("Travelling to a new planet... (Doesn't actually work yet)");
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }

    public String getLocation() {
        return location.name;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public String toString() {
        return String.format("Name: %s, Shields: %d, Location: %s", this.name, this.shields, this.location); // need location string not just the object
    }


}
