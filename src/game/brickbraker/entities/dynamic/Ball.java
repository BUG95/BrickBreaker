package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.fixed.Brick;

import java.awt.*;

public class Ball extends DynamicEntity {

    public static final float BALL_WIDTH = 10, BALL_HEIGHT = 10;
    private final int BALL_SPEED = 5;
    private boolean isFree = false;
    private boolean leftDirection, rightDirection, upDirection, downDirection;

    public Ball(Game game, float x, float y) {
        super(game, x, y, BALL_WIDTH, BALL_HEIGHT, false);
        leftDirection = false;
        rightDirection = false;
        upDirection = true;
        downDirection = false;
    }

    private void resetDirections(){
        leftDirection = false;
        rightDirection = false;
        upDirection = false;
        downDirection = false;
    }

    private void checkCollisions(){
        if (checkCollisionWith(game.getPlayer())){
            resetDirections();
            upDirection = true;
            float a, b;
            a = x + width / 2;
            b = game.getPlayer().getX() + Player.PLAYER_WIDTH / 2;

            if(a > b){
                rightDirection = true;
            } else if(a < b){
                leftDirection = true;
            }

            return;
        }

        for(Brick b : game.getMap().getBrickManager().getBricks())
            if(checkCollisionWith(b)){
                b.setHit(true);
                if(downDirection){
                    downDirection = false;
                    upDirection = true;
                    return;
                }
                upDirection = false;
                downDirection = true;
                return;
            }
    }


    @Override
    public void move() {
        checkCollisions();
        moveUp();
        moveDown();
        moveLeft();
        moveRight();
    }

    private void moveUp(){
        if(upDirection) {
            y -= BALL_SPEED;
            if(y < 0){
                upDirection = false;
                downDirection = true;
            }
        }
    }

    private void moveDown(){
        if(downDirection) {
            y += BALL_SPEED;
            if(y > game.getPlayer().getY() + Player.PLAYER_HEIGHT){
                isActive = false;
                resetDirections();
                upDirection = true;
            }
        }
    }

    private void moveLeft(){
        if(leftDirection) {
            if(x - BALL_SPEED < 0){
                leftDirection = false;
                rightDirection = true;
            }
            x -= BALL_SPEED;
        }
    }

    private void moveRight(){
        if(rightDirection) {
            if(x >= game.getDisplay().getCanvas().getWidth() - BALL_WIDTH){
                rightDirection = false;
                leftDirection = true;
            }
            x += BALL_SPEED;
        }
    }

    private void followPlayer(){
         x = game.getPlayer().getX() + Player.PLAYER_WIDTH / 2 - Ball.BALL_WIDTH / 2;
         y = game.getPlayer().getY() - Player.PLAYER_HEIGHT;
    }

    private void checkInput(){
        if(game.getKeyManager().space){
            isActive = true;
        }
    }

    @Override
    public void tick() {
        checkInput();
        if(isActive){
            move();
        } else {
            followPlayer();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, (int)BALL_WIDTH, (int)BALL_HEIGHT);
        g.setColor(Color.BLUE);
    }
}
