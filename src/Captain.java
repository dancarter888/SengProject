public class Captain extends CrewMember{

    public Captain() {
        super(50, "Bad");
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
