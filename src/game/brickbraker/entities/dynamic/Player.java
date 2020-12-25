package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;

import java.awt.*;

public class Player extends DynamicEntity {

    public static final float PLAYER_WIDTH = 60, PLAYER_HEIGHT = 10;
    private static final int PLAYER_SPEED = 5;

    public Player(Game game, float x, float y) {
        super(game, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public void move() {
        if(game.getKeyManager().leftArrow){
            if(x - PLAYER_SPEED < 0) return;
            x -= PLAYER_SPEED;
        } else if(game.getKeyManager().rightArrow){
            if(x >= game.getDisplay().getCanvas().getWidth() - PLAYER_WIDTH) return;
            x += PLAYER_SPEED;
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}
