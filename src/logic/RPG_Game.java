package logic;

import players.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber;

    public static void startGame() {
        Boss boss = new Boss(1000, 50, "Dragon");
        Warrior warrior1 = new Warrior(280, 20, "Arthur");
        Warrior warrior2 = new Warrior(270, 15, "Alex");
        Magic magic = new Magic(290, 20, "Merlin");
        Medic doc = new Medic(250, 5, "Aibolit", 15);
        Berserk berserk = new Berserk(260, 10, "Gendalf");
        Medic assistant = new Medic(300, 5, "Nurse", 5);
        Witcher witcher = new Witcher(300, 0, "Geralt");
        Hero[] heroes = {warrior1, warrior2, magic, doc, berserk, assistant, witcher};

        printStatistics(boss, heroes);
        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        boss.attack(heroes);
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && boss.getHealth() > 0 &&
                    boss.getDefence() != hero.getAbility()) {
                hero.attack(boss);
                hero.applySuperAbility(boss, heroes);
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND " + roundNumber + " --------------");
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}