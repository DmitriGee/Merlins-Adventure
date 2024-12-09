import java.util.ArrayList;

public class Enemy {
    String name;
    int hitPoints, maxHitPoints, powerPoints, maxPowerPoints;
    ArrayList<Attack> attacks;

    public int act() {
        return 0;
    }

    public Attack attack() {
        return null;
    }
}
