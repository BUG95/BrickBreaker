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

    public void test(Graphics g, String text, int x, int xOffset, int y, int height, Color color, Font font){
        g.setColor(color);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);

        int width = fm.stringWidth(text);
        int xPos = x + xOffset + (width - fm.stringWidth(text)) / 2;
        int yPos = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.drawString(text, xPos, yPos);
    }

}
