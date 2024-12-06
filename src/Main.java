import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends PApplet {
    int windowWidth = 1000;
    int windowHeight = 720;

    int enemyWidth = 50;
    int enemyHeight = 100;

    Player player = null;
    ArrayList<Enemy> activeEnemies = new ArrayList<>();


    public void settings() {
        size(windowWidth,windowHeight);
    }
    public void setup() {
        frameRate(60);
        player = new Player(17,6,40,10,0);

        Enemy trainingDummy = new ScriptedMonsters.TrainingDummy();
        activeEnemies.add(trainingDummy);
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
                    rect(windowWidth-250-35,250-115,50-10,100-10);
                } else if (i == 2) { // Bottom Position
                    rect(windowWidth-250+45,250+125,50+10,100+10);
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