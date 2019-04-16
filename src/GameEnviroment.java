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

    public void newDay() {
        System.out.println("New day");
    }

    public void takeAction(CrewMember member) {
        System.out.println("Do something");
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
    
    public void viewCrewMemberStatus(CrewMember member) {
        
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
    }
}
