public class Monster extends Creature {
    Monster(int attack, int protection, int health, int damage_min, int damage_max) {
        super(attack, protection, health,  damage_min, damage_max);
    }
    @Override
    public void attack(Creature creature) {
        //Рассчитываем модификатор атаки
        int modification = (getAttack() - creature.getAttack()) + 1;
        if(modification <= 0) modification = 1;
        //Определение успеха атаки
        for(int i = 0; i < modification; i++){
            int result = Main.getRandom(1,6);

            if(result == 5 || result == 6) {
                int hit = Main.getRandom(getDamageMin(), getDamageMax());
                creature.setHealth(creature.getHealth() - hit);
                System.out.println("Монстр наносит удар с силой " + hit);
                if(creature.getHealth() <= 0){
                    System.out.println("Игрок теряет здоровье. Его здоровье стало " + creature.getHealth());
                    System.out.println("Игрок умер");
                    break;
                } else {
                    System.out.println("Игрок теряет здоровье. Его здоровье стало " + creature.getHealth());
                    if(((Player)creature).getCount() > 0){
                        System.out.println("Желаете исцелить игрока? 1.Да 2.Нет \n Вы можете исцелить еще " + ((Player)creature).getCount() + "раз(а)");

                    }
                    Main.checkInput();
                    switch (Main.sc.nextInt()){
                        case 1 :((Player)creature).healing(); break;
                        case 2: break;
                    }
                }
                break;
            }
        }
    }
    @Override
    void printCreatures(int i) {
         System.out.println(" Монстр " + i + " - Атака: " + getAttack() + " Защита: " + getProtection() + " Здоровье: "
                 + getHealth() + " Урон: [ " + getDamageMin() + " - " + getDamageMax() + " ]\n");

    }


}
