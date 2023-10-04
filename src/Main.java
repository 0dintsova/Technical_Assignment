import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int presencePlayer = 0, presenceMonster = 0;
    static Scanner sc = new Scanner(System.in);
    private static final ArrayList<Creature> list = new ArrayList<>();

    public static void main(String[] args) {
        int type = 1;
        while (type != 0) {
            type = menu();
        }
    }

    private static int menu() {
        int type = 0;
        System.out.println(" 1. Промотр существ.\n 2. Создать существо.\n 3. Начать бой\n Введите 0, чтобы завершить программу.");
        checkInput();
        type = sc.nextInt();
        switch (type) {
            case 1:
                if (list.isEmpty()) System.out.println("\n Никого нет\n Создайте существ!\n");
                else printCreatures();
                break;
            case 2:
                System.out.println(" 1.Создать игрока\n 2.Создать монстра\nВведите 0, чтобы вернуться назад.\n");
                checkInput();
                int input = sc.nextInt();
                if(input == 1 || input == 2) createCreature(input);
                else if(input!= 0) System.out.println("Неправильный ввод!\n");
                break;
            case 3:
                System.out.println("Бой\n");
                if(presenceMonster >= 1 && presencePlayer >= 1) {
                    //Выбор существ, которые будут бородбся друг с другом.
                    int numSelectedMonster, numSelectedPlayer;
                    System.out.println("Выберите игрока и монстра указав их номер.\n");
                    printCreatures();
                    System.out.println("Введите номер игрока");

                    do{
                        checkInput();
                        numSelectedPlayer = sc.nextInt();
                        if(numSelectedPlayer > presencePlayer || numSelectedPlayer < 1) System.out.println(" Неправильный ввод");
                    }while (numSelectedPlayer > presencePlayer || numSelectedPlayer < 1);


                    System.out.println("Введите номер монстра");
                    do{
                        checkInput();
                        numSelectedMonster = sc.nextInt();
                        if(numSelectedMonster > presenceMonster || numSelectedMonster < 1) System.out.println(" Неправильный ввод");
                    }while (numSelectedMonster > presenceMonster || numSelectedMonster < 1);


                    Creature monster = null, player = null;
                    int countMonsters = 0, countPlayers = 0;

                    for(Creature item : list){
                        if(item instanceof Monster ) {
                            countMonsters ++;
                            if(countMonsters == numSelectedMonster){
                                monster = item;
                                System.out.println("Монстр выбран");
                            }
                        }
                        if(item instanceof Player ){
                            countPlayers ++;
                            if(countPlayers == numSelectedPlayer) {
                                player = item;
                                System.out.println("Игрок выбран");
                            }
                        }
                    }
                    //Бой пока у какого-нибудь существа здоровье станет меньше или равно 0
                    do{
                        monster.attack(player);

                        if(player.getHealth() <= 0){
                            list.remove(player);
                            presencePlayer--;
                            return type;
                        }

                        player.attack(monster);

                        if(monster.getHealth() <= 0){
                            list.remove(monster);
                            presenceMonster--;
                            return type;
                        }

                    }while(monster.getHealth() > 0 && player.getHealth() > 0);

                } else System.out.println("\n Некому биться\n Создайте существ!\n");
                break;
        }
        return type;
    }

    private static void createCreature(int type) {
        int input;

        System.out.println("Введите следующие параметры:  Атака, Защита, Здоровье, Урон\n");

        System.out.println("Введите значение параметра Защита. Число от 1 до 30");
        int attack = checkNum1to30();

        System.out.println("Введите значение параметра Аттака. Число от 1 до 30");
        int protection = checkNum1to30();

        System.out.println("Введите значение параметра Здоровье. Число больше 1");
        do {
            checkInput();
            input = sc.nextInt();
            if (input < 1) System.out.println("Неправильное значение!");
        } while (input < 1);
        int health = input;

        System.out.println("Введите диапазон параметра Урон. Введите 2 числа. Первое число минимальный урон, второе число максимальный урон.");
        checkInput();
        int damage_min = sc.nextInt();
        checkInput();
        int damage_max;
        do {
            damage_max = sc.nextInt();
            if (damage_min > damage_max) System.out.println("Второе значение меньше предыдущего");
        } while (damage_min > damage_max);

        if (type == 1) {
            list.add(new Player(attack, protection, health, damage_min, damage_max));
            System.out.println("\nИгрок создан.");
            presencePlayer++;
        }
        if (type == 2) {
            list.add(new Monster(attack, protection, health, damage_min, damage_max));
            System.out.println("\nМонстр создан.");
            presenceMonster++;
        }
    }

     public static void checkInput() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Неправильный ввод!");
        }
    }
    public static int getRandom(int min, int max) {
        return (int) (Math.random() * (max + 1)) + min;
    }

    private static int checkNum1to30() {
        int input;
        do {
            checkInput();
            input = sc.nextInt();
            if (input < 1 || input > 30) System.out.println("Неправильное значение!");
        } while (input < 1 || input > 30);
        return input;
    }

    private static void printCreatures() {

        System.out.println("Игроки");
        int i = 1;
        for(Creature item : list){
            if (item instanceof Player){
                item.printCreatures(i);
                i++;
            }

        }

        System.out.println("\nМонстры");
        i = 1;
        for(Creature item : list){
            if (item instanceof Monster){
                item.printCreatures(i);
                i++;
            }
        }

    }


}