import java.util.ArrayList;
import java.util.Scanner;

public class Crew {

    private String name;
    private int crewSize;
    private ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    private ArrayList<Item> ownedItems;
    private ArrayList<CrewMember> crewWithActionsRemaining;
    private ArrayList<CrewMember> crewWithSpacePlague = new ArrayList<>();
    private int money = 0;
    private Ship theShip;
    private int piecesFound = 0;

    public Crew(int numberOfCrew, String name) {
        this.name = name;
        this.crewSize = numberOfCrew;
        this.createCrew(numberOfCrew);
        System.out.println("Crew: " + crewMemberList.toString());
        this.crewWithActionsRemaining = (ArrayList<CrewMember>) crewMemberList.clone();
        this.theShip = new Ship();
    }

    public void createCrew(int numberOfCrew) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Crew member options: \n1.Captain\n2.Engineer"));
        for(int i=0; i<numberOfCrew; i++) {
            int classSelected = 0;
            do {
                System.out.println(String.format("Crew member no. %d: ", i+1));
                if(scanner.hasNextInt()) {
                    classSelected = scanner.nextInt();
                }
            } while(classSelected == 0);

            System.out.println("Adding crew...");
            switch (classSelected) {
                case 1:
                    Captain newCaptain = new Captain();
                    this.crewMemberList.add(newCaptain);
                    break;
                case 2:
                    Engineer newEngineer = new Engineer();
                    this.crewMemberList.add(newEngineer);
                    break;
                default:
                    System.out.println("not 1 or 2");
                    break;
            }
        }

    }

    public String getShipStatus() {

        return this.theShip.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public ArrayList<CrewMember> getCrewMemberList() {
        return crewMemberList;
    }

    public void setCrewMemberList(ArrayList<CrewMember> crewMemberList) {
        this.crewMemberList = crewMemberList;
    }

//    public ArrayList<Item> getOwnedItems() {
//        return ownedItems;
//    }

//    public void setOwnedItems(ArrayList<Item> ownedItems) {
//        this.ownedItems = ownedItems;
//    }

    public ArrayList<CrewMember> getCrewWithActionsRemaining() {
        return crewWithActionsRemaining;
    }

    public void setCrewWithActionsRemaining(ArrayList<CrewMember> crewWithActionsRemaining) {
        this.crewWithActionsRemaining = crewWithActionsRemaining;
    }

    public ArrayList<CrewMember> getCrewWithSpacePlague() {
        return crewWithSpacePlague;
    }

    public void setCrewWithSpacePlague(ArrayList<CrewMember> crewWithSpacePlague) {
        this.crewWithSpacePlague = crewWithSpacePlague;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Ship getTheShip() {
        return theShip;
    }

    public void setTheShip(Ship theShip) {
        this.theShip = theShip;
    }

    public int getPiecesFound() {
        return piecesFound;
    }

    public void setPiecesFound(int piecesFound) {
        this.piecesFound = piecesFound;
    }

//    @Override
//    public String toString() {
//
//    }


}
