import java.util.Scanner;

public class Ship {

    private String name;
    private int shields = 100;
    private Planet location;

    public Ship() {
        this.name = this.setName();
        this.location = new Planet();
    }

    public String getName() {
        return name;
    }

    private String setName() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        do {
            System.out.println("Ship name?");
            name = scanner.nextLine();
        } while(name.length() == 0);

        return name;
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
}
