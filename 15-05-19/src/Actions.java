import java.util.ArrayList;
import java.util.Scanner;

public class Actions {

    public static void sleep(CrewMember member) {
        member.sleep();
    }

    public static void repairShip(Crew crew, CrewMember member) {
        crew.getTheShip().repairShields(member);
    }

    public static void searchPlanet(Crew crew, CrewMember member) {
        crew.getTheShip().searchThePlanet(crew, member);
    }

    public static void useItem(Crew crew, CrewMember member) {
        crew.useItem(member);
    }

    public static void pilotShip(Crew crew) {
        if(crew.getCrewWithActionsRemaining().size() >= 2) {
            //asteroidBelt();
            CrewMember memberOne  = chooseCrewMember(crew);
            CrewMember memberTwo  = chooseCrewMember(crew, memberOne);

            crew.getTheShip().travelToNewPlanet(memberOne, memberTwo);
            memberOne.performAction();
            memberTwo.performAction();
        } else {
            System.out.println("It takes two crew members to pilot ship");
        }
    }

    public static CrewMember chooseCrewMember(Crew crew) {
        Scanner scanner = new Scanner(System.in);
        String members = "Which crew member to perform this action: \n";
        ArrayList<CrewMember> memberList = crew.getCrewWithActionsRemaining();
        for (int i = 1; i < memberList.size() + 1; i++) {
            members += String.format("%d. %s\n", i, memberList.get(i - 1).getName());
        }
        System.out.println(members);
        int index = scanner.nextInt();

        //scanner.close();
        return memberList.get(index - 1);
    }

    public static CrewMember chooseCrewMember(Crew crew, CrewMember alreadyChosen) { //DUPLICATE can be changed
        Scanner scanner = new Scanner(System.in);
        String members = "Which other crew member to perform this action: \n";
        ArrayList<CrewMember> memberList = (ArrayList<CrewMember>) crew.getCrewWithActionsRemaining().clone();
        memberList.remove(alreadyChosen);
        for (int i = 1; i < memberList.size() + 1; i++) {
            if (memberList.get(i - 1) != alreadyChosen){
                members += String.format("%d. %s\n", i, memberList.get(i - 1).getName());
            }
        }
        System.out.println(members);
        int index = scanner.nextInt();

        //scanner.close();
        return memberList.get(index - 1);
    }


}
