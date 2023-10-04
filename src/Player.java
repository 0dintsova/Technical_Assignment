public class Player extends Creature {
    private int count = 4;

    public int getCount(){
        return count;
    }
    Player(int attack, int protection, int health, int damage_min, int damage_max){
        super(attack, protection, health, damage_min, damage_max);
    }
    void healing(){
        // проверка сколько раз исцелялился игрок
        if(count != 0){
            setHealth(getHealth()+ (int) (getHealthFinal() * 0.3));
            count--;
            System.out.println("Игрок исцелился. Его здоровье стало " + getHealth());
        }else System.out.println("Вам не доступно исцеление!");
    }
    public void attack(Creature creature) {
        //Рассчитываем модификатор атаки
        int modification = (getAttack() - creature.getAttack()) + 1;
        if(modification <= 0) modification =1;
        int result;
        //Определение успеха атаки
        for(int i = 0; i < modification; i++){
            result = Main.getRandom(1,6);

            if(result == 5 || result == 6) {
                int hit = Main.getRandom(getDamageMin(),getDamageMax());
                creature.setHealth(getHealth() - hit);
                System.out.println("Игрок наносит удар с силой " + hit);

                if(creature.getHealth() <= 0){
                    System.out.println("Монстр теряет здоровье. Его здоровье стало " + creature.getHealth());
                    System.out.println("Монстр умер");
                }else System.out.println("Монстр теряет здоровье. Его здоровье стало " + creature.getHealth());
                break;
            }
        }
    }

    @Override
    void printCreatures(int i) {
         System.out.println( " Игрок " + i + " - Атака: " + getAttack() + " Защита: " + getProtection() + " Здоровье: " + getHealth() +
                 " Урон: [ " + getDamageMin() + " - " + getDamageMax() + " ]");
    }


}
