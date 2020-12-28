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

    public void drawText(Graphics g, String text, int x, String xOffsetText, Font xOffsetFont, int y, int height, Color color, Font font){
        g.setColor(color);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);

        int xPos, yPos;
        int width = fm.stringWidth(text);

        if(xOffsetText != null){
            FontMetrics xOffsetFontMetrics = g.getFontMetrics(xOffsetFont);
            xPos = x + xOffsetFontMetrics.stringWidth(xOffsetText) + (width - fm.stringWidth(text)) / 2;
        } else {
            xPos = x + (width - fm.stringWidth(text)) / 2;
        }

        yPos = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.drawString(text, xPos, yPos);
    }

}
