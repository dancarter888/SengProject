import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ship {

    private String name;
    private int shields = 100;
    private Planet location;
    private ArrayList<Planet> allPlanets = new ArrayList<>();

    public Ship(String name) {
        this.name = name;
        this.location = new StartPlanet();
        setAllPlanets();
    }

    public void setAllPlanets() {
        Planet startPlanet = this.location;
        Planet earth = new Earth();
        allPlanets.add(startPlanet);
        allPlanets.add(earth);
        System.out.println("Planets" + allPlanets + "\n");
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> repairShields(CrewMember member) {
        // maybe different amount depending on class
        if((this.shields + 20) >= 100) {
            this.shields = 100;
        } else {
            this.shields += 20;
        }
        
        ArrayList<String> strings = new ArrayList<>();
        strings.add(String.format("%s repairing shields...", member.getName()));
        strings.add(String.format("Ship now has %d shields", this.shields));
        member.performAction();
        
        return strings;
    }

    public ArrayList<String> searchThePlanet(Crew crew, CrewMember member) {
        ArrayList<String> strings = this.location.searchPlanet(crew, member);
        member.performAction();
        return strings;
    }

    public void travelToNewPlanet(CrewMember memberOne, CrewMember memberTwo) {
        Planet newPlanet = this.choosePlanet();
        this.location = newPlanet;
        this.asteroidBelt(); // Could possibly use observer for this maybe dont know
        System.out.println(memberOne.getName() + " and " + memberTwo.getName() + " have piloted ship to " + this.location.getName());
    }

    private void asteroidBelt() {
        Random rand = new Random();
        int num = rand.nextInt(2);
        if(num == 1) {
            this.shields -= 20;
            System.out.println(String.format("Asteroid belt hit while travelling to %s!\n Shields are now %d", this.location, this.shields));

        }
    }

    public Planet choosePlanet() {
        Scanner scanner = new Scanner(System.in);
        String planets = "Which Planet To Travel To?\n";
        ArrayList<Planet> planetList = (ArrayList<Planet>) this.allPlanets.clone();
        planetList.remove(this.location);
        for (int i = 1; i < planetList.size() + 1; i++) {
            if (planetList.get(i - 1) != this.location) {
                planets += String.format("%d. %s\n", i, planetList.get(i - 1).getName());
            }
        }
        System.out.println(planets);
        int index = scanner.nextInt();

        //scanner.close();
        return planetList.get(index - 1);
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public String toString() {
        return String.format("Name: %s\nShields: %d\nLocation: %s", this.name, this.shields, this.location); // need location string not just the object
    }


}
