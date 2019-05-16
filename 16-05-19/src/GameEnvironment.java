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
    	launchCreateCrewMembersGui();
    }
    
    public void launchCreateCrewMembersGui() {
    	CreateCrewMembers createGui = new CreateCrewMembers(this);
    }
    
    public void closeCreateCrewMembersGui(CreateCrewMembers createGui) {
    	createGui.closeWindow();
    	launchGameGui();
    }
    
    public void launchPilotShipGui(GameScreen gameGui) {
    	gameGui.closeWindow();
    	PilotShipScreen pilotGui = new PilotShipScreen(this);
    }
    
    public void closePilotShipGui(PilotShipScreen pilotGui) {
    	pilotGui.closeWindow();
    	launchGameGui();
    }
    
    public void launchOutPostGui(GameScreen gameGui) {
    	gameGui.closeWindow();
    	OutPost outpost = this.crew.getTheShip().getLocation().getOutPost();
    	OutPostGui outPostGui = new OutPostGui(this, outpost, this.crew);
    }
    
    public void closeOutPostGui(OutPostGui outPostGui) {
    	outPostGui.closeWindow(); 
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
                        //this.takeAction();
                    	System.out.println("Changed");
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
    	this.crew.getTheShip().getLocation().getOutPost().visitShop(this.crew);;
    }

    public ArrayList<String> takeAction(int actions, CrewMember member) {
    	ArrayList<String> strings = new ArrayList<>(); //place holder
    	if(member.getActionsRemaining() == 0) {
    		strings.add("This crew member has no actions remaining!");
    	} else if(member.getTiredness() == 0 && actions != 2) {
    		strings.add("This crew member has no energy left!");
    	} else if(member.getHunger() < 20) {
    		strings.add("This crew member needs to eat!");
    	} else {
	        switch (actions) {
	            case 1:
	                //consumeItem
	                //create method in crew use item where can choose which item and which member to use it on, eat/takeMedicine method in crew member
	                //Actions.useItem(this.crew, member);
	                break;
	            case 2:
	                // sleep
	                // Choose crew member here and create method in crewMember
	            	strings = Actions.sleep(member);
	                break;
	            case 3:
	                //repair
	                // create method in ship
	                strings = Actions.repairShip(this.crew, member);
	                break;
	            case 4:
	                //search planet
	                // get planet and use search method
	            	strings = Actions.searchPlanet(this.crew, member);
	                break;
	            case 5:
	                //Pilot Ship
	                //Actions.pilotShip(this.crew);
	                break;
	            default:
	                System.out.println("No actions");
	                break;
	        }
	        this.crew.updateCrewWithActionsRemaining();
    	}
        
        return strings;
    }

    public ArrayList<String> newDay() {
        this.currentDay++;
        // create method in crew with for loop to reset all actionsRemaining
        this.crew.resetCrewActions();
        //check for space plague
        ArrayList<String> strings = this.crew.dealSpacePlagueDamage();
        this.numberOfCrew = this.crew.getCrewSize();
        //random event
        Random rand = new Random();
        int randEvent = rand.nextInt(2);
        strings.add(this.callEvent(randEvent));
        strings.add(String.format("Current day: %d", this.currentDay));
        
        return strings;
    }

    public String callEvent(int event) {
        //System.out.println("\nEvent called");
    	String text = "";
        switch (event) {
            case 0:
                text = Events.alienPirates(this.crew);
                break;
            case 1:
                if (this.crew.getCrewMemberList().size() > this.crew.getCrewWithSpacePlague().size()) {
                	text = Events.spacePlague(this.crew);
                }
                else {
                	text = Events.alienPirates(this.crew);
                }
                break;
        }
        
        return text;
    }

    public static void main(String[] args) {
    	GameEnvironment game = new GameEnvironment(0, 0, 0, "hello");
    	game.launchSetupGui();
    }
}