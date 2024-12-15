import processing.core.PImage;

import java.util.ArrayList;

public class ScriptedMonsters {
    public static class TrainingDummy extends Enemy {
        public TrainingDummy() {
            super("Training Dummy",1000,1000,0,0,new ArrayList<>(),null);
        }
    }

    public static class Jeff extends Enemy {

        public Jeff(PImage img) {
            super("Jeff",25,25,0,0, new ArrayList<>(),img);
            Attack stare = new Attack("Stare",null,1,100,25,2);
            this.attacks.add(stare);
            this.img = img;
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
