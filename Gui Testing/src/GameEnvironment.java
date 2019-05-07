import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameEnvironment {

    private int totalDays;
    private int currentDay = 1;
    private int piecesNeeded;
    private int numberOfCrew;
    private Crew crew;
    public static final ArrayList<Item> allItems = getAllItems();
    private String crewName;
    private String shipName;

    public GameEnvironment(int totalDays, int piecesNeeded, int numberOfCrew, String name) {
        this.totalDays = totalDays;
        this.piecesNeeded = piecesNeeded;
        this.numberOfCrew = numberOfCrew;
        //this.crew = new Crew(numberOfCrew, name);
    }

    public static ArrayList<Item> getAllItems() {
        Apple apple = new Apple();
        SpacePlagueCure cure = new SpacePlagueCure();
        LesserHealing lesser = new LesserHealing();

        ArrayList<Item> items = new ArrayList<>();
        items.add(apple);
        items.add(cure);
        items.add(lesser);

        return items;
    }
    
    public void launchGameGui() {
    	GameScreen gameGui = new GameScreen(this);
    }
    
    public void closeGameGui(GameScreen mainWindow) {
    	mainWindow.closeWindow();
    }

    public void launchSetupGui() {
    	GameSetup setupWindow = new GameSetup(this);
    }
    
    public void closeSetupGui(GameSetup setupWindow) {
    	setupWindow.closeWindow();
    	for(int i=0; i<this.numberOfCrew; i++) {
    		//launch crew adding maybe
    	}
    	launchGameGui();
    }
    
    public void createCrew(int numberOfCrew, String crewName, String shipName) {
    	this.crew = new Crew(numberOfCrew, crewName, shipName);    	
    }
    
    // Just for gui ###############
    public void setTotalDays(int days) {
    	totalDays = days;
    }
    
    public void setPiecesNeeded(int num) {
    	piecesNeeded = num;
    }
    
    public void setNumberOfCrew(int num) {
    	numberOfCrew = num;
    }
    
    public void setCrewName(String name) {
    	this.crewName = name;
    }
    
    public void setShipName(String name) {
    	this.shipName = name;
    }
    
    // ############################
    public int getTotalDays() {
        return totalDays;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getPiecesNeeded() {
        return piecesNeeded;
    }

    public int getNumberOfCrew() {
        return numberOfCrew;
    }
    
    public String getCrewName() {
    	return this.crewName;
    }
    
    public String getShipName() {
    	return this.shipName;
    }

    public Crew getCrew() {
        return crew;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while ((currentDay < totalDays + 1) && (crew.getPiecesFound() < piecesNeeded) && (this.crew.getTheShip().getShields() > 0) && (this.crew.getCrewMemberList().size() > 0)) {
            System.out.println(String.format("\nActivities: \n1.View Crew Status\n2.View Ship Status\n3.Go to Outpost"
                    + "\n4.Perform Action\n5.Move to next day"));

            int activity = scanner.nextInt();
            switch (activity) {
                case 1: //View Crew Status
                    this.viewCrewMemberStatus(); //done
                    break;
                case 2: //View Ship Status
                    this.viewShipStatus(); //done
                    break;
                case 3: //Go To OutPost
                    this.visitOutPost(); //done
                    break;
                case 4: //Perform Action
                    if(this.crew.crewWithActions()) {
                        this.takeAction();
                    } else {
                        System.out.println("There are no crew with actions remaining");
                    }
                    break;
                case 5: //Move To Next Day
                    this.newDay(); //just need to do random events
                    break;
                default:
                    System.out.println("enter something");
                    break;
            }
        }
        if (crew.getPiecesFound() == piecesNeeded) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lose!");
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
        OutPost outpost = this.crew.getTheShip().getLocation().getOutPost();
        int actions= 0;
        while (actions != 3) {
            System.out.println("You Are At " + this.crew.getTheShip().getLocation().getName() + "'s OutPost.");
            System.out.println("You Have $" + this.crew.getMoney() + ".");
            System.out.println("Possible actions:\n1. View Items For Sale\n2. View My Items\n3. Leave OutPost");
            actions = scanner.nextInt();
            switch (actions) {
                case 1:
                    //View Items For Sale
                    outpost.viewItemsForSale();
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

    public void askBuyItems() {
        Scanner scanner = new Scanner(System.in);

        OutPost outpost = this.crew.getTheShip().getLocation().getOutPost();
        int action = 0;
        while (action != 3) {
            System.out.println("What Would You Like To Do?\n1. Buy\n2. Get Description\n3. Leave");
            action = scanner.nextInt();
            switch (action) {
                case 1: //Buy
                    outpost.buyItems(this.crew);
                    break;
                case 2: //Get Description
                    getDescription();
                    break;
                case 3: //Leave
                    System.out.println("No Items Bought\n");
                    break;
                default:
                    System.out.println("No actions");
                    break;

            }
        }
    }

    public void getDescription() {
        Scanner scanner = new Scanner(System.in);
        int action;
        OutPost outpost = this.crew.getTheShip().getLocation().getOutPost();
        ArrayList<Item> itemsForSale = this.crew.getTheShip().getLocation().getOutPost().getOutPostItems();
        System.out.println("Get Description Of Which Item?");
        outpost.viewItemsForSale();
        action = scanner.nextInt();
        Item itemToDescribe = itemsForSale.get(action - 1);
        System.out.println(itemToDescribe.getDescription() + "\n");
    }

    public void viewMyItems() {//this seems like it could be improved
        ArrayList<Item> allItems = this.crew.getTheShip().getLocation().getOutPost().getOutPostItems();
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
                //consumeItem
                //create method in crew use item where can choose which item and which member to use it on, eat/takeMedicine method in crew member
                Actions.useItem(this.crew);
                break;
            case 2:
                // sleep
                // Choose crew member here and create method in crewMember
                Actions.sleep(this.crew);
                break;
            case 3:
                //repair
                // create method in ship
                Actions.repairShip(this.crew);
                break;
            case 4:
                //search planet
                // get planet and use search method
                Actions.searchPlanet(this.crew);
                break;
            case 5:
                //Pilot Ship
                Actions.pilotShip(this.crew);
                break;
            default:
                System.out.println("No actions");
                break;
        }
        this.crew.updateCrewWithActionsRemaining();
    }

    public void newDay() {
        this.currentDay++;
        // create method in crew with for loop to reset all actionsRemaining
        this.crew.resetCrewActions();
        //check for space plague
        this.crew.dealSpacePlagueDamage();
        //random event
        Random rand = new Random();
        int randEvent = rand.nextInt(2);
        this.callEvent(randEvent);
        System.out.println(String.format("Current day: %d", this.currentDay));
    }

//    private void dealSpacePlagueDamage() {
//        // updates the sapce plague list
//        this.crew.updateCrewWithSpacePlague(); // where to do this
//        // deals the damage for the space plague
//        ArrayList<CrewMember> sickMembers = this.crew.getCrewWithSpacePlague();
//        for(CrewMember member : sickMembers) {
//            member.spacePlagueDamage();
//        }
//    }

    public void callEvent(int event) {
        System.out.println("\nEvent called");
        switch (event) {
            case 0:
                Events.alienPirates(this.crew);
                break;
            case 1:
                if (this.crew.getCrewMemberList().size() > this.crew.getCrewWithSpacePlague().size()) {
                    Events.spacePlague(this.crew);
                }
                else {
                    Events.alienPirates(this.crew);
                }
                break;
        }
    }

    public static void main(String[] args) {
    	
    	GameEnvironment game = new GameEnvironment(0, 0, 0, "hello");
    	game.launchSetupGui();
    	
    	
    	
    	
//        //Sets Up Game
//        System.out.println("Commence Game Setup");
//        Scanner scanner = new Scanner(System.in);
//        int totalDays = 0;
//        do {
//            System.out.println("How many days would you like? (Between 3 - 10)");
//            if(scanner.hasNextInt()) {
//                totalDays = scanner.nextInt();
//                scanner.nextLine(); // Clears input from scanner
//                if (totalDays < 3 || totalDays > 10) {
//                    System.out.println("You entered " + totalDays + ". Enter an Int between 3 and 10");
//                }
//            } else if(scanner.hasNext()) {
//                String string = scanner.nextLine();
//                System.out.println("You entered " + string + ". Enter an Int between 3 and 10");
//            }
//        } while(totalDays < 3 || totalDays > 10);
//
//        int piecesNeeded = 2 * (totalDays / 3);
//        System.out.println("Therefore number of pieces needed is " + piecesNeeded + ".\n");
//
//        int numberOfCrew = 0;
//        do {
//            System.out.println("How many crew members would you like? (Between 2 - 4)");
//            if(scanner.hasNextInt()) {
//                numberOfCrew = scanner.nextInt();
//                scanner.nextLine(); // Clears input from scanner
//                if (numberOfCrew < 2 || numberOfCrew > 4) {
//                    System.out.println("You entered " + numberOfCrew + ". Enter an Int between 2 and 4");
//                }
//            } else if(scanner.hasNext()) {
//                String string = scanner.nextLine();
//                System.out.println("You entered " + string + ". Enter an Int between 2 and 4");
//            }
//        } while(numberOfCrew < 2 || numberOfCrew > 4);
//
//        String name = "";
//        do {
//            System.out.println("What is the name of your crew?");
//            if(scanner.hasNextLine()) {
//                name = scanner.nextLine();
//            }
//        } while(name.length() == 0);
//
////        System.out.println(piecesNeeded);
////        System.out.println(totalDays);
//        GameEnvironment game = new GameEnvironment(totalDays, piecesNeeded, numberOfCrew, name);
//
//        //Prints Game Details
//        System.out.println("--------------------");
//        System.out.println("Days: " + game.getTotalDays() + "\n");
//        System.out.println("Pieces Needed: " + piecesNeeded + "\n");
//        game.getCrew().printCrew();
//        System.out.println("Ship:");
//        System.out.println(game.getCrew().getTheShip() + "\n");
//        System.out.println("Current Day: " + game.getCurrentDay());
//        System.out.println("--------------------\n");
//
//        //Plays Game
//        System.out.println("Commence Game Play");
//        game.playGame();
//
//        //Tests
//        //game.viewCrewMemberStatus();
//        //game.viewShipStatus();
//        //game.visitOutPost();
//        //game.takeAction();
//        //game.newDay();
    }
}