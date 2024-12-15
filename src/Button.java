import processing.core.PApplet;

public class Button {
    String label = "Unset";
    int x, y, w, h;
    int foreground, background;
    public Button(String label, int x, int y, int w, int h, int foreground, int background) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.foreground = foreground;
        this.background = background;
    }
    public boolean isPressed(int mouseX, int mouseY, boolean mousePressed) {
        return isHover(mouseX, mouseY) && mousePressed;
    }
    public boolean isHover(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }
    public void draw(PApplet applet) {
        applet.fill(background);
        applet.rect(this.x, this.y, this.w, this.h);
        applet.fill(foreground);
        applet.textAlign(PApplet.CENTER, PApplet.CENTER);
        applet.text(this.label, this.x + this.w / 2, this.y + this.h / 2);
    }
}
