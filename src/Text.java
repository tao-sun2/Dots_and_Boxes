import java.awt.*;

public class Text {
    private Font font;
    private String message;
    private Color color;
    private int x;
    private int y;

    public Text(Font font, String message, Color color) {
        this.font = font;
        this.message = message;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Text(Font font, String message, Color color, int x, int y) {
        this.font = font;
        this.message = message;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void paint(){
        StdDraw.setFont(font);
        StdDraw.setPenColor(color);
        StdDraw.text(x,y,message);

    }
}
