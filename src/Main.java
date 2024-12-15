import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    int windowWidth = 1000;
    int windowHeight = 720;

    Player player = null;
    ArrayList<Enemy> activeEnemies = new ArrayList<>();

    final int UI_MAIN = 0;
    final int UI_FIGHT = 1;
    final int UI_ACT = 2;
    final int UI_ITEM = 3;
    final int UI_RUN = 4;
    final int UI_FIGHT_WHO = 5;
    final int UI_FIGHT_RESULT = 6;

    int UIMenu = UI_MAIN;
    Attack UISelectedAttack = null;
    Enemy UISelectedEnemy = null;
    Attack.Return AttackReturn = null;
    int UIAttackReturnStage = 0;
    Enemy remove = null;
    Button BFight = new Button("Fight", windowWidth-400, windowHeight-100, 190, 30, 0, 205);
    Button BAct = new Button("Act", windowWidth-200, windowHeight-100, 190, 30, 0, 205);
    Button BItem = new Button("Item", windowWidth-400, windowHeight-50, 190, 30, 0, 205);
    Button BRun = new Button("Run", windowWidth-200, windowHeight-50, 190, 30, 0, 205);
    Button BBack = new Button("Back", windowWidth-400, windowHeight-100, 190, 30, 0, 205);
    Button CBAttack = new Button("/!\\",0,0,0,0,0,205);
    Button CBWho = new Button("/!\\",0,0,0,0,0,205);
    boolean mouseDown = false;
    boolean canClick = true;


    public void settings() {
        size(windowWidth,windowHeight);
    }
    public void setup() {
        frameRate(60);
        player = new Player(40,10,40,10,0);
        player.img = loadImage("img/Merlin.png");
        player.attacks.add(new Attack("[DEV]Miss", "[DevTools] Guaranteed miss", 4, -1, 4, -1));
        player.attacks.add(new Attack("[DEV]Hit", "[DevTools] Guaranteed hit", 4, 100, 4, -1));
        player.attacks.add(new Attack("[DEV]Critical", "[DevTools] Guaranteed critical hit", 4, 100, 8, 100));

        Enemy jeff = new ScriptedMonsters.Jeff(loadImage("img/Dummy.png"));
        Enemy jeff2 = new ScriptedMonsters.Jeff(loadImage("img/Dummy.png"));
        Enemy jeff3 = new ScriptedMonsters.Jeff(loadImage("img/Dummy.png"));
        activeEnemies.add(jeff);
        activeEnemies.add(jeff2);
        activeEnemies.add(jeff3);
    }
    public void draw() {
        background(255);
        noStroke();
        drawField();
        drawUI();
        textSize(30);
        textAlign(CENTER, CENTER);
        fill(0);
        if (activeEnemies.isEmpty()) {
            text("YOU WON THE DEMO!", (int) (windowWidth / 2), (int) (windowHeight / 2));
        }
        textSize(26);
        textAlign(LEFT,TOP);
        text("PROOF OF CONCEPT - Features Missing and/or Bugs Not Squashed!", 0,0);
    }
    public void drawField() {
        fill(color(0,255,0));
        rect(200,250,50,100); // Player Placeholder
        copy(player.img, 0,0,50,100,200,250,50,100);

        fill(color(255,0,0));
        for (int i = 0; i < activeEnemies.size(); i++) {
            Enemy e = activeEnemies.get(i);
            if (e != null) {
                if (i == 0) { // Middle Position
                    rect(windowWidth-250,250,50,100);
                    copy(e.img, 0,0,50,100,windowWidth-250,250,50,100);
                    drawProgressBar(e.hitPoints,e.maxHitPoints,color(255,0,0),0,windowWidth-250,250,60,10,2);
                } else if (i == 1) { // Top Position
                    rect(windowWidth-250-35,250-115,50-10,100-20);
                    copy(e.img, 0,0,50,100,windowWidth-250-35,250-115,50-10,100-20);
                    drawProgressBar(e.hitPoints,e.maxHitPoints,color(255,0,0),0,windowWidth-250-35,250-115,60,10,2);
                } else if (i == 2) { // Bottom Position
                    rect(windowWidth-250+45,250+125,50+10,100+20);
                    copy(e.img, 0,0,50,100,windowWidth-250+45,250+125,50+10,100+20);
                    drawProgressBar(e.hitPoints,e.maxHitPoints,color(255,0,0),0,windowWidth-250+45,250+125,60,10,2);
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

        canClick = !mouseDown;
        mouseDown = mousePressed;
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

                if (canClick) {
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
            }
                break;
            case UI_FIGHT: {
                int xp = 2, yp = 1;
                textSize(16);
                BBack.draw(this);
                if (BBack.isHover(mouseX,mouseY)) {
                    BBack.background = 255;
                } else {
                    BBack.background = 205;
                }
                if (canClick) {
                    if (BBack.isPressed(mouseX, mouseY, mousePressed)) {
                        UIMenu = UI_MAIN;
                        break;
                    }
                }
                for (Attack a: player.attacks) {
                    CBAttack.label = a.name;
                    CBAttack.w = 190;
                    CBAttack.h = 30;
                    CBAttack.x = windowWidth-600+(200*xp);
                    CBAttack.y = windowHeight-150+(50*yp);
                    xp++;
                    if (xp > 2) {
                        xp = 1;
                        yp++;
                    }
                    if (CBAttack.isHover(mouseX, mouseY)) {
                        CBAttack.background = 255;
                        if (a.description != null) {
                            textAlign(CENTER, CENTER);
                            textSize(10);
                            fill(0);
                            text(a.description, windowWidth-210, windowHeight-60);
                        }
                    } else {
                        CBAttack.background = 205;
                    }
                    textSize(16);
                    CBAttack.draw(this);
                    if (canClick) {
                        if (CBAttack.isPressed(mouseX,mouseY,mousePressed)) {
                            UISelectedAttack = a;
                            UIMenu = UI_FIGHT_WHO;
                            break;
                        }
                    }
                }
            }
                break;
            case UI_FIGHT_WHO: {
                int xp = 2, yp = 1;
                textSize(16);
                if (BBack.isHover(mouseX, mouseY)) {
                    BBack.background = 255;
                } else {
                    BBack.background = 205;
                }
                BBack.draw(this);
                if (BBack.isPressed(mouseX,mouseY,mousePressed)) {
                    UIMenu = UI_FIGHT;
                    break;
                }
                for (Enemy e : activeEnemies) {
                    CBWho.label = e.name;
                    CBWho.w = 190;
                    CBWho.h = 30;
                    CBWho.x = windowWidth-600+(200*xp);
                    CBWho.y = windowHeight-150+(50*yp);
                    xp++;
                    if (xp > 2) {
                        xp = 1;
                        yp++;
                    }
                    if (CBWho.isHover(mouseX, mouseY)) {
                        CBWho.background = 255;
                    } else {
                        CBWho.background = 205;
                    }
                    textSize(16);
                    CBWho.draw(this);
                    if (canClick) {
                        if (CBWho.isPressed(mouseX,mouseY,mousePressed)) {
                            UISelectedEnemy = e;
                            UIMenu = UI_FIGHT_RESULT;
                            AttackReturn = UISelectedAttack.roll();
                            UISelectedEnemy.hitPoints -= AttackReturn.damage;
                            if (UISelectedEnemy.hitPoints <= 0) {
                                remove = e;
                            }
                            UIAttackReturnStage = 0;
                        }
                    }
                }
                if (remove != null) {
                    activeEnemies.remove(remove);
                    remove = null;
                }
            }
                break;
            case UI_FIGHT_RESULT: {
                textAlign(LEFT, CENTER);
                switch (UIAttackReturnStage) {
                    case 0: {
                        if (AttackReturn.crit) {
                            textSize(24);
                            fill(color(205, 0, 0));
                            float _w = textWidth("CRITICAL!");
                            text("CRITICAL!", 10, windowHeight - 20);
                            textSize(14);
                            fill(0);
                            text("Dealt " + AttackReturn.damage + "HP of damage.", 10 + _w + 5, windowHeight - 20);
                        } else if (AttackReturn.hit) {
                            textSize(14);
                            fill(0);
                            text("Hit! Dealt " + AttackReturn.damage + "HP of damage.", 10, windowHeight - 20);
                        } else {
                            textSize(14);
                            fill(0);
                            text("Miss!", 10, windowHeight - 20);
                        }
                    }
                        break;
                    case 1: {
                        if (UISelectedEnemy.hitPoints <= 0) {
                            textSize(14);
                            fill(0);
                            text(UISelectedEnemy.name+" defeated!", 10, windowHeight - 20);
                        } else {
                            UIAttackReturnStage++;
                        }
                    }
                        break;
                }
                if (mouseDown && canClick) {
                    UIAttackReturnStage++;
                }
                if (UIAttackReturnStage >= 2) {
                    UIMenu = UI_MAIN;
                }
            }
                break;
            default:
                UIMenu = UI_MAIN;
                break;
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