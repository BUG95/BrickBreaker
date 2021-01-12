package game.brickbraker.ui;

import game.brickbraker.gfx.Text;
import game.brickbraker.states.ClickListener;

import java.awt.*;

public class UIText extends UIObject {
    private ClickListener clickListener;
    private String text;
    private Font font;
    private FontMetrics fm;

    public UIText(float x, float y, int width, int height, String text, Font font, ClickListener clickListener) {
        super(x, y, width, height);
        this.clickListener = clickListener;
        this.text = text;
        this.font = font;
        fm = Text.getInstance().getFontMetricsOf(font);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        if(isHovering()){
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.WHITE);
        }
        //g.fillRect((int)x, (int)y, width, height);
        //g.setColor(Color.ORANGE);
        g.drawString(text, (int)x, (int)y + (height - fm.getHeight()) / 2 + fm.getAscent() + fm.getDescent() / 7);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }
}
