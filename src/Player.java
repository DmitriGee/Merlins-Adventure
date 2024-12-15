import java.util.ArrayList;
import processing.core.PImage;

public class Player {
    int hitPoints, powerPoints;
    int maxHitPoints, maxPowerPoints;
    int gold;
    ArrayList<Attack> attacks = null;
    PImage img = null;

    public Player(int hitPoints, int powerPoints, int maxHitPoints, int maxPowerPoints, int gold) {
        this.hitPoints = hitPoints;
        this.powerPoints = powerPoints;
        this.maxHitPoints = maxHitPoints;
        this.maxPowerPoints = maxPowerPoints;
        this.gold = gold;
        this.attacks = new ArrayList<>();
    }
}
