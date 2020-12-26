package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.fixed.Brick;

import java.awt.*;

public class Ball extends DynamicEntity {

    public static final float BALL_WIDTH = 14, BALL_HEIGHT = 14;
    private final int BALL_SPEED = 5;
    private boolean leftDirection, rightDirection, upDirection, downDirection;
    private boolean moveSlow;
    private boolean isX = false;
    public Ball(Game game, float x, float y) {
        super(game, x, y, BALL_WIDTH, BALL_HEIGHT, false);
        resetDirections();

    }

    private void resetDirections(){
        leftDirection = false;
        rightDirection = false;
        upDirection = true;
        downDirection = false;
        moveSlow = false;
    }

    private void checkCollisions(){
        if (checkCollisionWith(game.getGameState().getMap().getPlayer())){
            resetDirections();

            // playerWidth ->  [[l1][l2][c][r2][r1]]
            float l1, l2, c, r2, ballX;
            float playerX = game.getGameState().getMap().getPlayer().getX();
            float offset = Player.PLAYER_WIDTH / 5;

            l1 = playerX +   offset;
            l2 = playerX + 2*offset;
            c =  playerX + 3*offset;
            r2 = playerX + 4*offset;

            ballX = x + BALL_WIDTH / 2;

            if(ballX <= l1) {
                leftDirection = true;
            } else if (ballX > l1 && ballX <= l2){
                leftDirection = true;
                moveSlow = true;
            } else if(ballX >= c && ballX <= r2){
                rightDirection = true;
                moveSlow = true;
            } else if(ballX > r2){
                rightDirection = true;
            }

            return;
        }

        for(Brick b : game.getGameState().getMap().getBrickManager().getBricks())
            if(checkCollisionWith(b)){
                b.setHit(true);
                // temp code
                if(isX) return;
                //
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
            if(y < game.getGameState().getMap().getTopBorderHeight()){
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
            if(moveSlow) {
                speed = BALL_SPEED - BALL_SPEED / 2.0f;
            }
            if(x - game.getGameState().getMap().getLeftBorderWidth() - speed < 0){
                leftDirection = false;
                rightDirection = true;
                return;
            }
            if(moveSlow){
                x -= BALL_SPEED - BALL_SPEED / 2.0f;
                return;
            }
            x -= BALL_SPEED;
        }
    }

    private void moveRight(){
        if(rightDirection) {
            if(x >= game.getDisplay().getCanvas().getWidth() - BALL_WIDTH - game.getGameState().getMap().getRightBorderWidth()){
                rightDirection = false;
                leftDirection = true;
                return;
            }
            if(moveSlow){
                x += BALL_SPEED - BALL_SPEED / 2.0f;
                return;
            }
            x += BALL_SPEED;
        }
    }

    private void followPlayer(){
         x = game.getGameState().getMap().getPlayer().getX() + Player.PLAYER_WIDTH / 2 - Ball.BALL_WIDTH / 2;
         y = game.getGameState().getMap().getPlayer().getY() - Ball.BALL_HEIGHT;
    }

    private void checkInput(){
        if(game.getKeyManager().space){
            isActive = true;
        }
        // temp code
        if(game.getKeyManager().x){
            isX = true;
        }
        //
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
