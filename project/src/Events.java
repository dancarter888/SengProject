import java.util.Random;

public class Events {

    public static void alienPirates(Crew crew) {
        Random rand = new Random();
        int randItemIndex = rand.nextInt(crew.getOwnedItems().size());
        Item removedItem = crew.getOwnedItems().get(randItemIndex);
        crew.removeOwnedItems(randItemIndex);
        System.out.println("Alien Pirates have invaded and stolen your " + removedItem.getName());
    }

    public static void spacePlague(Crew crew) {
        Random rand = new Random();
        int numToInfect = rand.nextInt(crew.getCrewSize()+1);

        for(int i=0; i<numToInfect; i++) {
            // gets the crew member to be infected
            int memberInfectedIndex = rand.nextInt(crew.getCrewSize());
            CrewMember memberInfected = crew.getCrewMemberList().get(memberInfectedIndex);
            if(!(crew.getCrewWithSpacePlague().contains(memberInfected))) {
                memberInfected.setHasPlague(true);
                crew.addCrewWithSpacePlague(memberInfected);
                System.out.println(memberInfected.getName() + " has been infected with Space Plague");
            }
        }
    }





}
