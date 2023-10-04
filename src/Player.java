public class Player extends Creature {
    int count = 4;
    Player(int attack, int protection, int health, int damage_min, int damage_max){
        super(attack, protection, health, damage_min, damage_max);
    }
    void healing(){
        // проверка сколько раз исцелялился игрок
        if(count != 0){
            health += (int) (health_final * 0.3);
            count--;
            System.out.println("Игрок исцелился. Его здоровье стало " + health);
        }else System.out.println("Вам не доступно исцеление!");
    }
    // 4 6 3 4 6
    // 6 3 1 1 3
    public void attack(Creature creature) {
        //Рассчитываем модификатор атаки
        int modification = (attack - creature.attack) + 1;
        if(modification <= 0) modification =1;
        int result;
        //Определение успеха атаки
        for(int i = 0; i < modification; i++){
            result = Main.getRandom(1,6);

            if(result == 5 || result == 6) {
                int hit = Main.getRandom(damage_min, damage_max);
                creature.health -= hit;
                System.out.println("Игрок наносит удар с силой " + hit);

                if(creature.health <= 0){
                    System.out.println("Монстр теряет здоровье. Его здоровье стало " + creature.health);
                    System.out.println("Монстр умер");
                }else System.out.println("Монстр теряет здоровье. Его здоровье стало " + creature.health);
                break;
            }
        }
    }

    @Override
    void printCreatures(int i) {
         System.out.println( " Игрок " + i + " - Атака: " + attack + " Защита: " + protection + " Здоровье: " + health +
                 " Урон: [ " + damage_min + " - " + damage_max + " ]");
    }


}
