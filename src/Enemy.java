import processing.core.PImage;

import java.util.ArrayList;

public class Enemy {
    String name;
    int hitPoints, maxHitPoints, powerPoints, maxPowerPoints;
    ArrayList<Attack> attacks;
    PImage img;

    public Enemy(String name, int hitPoints, int maxHitPoints, int powerPoints, int maxPowerPoints, ArrayList<Attack> attacks, PImage img) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.powerPoints = powerPoints;
        this.maxPowerPoints = maxPowerPoints;
        this.attacks = attacks;
        this.img = img;
    }

    public int act() {
        return 0;
    }

    public Attack attack() {
        return null;
    }
}
