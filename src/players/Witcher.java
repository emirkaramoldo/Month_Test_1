package players;

import java.util.Random;

public class Witcher extends Hero {
    private int blockedDamage;

    public Witcher(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.REVIVAl);
    }

    @Override
    public void applySuperAbility(Boss boss, Hero[] heroes) {
        Random random = new Random();
        if (random.nextBoolean() && this.getHealth() > 0) {
            for (Hero hero : heroes) {
                if (hero.getHealth() <= 0 && hero != this) {
                    hero.setHealth(this.getHealth());
                    this.setHealth(0);
                    System.out.println("Witcher " + this.getName() + " revived " + hero.getName() + " at the cost of his own life.");
                    break;
                }
            }
        }
    }
}
