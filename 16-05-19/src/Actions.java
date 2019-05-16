import java.util.ArrayList;

public class Actions {

    public static ArrayList<String> sleep(CrewMember member) {
    	ArrayList<String> strings = member.sleep();
    	return strings;
    }

    public static ArrayList<String> repairShip(Crew crew, CrewMember member) {
    	ArrayList<String> strings = crew.getTheShip().repairShields(member);
    	return strings;
    }

    public static ArrayList<String> searchPlanet(Crew crew, CrewMember member) {
        ArrayList<String> strings = crew.getTheShip().searchThePlanet(crew, member);
        return strings;
    }

    public static ArrayList<String> useItem(Crew crew, CrewMember member, String item) {
    	ArrayList<String> strings = crew.useItem(member, item);
    	return strings;
    }

    public static ArrayList<String> pilotShip(Crew crew, CrewMember memberOne, CrewMember memberTwo, String planet) {
    	ArrayList<String> strings = new ArrayList<>();
    	if(crew.getCrewWithActionsRemaining().size() >= 2) {

            strings = crew.getTheShip().travelToNewPlanet(memberOne, memberTwo, planet);
            if(!(memberOne.getClassType().equals("Captain"))) {
            	memberOne.performAction();
            }
            if(!(memberOne.getClassType().equals("Captain"))) {
            	memberTwo.performAction();
            }
        } else {
            strings.add("It takes two crew members to pilot ship");
        }
    	
    	return strings;
    }
}
