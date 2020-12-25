package game.brickbraker.entities.fixed;

import game.brickbraker.Game;

import java.awt.*;

public class Brick extends FixedEntity {
    public static final float BRICK_WIDTH = 30, BRICK_HEIGHT = 30;
    private boolean isHit = false;
    private int level;

    public Brick(Game game, float x, float y, int level) {
        super(game, x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.level = level;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN.darker());
        if(level == 2) g.setColor(Color.ORANGE);
        g.fillRect((int)x, (int)y, (int)BRICK_WIDTH, (int)BRICK_HEIGHT);
    }

    public boolean getHit(){
        return isHit;
    }

    public void setHit(boolean hit){
        isHit = hit;
    }
}
