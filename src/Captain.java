//sadsadasd
public class Captain extends CrewMember{

    private int health = 50;
    private String searchingSkill = "Bad";

    public Captain() {
        super();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getSearchingSkill() {
        return searchingSkill;
    }

    public void setSearchingSkill(String searchingSkill) {
        this.searchingSkill = searchingSkill;
    }
}
