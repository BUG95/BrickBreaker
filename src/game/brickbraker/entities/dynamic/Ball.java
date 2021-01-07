package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.gift.Gift;
import game.brickbraker.entities.fixed.Brick;
import game.brickbraker.gfx.Assets;

import java.awt.*;

public class Ball extends DynamicEntity {

    public static final float BALL_WIDTH = 14, BALL_HEIGHT = 14;
    public static final int REGULARBALL_ID = 1, FIREBALL_ID = 2, SPEEDBALL_ID = 3;
    private final int BALL_SPEED = 5;
    private int giftBallSpeed = 0;
    private boolean leftDirection, rightDirection, upDirection, downDirection;
    private boolean moveSlow;
    private boolean cancelDirections = false;
    public Ball(Game game, float x, float y) {
        super(game, x, y, BALL_WIDTH, BALL_HEIGHT, false, REGULARBALL_ID);
        resetDirections();
    }

    private void resetDirections(){
        leftDirection = false;
        rightDirection = false;
        upDirection = true;
        downDirection = false;
        moveSlow = false;
    }

    private void addToPlayerScore(int amt){
        game.getGameState().getMap().getPlayer().addToScore(amt);
    }

    private void checkCollisions(){
        if (checkCollisionWith(game.getGameState().getMap().getPlayer())){
            resetDirections();

            // playerWidth ->  [[l1][l2][c][r2][r1]]
            float l1, l2, c, r2, ballX;
            float playerX = game.getGameState().getMap().getPlayer().getX();
            float offset = game.getGameState().getMap().getPlayer().getWidth() / 5;

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
                addToPlayerScore(b.getLevel());
                if(cancelDirections) return; //from gift -> fireball
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
            y -= BALL_SPEED + giftBallSpeed;
            if(y < game.getGameState().getMap().getTopBorderHeight()){
                upDirection = false;
                downDirection = true;
            }
        }
    }

    private void moveDown(){
        if(downDirection) {
            y += BALL_SPEED + giftBallSpeed;
            if(y > game.getGameState().getMap().getPlayer().getY() + Player.PLAYER_HEIGHT){
                game.getGameState().getMap().getPlayer().decreaseLives();
                isActive = false;
                resetDirections();
                upDirection = true;
            }
        }
    }

    private void moveLeft(){
        if(leftDirection) {
            float speed = BALL_SPEED + giftBallSpeed;
            if(moveSlow) {
                speed = speed - speed / 2.0f;
            }
            if(x - game.getGameState().getMap().getLeftBorderWidth() - speed < 0){
                leftDirection = false;
                rightDirection = true;
                return;
            }
            if(moveSlow){
                x -= speed - speed / 2.0f;
                return;
            }
            x -= BALL_SPEED + giftBallSpeed;
        }
    }

    private void moveRight(){
        if(rightDirection) {
            int speed = BALL_SPEED + giftBallSpeed;
            if(x >= game.getDisplay().getCanvas().getWidth() - BALL_WIDTH - game.getGameState().getMap().getRightBorderWidth()){
                rightDirection = false;
                leftDirection = true;
                return;
            }
            if(moveSlow){
                x += speed - speed / 2.0f;
                return;
            }
            x += speed;
        }
    }

    private void followPlayer(){
         x = game.getGameState().getMap().getPlayer().getX() + game.getGameState().getMap().getPlayer().getWidth() / 2 - Ball.BALL_WIDTH / 2;
         y = game.getGameState().getMap().getPlayer().getY() - Ball.BALL_HEIGHT;
    }

    private void checkInput(){
        if(game.getKeyManager().space){
            isActive = true;
        }
    }

    private boolean levelCompleted(){
        return (game.getGameState().getMap().getBrickManager().getAvailableBricks() == 0);
    }

    private void nextLevel(){
        int currentLives = game.getGameState().getMap().getPlayer().getLives();
        game.getGameState().levelUp();
        game.getGameState().getMap().getPlayer().setLives(currentLives);
    }

    @Override
    public void tick() {
        if(levelCompleted()){
            nextLevel();
            Gift.stop();
            return;
        }
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
        g.drawImage(Assets.getInstance().getBallByGameLevel(game.getGameState().getLevel(), level), (int)x, (int)y, (int)BALL_WIDTH, (int)BALL_HEIGHT, null);
    }

    public void setCancelDirections(boolean value){
        cancelDirections = value;
    }

    public void setGiftBallSpeed(int speed){
        giftBallSpeed = speed;
    }
}
