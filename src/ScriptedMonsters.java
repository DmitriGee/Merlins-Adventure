import java.util.ArrayList;

public class ScriptedMonsters {
    public static class TrainingDummy extends Enemy {
        String name = "Training Dummy";
        int maxHitPoints = 1000;
        int hitPoints = maxHitPoints;
        int maxPowerPoints = 0;
        int powerPoints = maxPowerPoints;
        public TrainingDummy() {}
    }
    public static class Jeff extends TrainingDummy {
        int maxHitPoints = 25;
        int hitPoints = maxHitPoints;
        int maxPowerPoints = 0;
        int powerPoints = maxPowerPoints;
        ArrayList<Attack> attacks = null;

        public Jeff() {
            this.attacks = new ArrayList<>();
            Attack stare = new Attack("Stare",null,1,100,25,2);
            this.attacks.add(stare);
        }

        @Override
        public int act() {
            int rand = (int)(Math.random()*2);
            if (rand == 0) {
                return Action.PASS;
            } else {
                return Action.ATTACK;
            }
        }

        @Override
        public Attack attack() {
            return attacks.get(0);
        }
    }
}
