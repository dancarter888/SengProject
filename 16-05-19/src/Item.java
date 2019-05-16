public interface Item {

    int getCost();
    String getName();
    String getDescription();
    String useItem(CrewMember member);
    String toString();
}
