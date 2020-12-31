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
            xPos = x + xOffsetFontMetrics.stringWidth(xOffsetText); //+ (width - fm.stringWidth(text)) / 2;
        } else {
           // xPos = x + (width - fm.stringWidth(text)) / 2;
            //xPos = x + fm.stringWidth(text) / 2;
            xPos = x;
        }

        yPos = y + (height - fm.getHeight()) / 2 + fm.getAscent();

        g.drawString(text, xPos, yPos);
    }

    public void drawTextInsideRectangle(Graphics g, String text, int x, int y, Font font, Color color){
        g.setColor(color);

        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);

        Rectangle rectangle = new Rectangle(x, y, fm.stringWidth(text), fm.getHeight() - fm.getAscent()/2);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        int yPos = y + (fm.getHeight() - fm.getAscent() / 2);

        g.setColor(Color.BLACK);
        g.drawString(text, x, yPos);
    }

    public FontMetrics getFontMetricsOf(Font font){
        Canvas c = new Canvas();
        return c.getFontMetrics(font);
    }

}
