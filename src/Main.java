import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int presencePlayer = 0, presenceMonster = 0;
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<Creature> list = new ArrayList<>();

    public static ArrayList<Creature> getList() {
        return list;
    }
    // Реализацию опубликуйте в открытом репозитории на github. В readme репозитория оставьте свое имя пользователя в Telegram для связи.
//            Условия:
//            1) В игре есть Существа. К ним относятся Игрок и Монстры.
//            2) У Существа есть параметры Атака и Защита. Это целые числа от 1 до 30.
//            3) У Существа есть Здоровье. Это натуральное число от 0 до N. Если Здоровье становится равным 0,
//            то Существо умирает. Игрок может себя исцелить до 4-х раз на 30% от максимального Здоровья.
//            4) У Существа есть параметр Урон. Это диапазон натуральных чисел M - N. Например, 1-6.
//
//            5) Одно Существо может ударить другое по такому алгоритму:
//            - Рассчитываем Модификатор атаки. Он равен разности Атаки атакующего и Защиты защищающегося плюс 1
//            - Успех определяется броском N кубиков с цифрами от 1 до 6, где N - это Модификатор атаки. Всегда
//            бросается хотя бы один кубик.
//            - Удар считается успешным, если хотя бы на одном из кубиков выпадает 5 или 6
//            - Если удар успешен, то берется произвольное значение из параметра Урон атакующего и вычитается из
//            Здоровья защищающегося.
//


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
                        if(player.health <= 0){
                            list.remove(player);
                            return type;
                        }
                        player.attack(monster);
                        if(monster.health <= 0){
                            list.remove(monster);
                            return type;
                        }

                    }while(monster.health > 0 && player.health > 0);

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