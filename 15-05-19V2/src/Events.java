import java.util.Random;

public class Events {

    public static String alienPirates(Crew crew) {
        Random rand = new Random();
        int randItemIndex = rand.nextInt(crew.getOwnedItems().size());
        Item removedItem = crew.getOwnedItems().get(randItemIndex);
        crew.removeOwnedItems(randItemIndex);
        String text = ("Alien Pirates have invaded and stolen your " + removedItem.getName());
        
        return text;
    }

    public static String spacePlague(Crew crew) {
        Random rand = new Random();
        int numToInfect = rand.nextInt(crew.getCrewSize()+1);
        String text = "";
        
        for(int i=0; i<numToInfect; i++) {
            // gets the crew member to be infected
            int memberInfectedIndex = rand.nextInt(crew.getCrewSize());
            CrewMember memberInfected = crew.getCrewMemberList().get(memberInfectedIndex);
            if(!(crew.getCrewWithSpacePlague().contains(memberInfected))) {
                memberInfected.setHasPlague(true);
                crew.addCrewWithSpacePlague(memberInfected);
                text += (memberInfected.getName() + " has been infected with Space Plague\n");
            }
        }
        
        return text;
    }





}
