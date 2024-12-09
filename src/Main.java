import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    int windowWidth = 1000;
    int windowHeight = 720;

    int enemyWidth = 50;
    int enemyHeight = 100;

    Player player = null;
    ArrayList<Enemy> activeEnemies = new ArrayList<>();

    final int UI_MAIN = 0;
    final int UI_FIGHT = 1;
    final int UI_ACT = 2;
    final int UI_ITEM = 3;
    final int UI_RUN = 4;
    final int UI_FIGHT_WHO = 5;

    int UIMenu = UI_MAIN;
    Attack UISelectedAttack = null;
    Button BFight = new Button("Fight", windowWidth-400, windowHeight-100, 190, 30, 0, 205);
    Button BAct = new Button("Act", windowWidth-200, windowHeight-100, 190, 30, 0, 205);
    Button BItem = new Button("Item", windowWidth-400, windowHeight-50, 190, 30, 0, 205);
    Button BRun = new Button("Run", windowWidth-200, windowHeight-50, 190, 30, 0, 205);
    Button BBack = new Button("Back", windowWidth-400, windowHeight-100, 190, 30, 0, 205);
    ArrayList<Button> LBAttacks = new ArrayList<>();
    ArrayList<Button> LBEnemies = new ArrayList<>();


    public void settings() {
        size(windowWidth,windowHeight);
    }
    public void setup() {
        frameRate(60);
        player = new Player(17,6,40,10,0);
        player.attacks.add(new Attack("Cast Lightning", "Cast a bolt of lightning.", 2, 95, 5, 15));
        player.attacks.add(new Attack("Cast Fireball", "Cast a flaming fireball.", 5, 15, 10, 1));

        Enemy jeff = new ScriptedMonsters.Jeff();
        activeEnemies.add(jeff);
    }
    public void draw() {
        background(255);
        noStroke();
        drawField();
        drawUI();
    }
    public void drawField() {
        fill(color(0,255,0));
        rect(200,250,50,100); // Player Placeholder

        fill(color(255,0,0));
        for (int i = 0; i < activeEnemies.size(); i++) {
            if (activeEnemies.get(i) != null) {
                if (i == 0) { // Middle Position
                    rect(windowWidth-250,250,50,100);
                } else if (i == 1) { // Top Position
                    rect(windowWidth-250-35,250-115,50-10,100-20);
                } else if (i == 2) { // Bottom Position
                    rect(windowWidth-250+45,250+125,50+10,100+20);
                }
            }
        }
    }

    public void drawUI() {
        fill(color(128));
        rect(0,windowHeight-120,windowWidth,120);

        textAlign(CENTER, CENTER);
        textSize(16);
        fill(color(0));
        text("HP",200,windowHeight-120+20);
        drawProgressBar(
                player.hitPoints,player.maxHitPoints,
                color(255,0,0),color(0),
                200+20,windowHeight-120+20-6,80,16,
                2
        );
        fill(255);
        textSize(12);
        text(player.hitPoints+"/"+player.maxHitPoints, 200+20+40,windowHeight-120+20);

        textSize(16);
        fill(color(0));
        text("PP",200,windowHeight-120+40);
        drawProgressBar(
                player.powerPoints,player.maxPowerPoints,
                color(0,0,255),color(0),
                200+20,windowHeight-120+40-6,80,16,
                2
        );
        fill(255);
        textSize(12);
        text(player.powerPoints+"/"+player.maxPowerPoints, 200+20+40,windowHeight-120+40);

        switch (UIMenu) {
            case UI_MAIN: {
                BFight.background = 205;
                BAct.background = 205;
                BItem.background = 205;
                BRun.background = 205;
                if (BFight.isHover(mouseX, mouseY)) {
                    BFight.background = 255;
                }
                if (BAct.isHover(mouseX, mouseY)) {
                    BAct.background = 255;
                }
                if (BItem.isHover(mouseX, mouseY)) {
                    BItem.background = 255;
                }
                if (BRun.isHover(mouseX, mouseY)) {
                    BRun.background = 255;
                }
                textSize(16);
                BFight.draw(this);
                BAct.draw(this);
                BItem.draw(this);
                BRun.draw(this);
                if (BFight.isPressed(mouseX,mouseY,mousePressed)) {
                    UIMenu = UI_FIGHT;
                }
                else if (BAct.isPressed(mouseX,mouseY,mousePressed)) {
                    UIMenu = UI_ACT;
                }
                else if (BItem.isPressed(mouseX,mouseY,mousePressed)) {
                    UIMenu = UI_ITEM;
                }
                else if (BRun.isPressed(mouseX,mouseY,mousePressed)) {
                    UIMenu = UI_RUN;
                }
            }
            case UI_FIGHT: {
                if (LBAttacks.isEmpty()) {
                    for (int i = 0; i < player.attacks.size(); i++) {
                        Attack a = player.attacks.get(i);
                        LBAttacks.add(new Button(a.name, 0,0,0,0,0,0));
                    }
                }
                int xp = 2, yp = 1;
                BBack.draw(this);
                if (BBack.isPressed(mouseX,mouseY,mousePressed)) {
                    LBAttacks.clear();
                    UIMenu = UI_MAIN;
                    break;
                }
                for (int i = 0; i < player.attacks.size(); i++) {
                    Attack a = player.attacks.get(i);
                    Button button = LBAttacks.get(i);
                    button.w = 190;
                    button.h = 30;
                    button.x = windowWidth-(200*xp);
                    button.y = windowHeight-(50*yp);
                    xp++;
                    if (xp > 2) {
                        xp = 1;
                        yp++;
                    }
                    if (button.isPressed(mouseX,mouseY,mousePressed)) {
                        UISelectedAttack = a;
                        UIMenu = UI_FIGHT_WHO;
                        break;
                    }
                }
            }
            case UI_FIGHT_WHO: {
                if (LBEnemies.isEmpty()) {
                    for (int i = 0; i < activeEnemies.size(); i++) {
                        Attack a = player.attacks.get(i);
                        LBEnemies.add(new Button(a.name, 0,0,0,0,0,0));
                    }
                }
                int xp = 2, yp = 1;
                BBack.draw(this);
                if (BBack.isPressed(mouseX,mouseY,mousePressed)) {
                    LBEnemies.clear();
                    UIMenu = UI_FIGHT;
                    break;
                }
                for (int i = 0; i < activeEnemies.size(); i++) {
                    Enemy e = activeEnemies.get(i);
                    Button button = LBEnemies.get(i);
                    button.w = 190;
                    button.h = 30;
                    button.x = windowWidth-(200*xp);
                    button.y = windowHeight-(50*yp);
                    xp++;
                    if (xp > 2) {
                        xp = 1;
                        yp++;
                    }
                    if (button.isPressed(mouseX,mouseY,mousePressed)) {
                        // TODO: Add attacking enemies code here
                    }
                }
            }

            default:
                UIMenu = UI_MAIN;
        }
    }

    public void drawProgressBar(int current, int max, int foreground, int background, int x, int y, int w, int h, int padding) {
        fill(background);
        rect(x,y,w,h);
        fill(foreground);
        rect(x+padding,y+padding,(float)(w-2*padding)/max*current,h-2*padding);
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}