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
        this.crewWithActionsRemaining = (ArrayList<CrewMember>) crewMemberList.clone();
        this.theShip = new Ship();

        //test item
        Apple apple = new Apple();
        ownedItems.add(apple);
        SpacePlagueCure cure = new SpacePlagueCure();
        ownedItems.add(cure);
        LesserHealing lesser = new LesserHealing();
        ownedItems.add(lesser);
        ownedItems.add(cure);
        ownedItems.add(lesser);
        ownedItems.add(cure);
        ownedItems.add(apple);
        ownedItems.add(cure);
        ownedItems.add(lesser);
    }

    public void createCrew(int numberOfCrew) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        while (i < numberOfCrew + 1) {
            int classSelected = 0;
            do {
                System.out.println(String.format("Crew member options for " + name + ": \n1.Captain\n2.Engineer"));
                System.out.println(String.format("Choose crew member no. %d: ", i));
                if(scanner.hasNextInt()) {
                    classSelected = scanner.nextInt();
                    scanner.nextLine(); // Clears input from scanner
                    if (classSelected < 1 || classSelected > 2) {
                        System.out.println("You entered " + classSelected + ". Enter an Int between 1 and 2");
                    } else {
                        i ++;
                    }
                } else if(scanner.hasNext()) {
                    String string = scanner.nextLine();
                    System.out.println("You entered " + string + ". Enter an Int between 1 and 2");
                }
            } while(classSelected == 0);


            switch (classSelected) {
                case 1:
                    Captain newCaptain = new Captain();
                    System.out.println("Added Captain " + newCaptain.getName());
                    this.crewMemberList.add(newCaptain);
                    break;
                case 2:
                    Engineer newEngineer = new Engineer();
                    System.out.println("Added Engineer "  + newEngineer.getName());
                    this.crewMemberList.add(newEngineer);
                    break;
                default:
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
        String items = "Which Item To Use? \n";
        for (int i = 1; i < this.ownedItems.size() + 1; i++) {
            items += String.format("%d. %s\n", i, this.ownedItems.get(i - 1).getName());
        }
        System.out.println(items);
        int index = scanner.nextInt();

        //scanner.close();
        return ownedItems.get(index - 1);
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

    public ArrayList<Item> getOwnedItems() {
        return ownedItems;
    }

    public void addOwnedItems(Item item) {
        this.ownedItems.add(item);
    }

    public void removeOwnedItems(int indexRemoved) {
        this.ownedItems.remove(indexRemoved);
    }

    public void setOwnedItems(ArrayList<Item> ownedItems) {
        this.ownedItems = ownedItems;
    }


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

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount) {
        this.money -= amount;
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

    public void printCrew() {
        String printString = "Crew named " + name + ":\n";
        int i = 0;
            for (CrewMember member : crewMemberList) {
                i ++;
                printString += i + ". ";
                printString += member.toString();
                printString += "\n";
            }
        System.out.println(printString);
    }


}
