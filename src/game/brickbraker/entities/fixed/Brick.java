package game.brickbraker.entities.fixed;

import game.brickbraker.Game;
import game.brickbraker.gfx.Assets;

import java.awt.*;

public class Brick extends FixedEntity {
    public static final float BRICK_WIDTH = 30, BRICK_HEIGHT = 15;
    private boolean isHit = false;
    private int level;

    public Brick(Game game, float x, float y, int level) {
        super(game, x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.level = level;
    }

    @Override
    public void tick() {
        if(isHit){
            level--;
            isHit = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().getBrickLevelByGameLevel(game.getGameState().getLevel(), level), (int)x, (int)y, (int)BRICK_WIDTH, (int)BRICK_HEIGHT, null);
    }

    public boolean getHit(){
        return isHit;
    }

    public void setHit(boolean hit){
        isHit = hit;
    }

    public int getLevel(){
        return level;
    }
}
