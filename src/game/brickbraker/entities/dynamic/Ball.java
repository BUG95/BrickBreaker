package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;

import java.awt.*;

public class Ball extends DynamicEntity {

    public static final float BALL_WIDTH = 10, BALL_HEIGHT = 10;

    public Ball(Game game, float x, float y) {
        super(game, x, y, BALL_WIDTH, BALL_HEIGHT);
    }

    @Override
    public void move() {
        x = game.getPlayer().getX() + Player.PLAYER_WIDTH / 2 - Ball.BALL_WIDTH / 2;
        y = game.getPlayer().getY() - Player.PLAYER_HEIGHT;
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, (int)BALL_WIDTH, (int)BALL_HEIGHT);
    }
}
