package ml.bjorn.bow.botsofwar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

    private String id;
    private Coordinates coordinates;
    private String owner;
    private int hp;
    private String name;
    private int rangeOfAttack;
    private int actionPoints;
    private int damage;
    private int cost;
    private boolean entrench;

    public Entity() {}

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRangeOfAttack() {
        return this.rangeOfAttack;
    }

    public void setRangeOfAttack(int rangeOfAttack) {
        this.rangeOfAttack = rangeOfAttack;
    }

    public int getActionPoints() {
        return this.actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isEntrench() {
        return this.entrench;
    }

    public boolean getEntrench() {
        return this.entrench;
    }

    public void setEntrench(boolean entrench) {
        this.entrench = entrench;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            ", owner='" + getOwner() + "'" +
            ", hp='" + getHp() + "'" +
            ", name='" + getName() + "'" +
            ", rangeOfAttack='" + getRangeOfAttack() + "'" +
            ", actionPoints='" + getActionPoints() + "'" +
            ", damage='" + getDamage() + "'" +
            ", cost='" + getCost() + "'" +
            ", entrench='" + isEntrench() + "'" +
            "}";
    }

}