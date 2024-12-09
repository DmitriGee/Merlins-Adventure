public class Attack {

    public static class Return {
        boolean hit, crit;
        int damage;
        public Return(boolean hit, boolean crit, int damage) {
            this.hit = hit;
            this.crit = crit;
            this.damage = damage;
        }
    }

    public String name;
    public String description;
    public int damage, chance;
    public int critDamage, critChance;

    public Attack(String name, String description, int damage, int chance, int critDamage, int critChance) {
        this.name = name;
        this.description = description;
        this.damage = damage;
        this.chance = chance;
        this.critDamage = critDamage;
        this.critChance = critChance;
    }

    public Attack.Return roll() {
        int attackRoll = (int)(Math.random()*100.0);
        if (attackRoll > this.chance) {
            // Miss
            return new Attack.Return(false, false, 0);
        } else {
            int critRoll = (int)(Math.random()*100.0);
            if (critRoll > this.critChance) {
                // Hit
                return new Return(true, false, this.damage);
            } else {
                // Critical Hit
                return new Return(true,true,this.critDamage);
            }
        }
    }
}
