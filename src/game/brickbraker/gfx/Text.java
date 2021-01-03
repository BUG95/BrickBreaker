package game.brickbraker.gfx;

import java.awt.*;

public class Text {
    private static Text text = null;
    private Text(){}
    public static Text getInstance(){
        if(text == null){
            text = new Text();
        }
        return text;
    }

    public void drawText(Graphics g, String text, int x, int y, int height, Color color, Font font){
        g.setColor(color);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int xPos, yPos;

        xPos = x;
        yPos = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.drawString(text, xPos, yPos);
    }

    public FontMetrics getFontMetricsOf(Font font){
        Canvas c = new Canvas();
        return c.getFontMetrics(font);
    }

}
