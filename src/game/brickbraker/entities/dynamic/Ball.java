package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.fixed.Brick;

import java.awt.*;

public class Ball extends DynamicEntity {

    public static final float BALL_WIDTH = 10, BALL_HEIGHT = 10;
    private final int BALL_SPEED = 5;
    private boolean leftDirection, rightDirection, upDirection, downDirection;
    private boolean slowLeftDirection, slowRightDirection;

    public Ball(Game game, float x, float y) {
        super(game, x, y, BALL_WIDTH, BALL_HEIGHT, false);
        resetDirections();
    }

    private void resetDirections(){
        leftDirection = false;
        rightDirection = false;
        slowLeftDirection = false;
        slowRightDirection = false;
        upDirection = true;
        downDirection = false;
    }
        // playerWidth ->  [[l1][l2][c][r2][r1]]
    private void checkCollisions(){
        if (checkCollisionWith(game.getGameState().getMap().getPlayer())){
            resetDirections();

            float l1, l2, c, r2, r1, ballX;
            float playerX = game.getGameState().getMap().getPlayer().getX();
            float offset = Player.PLAYER_WIDTH / 5;

            l1 = playerX +   offset;
            l2 = playerX + 2*offset;
            c =  playerX + 3*offset;
            r2 = playerX + 4*offset;
            r1 = playerX + 5*offset;

            ballX = x + BALL_WIDTH / 2;

            if(ballX >= playerX && ballX < l1) {
                leftDirection = true;
            } else if (ballX >= l1 && ballX < l2){
                leftDirection = true;
                slowLeftDirection = true;
            } else if(ballX >=c && ballX < r2){
                rightDirection = true;
                slowRightDirection = true;
            } else if(ballX >= r2 && ballX <= r1){
                rightDirection = true;
            }

            return;
        }

        for(Brick b : game.getGameState().getMap().getBrickManager().getBricks())
            if(checkCollisionWith(b)){
                b.setHit(true);
                if(downDirection){
                    downDirection = false;
                    upDirection = true;
                    return;
                }
                upDirection = false;
                downDirection = true;
                break;
            }
    }


    @Override
    public void move() {
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
            if(y > game.getGameState().getMap().getPlayer().getY() + Player.PLAYER_HEIGHT){
                isActive = false;
                resetDirections();
                upDirection = true;
            }
        }
    }

    private void moveLeft(){
        if(leftDirection) {
            float speed = BALL_SPEED;
            if(slowLeftDirection) {
                speed = BALL_SPEED - BALL_SPEED / 2.0f;
            }
            if(x - speed < 0){
                leftDirection = false;
                rightDirection = true;
                if(slowLeftDirection){
                    slowLeftDirection = false;
                    slowRightDirection = true;
                }
                return;
            }
            if(slowLeftDirection){
                x -= BALL_SPEED - BALL_SPEED / 2.0f;
                return;
            }
            x -= BALL_SPEED;
        }
    }

    private void moveRight(){
        if(rightDirection) {
            if(x >= game.getDisplay().getCanvas().getWidth() - BALL_WIDTH){
                rightDirection = false;
                leftDirection = true;
                if(slowRightDirection){
                    slowRightDirection = false;
                    slowLeftDirection = true;
                }
                return;
            }
            if(slowRightDirection){
                x += BALL_SPEED - BALL_SPEED / 2.0f;
                return;
            }
            x += BALL_SPEED;
        }
    }

    private void followPlayer(){
         x = game.getGameState().getMap().getPlayer().getX() + Player.PLAYER_WIDTH / 2 - Ball.BALL_WIDTH / 2;
         y = game.getGameState().getMap().getPlayer().getY() - Player.PLAYER_HEIGHT;
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
            checkCollisions();
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
