public class Engineer extends CrewMember{

    private int health = 20;
    private String searchingSkill = "Good";

    public Engineer() {
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
