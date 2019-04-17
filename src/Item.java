public interface Item {

    int getCost();
    String getName();
    String getDescription();
    void useItem(CrewMember member);

}
