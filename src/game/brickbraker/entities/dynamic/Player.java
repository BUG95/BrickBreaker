package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;

import java.awt.*;

public class Player extends DynamicEntity {

    public static final float PLAYER_WIDTH = 60, PLAYER_HEIGHT = 10, PLAYER_Y_OFFSET = 20;
    private static final int PLAYER_SPEED = 5;
    private int lives = 3;

    public Player(Game game, float x, float y) {
        super(game, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, true);
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
        if(isActive){
            move();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}
