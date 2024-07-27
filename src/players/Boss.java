package players;

import logic.RPG_Game;

public class Boss extends GameEntity {
    private SuperAbility defence;

    public Boss(int health, int damage, String name) {
        super(health, damage, name);
    }

    public void chooseDefence() {
        SuperAbility[] values = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(values.length);
        this.defence = values[randomIndex];
    }

    public void attack(Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                if (heroes[i] instanceof Berserk &&
                        this.defence != SuperAbility.BLOCK_DAMAGE_AND_REVERT) {
                    int blocked = 5 * (RPG_Game.random.nextInt(2) + 1); // 5 or 10
                    ((Berserk) heroes[i]).setBlockedDamage(blocked);
                    heroes[i].setHealth(heroes[i].getHealth()
                            - (this.getDamage() - blocked));
                } else {
                    heroes[i].setHealth(heroes[i].getHealth() - this.getDamage());
                }
            }
        }
    }

    public SuperAbility getDefence() {
        return defence;
    }

    @Override
    public String toString() {
        return "BOSS " + super.toString() + " defence: " + this.defence;
    }
}
