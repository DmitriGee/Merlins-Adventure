public class Player {
    public int hitPoints, powerPoints;
    public int maxHitPoints, maxPowerPoints;
    public int gold;

    public Player(int hitPoints, int powerPoints, int maxHitPoints, int maxPowerPoints, int gold) {
        this.hitPoints = hitPoints;
        this.powerPoints = powerPoints;
        this.maxHitPoints = maxHitPoints;
        this.maxPowerPoints = maxPowerPoints;
        this.gold = gold;
    }
}
