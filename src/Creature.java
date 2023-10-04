abstract public class Creature {
    int attack;
    int protection;
    int health_final;
    int health;
    int damage_min;
    int damage_max;


    abstract void attack(Creature creature);
    abstract void printCreatures(int i);
    Creature(int attack, int protection, int health_final, int damage_min, int damage_max){
        this.attack = attack;
        this.damage_min = damage_min;
        this.damage_max = damage_max;
        this.health_final = health_final;
        this.health = health_final;
        this.protection = protection;
    }
}
