import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameEnvironment {

    private int totalDays;
    private int currentDay = 0;
    private int piecesNeeded;
    private int numberOfCrew;
    private Crew crew;

    public GameEnvironment(int totalDays, int piecesNeeded, int numberOfCrew, String name) {
        this.totalDays = totalDays;
        this.piecesNeeded = piecesNeeded;
        this.numberOfCrew = numberOfCrew;
        this.crew = new Crew(numberOfCrew, name);
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getPiecesNeeded() {
        return piecesNeeded;
    }

    public void setPiecesNeeded(int piecesNeeded) {
        this.piecesNeeded = piecesNeeded;
    }

    public int getNumberOfCrew() {
        return numberOfCrew;
    }

    public void setNumberOfCrew(int numberOfCrew) {
        this.numberOfCrew = numberOfCrew;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (currentDay < totalDays) { // also add win condition
            System.out.println(String.format("\nActivities: \n1.View Crew Status\n2.View Ship Status\n3.Go to Outpost"
                    + "\n4.Perform Action\n5.Move to next day"));

            int activity = scanner.nextInt();
            switch (activity) {
                case 1:
                    this.viewCrewMemberStatus(); //done
                    break;
                case 2:
                    this.viewShipStatus(); //done
                    break;
                case 3:
                    this.visitOutPost(); // check if this takes action away from crew
                    break;
                case 4:
                    this.takeAction();
                    break;
                case 5:
                    this.newDay(); //just need to do random events
                    break;
                default:
                    System.out.println("enter something");
                    break;
            }
        }
        scanner.close();
    }

    public void viewCrewMemberStatus() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CrewMember> members = crew.getCrewMemberList();
        crew.printCrew();
        int crewMemberNumber = -1;
        do {
            System.out.println("Which crew member?");
            if(scanner.hasNextInt()) {
                crewMemberNumber = scanner.nextInt();
                scanner.nextLine(); // Clears input from scanner
                if (crewMemberNumber < 1 || crewMemberNumber > numberOfCrew) {
                    System.out.println("You entered " + crewMemberNumber + ". Enter an Int between 1 and " + (numberOfCrew));
                }
            } else if(scanner.hasNext()) {
                String string = scanner.nextLine();
                System.out.println("You entered " + string + ". Enter an Int between 1 and " + (numberOfCrew));
            }
        } while(crewMemberNumber < 1 || crewMemberNumber > numberOfCrew);
        System.out.println(members.get(crewMemberNumber - 1).toString());
    }

    public void viewShipStatus() {
        System.out.println(crew.getShipStatus());
    }

    public void visitOutPost() {
        Scanner scanner = new Scanner(System.in);
        int actions= 0;
        while (actions != 3) {
            System.out.println("You Are At " + this.crew.getTheShip().getLocation().getName() + "'s OutPost.");
            System.out.println("You Have $" + this.crew.getMoney() + ".");
            System.out.println("Possible actions:\n1. View Items For Sale\n2. View My Items\n3. Leave OutPost");
            actions = scanner.nextInt();
            switch (actions) {
                case 1:
                    //View Items For Sale
                    this.viewItemsForSale();
                    //Asks if you want to buy
                    this.askBuyItems();
                    break;
                case 2:
                    // View My Items
                    this.viewMyItems();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("No actions");
                    break;

            }
        }
    }

    public void viewItemsForSale() {
        ArrayList<Item> itemsForSale = this.crew.getTheShip().getLocation().getOutPost().getAllItems(); //change this to be randomized
        System.out.println("Items For Sale:");
        int i = 0;
        for (Item item : itemsForSale) {
            i ++;
            System.out.println(i + ". " + item);
        }
        System.out.println("\n");
    }

    public void askBuyItems() {
        Scanner scanner = new Scanner(System.in);

        int action = 0;
        while (action != 2) {
            System.out.println("Buy Anything?\n1. Yes\n2. No");
            action = scanner.nextInt();
            switch (action) {
                case 1:
                    buyItems();
                    break;
                case 2:
                    System.out.println("No Items Bought\n");
                    break;
                default:
                    System.out.println("No actions");
                    break;

            }
        }
    }

    public void buyItems() {
        Scanner scanner = new Scanner(System.in);
        int action;
        ArrayList<Item> itemsForSale = this.crew.getTheShip().getLocation().getOutPost().getAllItems();
        System.out.println("Which Item Would You Like?");
        viewItemsForSale();
        action = scanner.nextInt();
        Item itemBought = itemsForSale.get(action - 1);
        if (this.crew.getMoney() > itemBought.getCost() || crew.getMoney() == itemBought.getCost()) { //If they can afford item
            this.crew.removeMoney(itemBought.getCost());
            this.crew.addOwnedItems(itemBought);
            System.out.println("Item Purchased: " + itemBought.getName() + "\nYou have $" + this.crew.getMoney() + " remaining.");
        } else { //Can't afford item
            System.out.println("Not enough money! This item costs $" + itemBought.getCost() + ", you only have $" + this.crew.getMoney());
        }
    }

    public void viewMyItems() {//this seems like it could be improved
        ArrayList<Item> allItems = this.crew.getTheShip().getLocation().getOutPost().getAllItems();
        System.out.println("My Items:");

        for (Item item : allItems) {
            int frequency = 0;
            for (Item myItem : this.crew.getOwnedItems()) {
                if (item.getName() == myItem.getName()) {
                    frequency ++;
                }
            }
            System.out.println(frequency + "x " + item);
        }

        System.out.println("\n");
    }

    public void takeAction() {
        System.out.println("Possible actions:\n1. Eat/Medicine\n2. Sleep\n3. Repair Shields\n4. Search Planet\n5. Pilot Ship\n");
        Scanner scanner = new Scanner(System.in);
        int actions = scanner.nextInt();
        switch (actions) {
            case 1:
                //eat/medicine
                //create method in crew use item where can choose which item and which member to use it on, eat/takeMedicine method in crew member
                if(this.crew.crewWithActions()) {
                    CrewMember member = this.chooseCrewMember();
                    this.crew.useItem(member);
                } else {
                    System.out.println("There are no crew with actions remaining");
                }
                break;
            case 2:
                // sleep
                // Choose crew member here and create method in crewMember
                this.sleep();
                break;
            case 3:
                //repair
                // create method in ship
                this.repairShip();
                break;
            case 4:
                //search planet
                // get planet and use search method
                this.searchPlanet();
                break;
            case 5:
                this.pilotShip();
                break;
            default:
                System.out.println("No actions");
                break;

        }

        this.crew.updateCrewWithActionsRemaining();
    }

    public void sleep() {
        if(this.crew.crewWithActions()) {
            CrewMember member = this.chooseCrewMember();
            member.sleep();
        } else {
            System.out.println("There are no crew with actions remaining");
        }

    }

    public void newDay() {
        this.currentDay++;
        // create method in crew with for loop to reset all actionsRemaining
        this.crew.resetCrewActions();
        //check for space plague
        //random event
        this.callEvent();
        System.out.println(String.format("Current day: %d", this.currentDay));
    }

    public void callEvent() {
        System.out.println("Event called");
    }

    public void repairShip() {
        if(this.crew.crewWithActions()) {
            CrewMember member  = this.chooseCrewMember();
            this.crew.getTheShip().repairShields(member);
        } else {
            System.out.println("There are no crew with actions remaining");
        }
    }

    public void searchPlanet() {
        if(this.crew.crewWithActions()) {
            CrewMember member  = this.chooseCrewMember();
            // using item as string till Item works
            String item = this.crew.getTheShip().searchThePlanet(member);
            if(item == null) {
                System.out.println("No item found");
            } else {
                System.out.println("Item found");
            }
        } else {
            System.out.println("There are no crew with actions remaining");
        }
    }

    public void pilotShip() {
        if(this.crew.getCrewWithActionsRemaining().size() >= 2) {
            CrewMember memberOne  = this.chooseCrewMember();
            CrewMember memberTwo  = this.chooseCrewMember();

            this.crew.getTheShip().travelToNewPlanet(memberOne, memberTwo);
            memberOne.performAction();
            memberTwo.performAction();
        } else {
            System.out.println("It takes two crew members to pilot ship");
        }
    }

    public CrewMember chooseCrewMember() {
        Scanner scanner = new Scanner(System.in);
        String members = "Which crew member to perform this action: \n";
        ArrayList<CrewMember> memberList = this.crew.getCrewWithActionsRemaining();
        for (int i = 0; i < memberList.size(); i++) {
            members += String.format("%d. %s\n", i, memberList.get(i).getName());
        }
        System.out.println(members);
        int index = scanner.nextInt();

        //scanner.close();
        return memberList.get(index);
    }



    public static void main(String[] args) {
        //Sets Up Game
        System.out.println("Commence Game Setup");
        Scanner scanner = new Scanner(System.in);
        int totalDays = 0;
        do {
            System.out.println("How many days would you like? (Between 3 - 10)");
            if(scanner.hasNextInt()) {
                totalDays = scanner.nextInt();
                scanner.nextLine(); // Clears input from scanner
                if (totalDays < 3 || totalDays > 10) {
                    System.out.println("You entered " + totalDays + ". Enter an Int between 3 and 10");
                }
            } else if(scanner.hasNext()) {
                String string = scanner.nextLine();
                System.out.println("You entered " + string + ". Enter an Int between 3 and 10");
            }
        } while(totalDays < 3 || totalDays > 10);

        int piecesNeeded = 2 * (totalDays / 3);
        System.out.println("Therefore number of pieces needed is " + piecesNeeded + ".\n");

        int numberOfCrew = 0;
        do {
            System.out.println("How many crew members would you like? (Between 2 - 4)");
            if(scanner.hasNextInt()) {
                numberOfCrew = scanner.nextInt();
                scanner.nextLine(); // Clears input from scanner
                if (numberOfCrew < 2 || numberOfCrew > 4) {
                    System.out.println("You entered " + numberOfCrew + ". Enter an Int between 2 and 4");
                }
            } else if(scanner.hasNext()) {
                String string = scanner.nextLine();
                System.out.println("You entered " + string + ". Enter an Int between 2 and 4");
            }
        } while(numberOfCrew < 2 || numberOfCrew > 4);

        String name = "";
        do {
            System.out.println("What is the name of your crew?");
            if(scanner.hasNextLine()) {
                name = scanner.nextLine();
            }
        } while(name.length() == 0);

//        System.out.println(piecesNeeded);
//        System.out.println(totalDays);
        GameEnvironment game = new GameEnvironment(totalDays, piecesNeeded, numberOfCrew, name);

        //Prints Game Details
        System.out.println("--------------------");
        System.out.println("Days: " + game.getTotalDays() + "\n");
        System.out.println("Pieces Needed: " + piecesNeeded + "\n");
        game.getCrew().printCrew();
        System.out.println("Ship:");
        System.out.println(game.getCrew().getTheShip());
        System.out.println("--------------------\n");

        //Plays Game
        System.out.println("Commence Game Play");
        game.playGame();

        //Tests
        //game.viewCrewMemberStatus();
        //game.viewShipStatus();
        //game.visitOutPost();
        //game.takeAction();
        //game.newDay();
    }
}