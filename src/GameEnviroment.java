import java.util.ArrayList;
import java.util.Scanner;

public class GameEnviroment {

    private int totalDays;
    private int currentDay = 0;
    private int piecesNeeded;
    private int numberOfCrew;
    private Crew crew;

    public GameEnviroment(int totalDays, int piecesNeeded, int numberOfCrew, String name) {
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
//            Scanner scanner = new Scanner(System.in);
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
                    //this.visitOutPost(); // check if this takes action away from crew
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

    public void newDay() {
        this.currentDay++;
        // create method in crew with for loop to reset all actionsRemaining
        this.crew.resetCrewActions();
        //check for space plague
        //random event
        this.callEvent();
        System.out.println(String.format("Current day: %d", this.currentDay));
    }

    public void viewShipStatus() {

        System.out.println(crew.getShipStatus());
    }

    public void viewCrewMemberStatus() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CrewMember> members = crew.getCrewMemberList();
        System.out.println(members.toString());
        int crewMemberNumber = -1;
        do {
            System.out.println("Which crew member?");
            if(scanner.hasNextInt()) {
                crewMemberNumber = scanner.nextInt();
            }
        } while(crewMemberNumber < 0);

        System.out.println(members.get(crewMemberNumber).toString());
    }

    public void callEvent() {
        System.out.println("Event called");
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

    public void repairShip() {
        if(this.crew.crewWithActions()) {
            CrewMember member  = this.chooseCrewMember();
            this.crew.getTheShip().repairShields(member);
        } else {
            System.out.println("There are no crew with actions remaining");
        }
    }

    public void sleep() {
        if(this.crew.crewWithActions()) {
            CrewMember member = this.chooseCrewMember();
            member.sleep();
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
        Scanner scanner = new Scanner(System.in);
        int totalDays = 0;
        do {
            System.out.println("How many days would you like? (Between 3 - 10)");
            if(scanner.hasNextInt()) {
                totalDays = scanner.nextInt();
            }
        } while(totalDays < 3 || totalDays > 10);

        int piecesNeeded = 2 * (totalDays / 3);
        System.out.println("Therefore number of pieces needed is " + piecesNeeded);

        int numberOfCrew = 0;
        do {
            System.out.println("How many crew members would you like? (Between 2 - 4)");
            if(scanner.hasNextInt()) {
                numberOfCrew = scanner.nextInt();
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


        GameEnviroment game = new GameEnviroment(totalDays, piecesNeeded, numberOfCrew, name);
        game.playGame();
        System.out.println("\n");
        //game.viewCrewMemberStatus();
        System.out.println("\n");
        //game.sleep();
    }
}