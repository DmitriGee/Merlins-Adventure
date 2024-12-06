import java.util.ArrayList;

public class Enemy {
    public int hitPoints, maxHitPoints, powerPoints, maxPowerPoints;
    public ArrayList<Attack> attacks;

    public int act() {
        return 0;
    }

    public Attack attack() {
        return null;
    }
}
