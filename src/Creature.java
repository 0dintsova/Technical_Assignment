abstract public class Creature {
    private int attack;
    private int protection;
    private int healthFinal;
    private int health;
    private int damageMin;
    private int damageMax;

    public int getAttack() {
        return attack;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthFinal() {
        return healthFinal;
    }

    public int getProtection() {
        return protection;
    }

    abstract void attack(Creature creature);
    abstract void printCreatures(int i);
    Creature(int attack, int protection, int healthFinal, int damage_min, int damage_max){
        this.attack = attack;
        this.damageMin = damage_min;
        this.damageMax = damage_max;
        this.healthFinal = healthFinal;
        this.health = healthFinal;
        this.protection = protection;
    }
}
