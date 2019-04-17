import java.util.ArrayList;
import java.util.Scanner;

public class Crew {

    private String name;
    private int crewSize;
    private ArrayList<CrewMember> crewMemberList = new ArrayList<>();
    private ArrayList<Item> ownedItems = new ArrayList<>();
    private ArrayList<CrewMember> crewWithActionsRemaining;
    private ArrayList<CrewMember> crewWithSpacePlague = new ArrayList<>();
    private int money = 100;
    private Ship theShip;
    private int piecesFound = 0;

    public Crew(int numberOfCrew, String name) {
        this.name = name;
        this.crewSize = numberOfCrew;
        this.createCrew(numberOfCrew);
        System.out.println("Crew: " + crewMemberList.toString());
        this.crewWithActionsRemaining = (ArrayList<CrewMember>) crewMemberList.clone();
        this.theShip = new Ship();

        //test item
        Apple apple = new Apple();
        ownedItems.add(apple);
        SpacePlagueCure cure = new SpacePlagueCure();
        ownedItems.add(cure);
        LesserHealing lesser = new LesserHealing();
        ownedItems.add(lesser);
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

    public void updateCrewWithActionsRemaining() {
        ArrayList<CrewMember> actionsRemaining = new ArrayList<>();

        for(CrewMember crewMember : this.crewWithActionsRemaining) {
            if(crewMember.getActionsRemaining() > 0) {
                actionsRemaining.add(crewMember);
            }
        }

        this.crewWithActionsRemaining = actionsRemaining;
    }

    public boolean crewWithActions() {

        return this.crewWithActionsRemaining.size() > 0;
    }

    public void resetCrewActions() {
        for(CrewMember member : this.crewMemberList) {
            member.setActionsRemaining(2);
        }

        this.crewWithActionsRemaining = (ArrayList<CrewMember>) this.crewMemberList.clone();

        System.out.println("Crew members now have 2 actions remaining");
    }

    public String getShipStatus() {

        return this.theShip.toString();
    }

    public void useItem(CrewMember member) {
        if(this.ownedItems.size() > 0) {
            Item item = this.chooseItem();
            item.useItem(member);
            member.performAction();
            this.ownedItems.remove(item); // could possibly remove in chooseItem()
        } else {
            System.out.println("You have no items!");
        }

    }

    public Item chooseItem() {
        Scanner scanner = new Scanner(System.in);
        String items = "Which crew member to perform this action: \n";
        for (int i = 0; i < this.ownedItems.size(); i++) {
            items += String.format("%d. %s\n", i, this.ownedItems.get(i).getName());
        }
        System.out.println(items);
        int index = scanner.nextInt();

        //scanner.close();
        return ownedItems.get(index);
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
