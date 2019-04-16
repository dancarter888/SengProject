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

    public void callEvent() {
        System.out.println("Event called");
    }



    public void pilotShip() {
        System.out.println("Ship piloted");
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
        while (currentDay < totalDays) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(String.format("Activities: \n1.View Crew Status\n2.View Ship Status\n3.Go to Outpost"
                    + "\n4.Perform Action\5.Move to next day"));

            int activity = scanner.nextInt();
            switch (activity) {
                case 1:
                    this.viewCrewMemberStatus();
                    break;
                case 2:
                    this.viewShipStatus();
                    break;
                case 3:
                    //this.visitOutPost();
                    break;
                case 4:
                    this.takeAction();
                    break;
                case 5:
                    this.newDay();
                    break;
                default:
                    System.out.println("enter something");
                    break;
            }
        }
    }

    public void newDay() {
        this.currentDay++;
        // create method in crew with for loop to reset all actionsRemaining
        //random event
    }

    public void takeAction() {
        System.out.println("Possible actions:\n1. Eat/Medicine\n2. Sleep\n3. Repair Shields\n4. Search Planet\n5. Pilot Ship\n");
        Scanner scanner = new Scanner(System.in);
        int actions = scanner.nextInt();
        switch (actions) {
            case 1:
                //eat/medicine
                //create method in crew use item where can coose which item and which member to use it on, eat/takeMedicine method in crew member
                break;
            case 2:
                // sleep
                // Choose crew member here and create method in crewMember
                break;
            case 3:
                //repair
                // create method in ship
                break;
            case 4:
                //search planet
                // get planet and use search method
                break;
            case 5:
                this.pilotShip();
                break;
            default:
                System.out.println("No actions");
                break;

        }



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
        game.viewCrewMemberStatus();
    }
}