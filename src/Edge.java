import java.awt.*;

public class Edge {
    private int x, y;
    private int width, height;

    private Color color = Color.WHITE;
    private boolean visible = false;
    private boolean free = true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Edge(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void paint() {
        if (!isVisible()) {
            return;
        }
        boolean horizontal = getWidth() > getHeight();
        int midValue = (horizontal ? getHeight() : getWidth()) / 2;
        int alphaStep = free ? 255 / midValue : 0;
        for (int i = 0; i < midValue; i+=1) {
            StdDraw.setPenColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - alphaStep * i));
            if (horizontal) {
                StdDraw.filledRectangle(x + getWidth() / 2.0, y + getHeight() / 2.0 + i, getWidth() / 2.0, i);
                StdDraw.filledRectangle(x + getWidth() / 2.0, y + getHeight() / 2.0 - i, getWidth() / 2.0, i);
            } else {
                StdDraw.filledRectangle(x + getWidth() / 2.0 + i, y + getHeight() / 2.0, i, getHeight() / 2.0);
                StdDraw.filledRectangle(x + getWidth() / 2.0 - i, y + getHeight() / 2.0, i, getHeight() / 2.0);
            }
        }
    }

}
