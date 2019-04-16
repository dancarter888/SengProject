public class CrewMember {

    private String name;
    private int hunger;
    private int tiredness;
    private boolean hasPlague;
    private int actionsRemaining;
    private int health;
    private String searchingSkill;

    public CrewMember(int health, String searchingSkill) {
        this.health = health;
        this.searchingSkill = searchingSkill;
    }
    
    public void getStatus(){
    	System.out.println("Hunger is" + this.getHunger());
        System.out.println("Tiredness is" + this.getTiredness());
        System.out.println("Health is" + this.getHealth());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getTiredness() {
        return tiredness;
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
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
    
    public boolean isHasPlague() {
        return hasPlague;
    }

    public void setHasPlague(boolean hasPlague) {
        this.hasPlague = hasPlague;
    }

    public int getActionsRemaining() {
        return actionsRemaining;
    }

    public void setActionsRemaining(int actionsRemaining) {
        this.actionsRemaining = actionsRemaining;
    }
}