package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.states.GameOverState;
import game.brickbraker.states.StateManager;

import java.awt.*;

public class Player extends DynamicEntity {

    public static final float PLAYER_WIDTH = 80, PLAYER_HEIGHT = 10, PLAYER_Y_OFFSET = 20;
    private static final int DEFAULT_PLAYER_SPEED = 6;
    private int giftPlayerSpeed = 0;
    private final int MAX_LIVES = 3;
    private int lives = 1;
    private long score = 0;

    public Player(Game game, float x, float y) {
        super(game, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, true, 1);
    }

    @Override
    public void move() {
        if(game.getKeyManager().leftArrow){
            moveLeft();
        } else if(game.getKeyManager().rightArrow){
            moveRight();
        }
    }

    private void moveLeft(){
        if(x - game.getGameState().getMap().getLeftBorderWidth() - DEFAULT_PLAYER_SPEED - giftPlayerSpeed < 0) return;
        x -= DEFAULT_PLAYER_SPEED + giftPlayerSpeed;
    }

    private void moveRight(){
        if(x >= game.getDisplay().getCanvas().getWidth() - width - game.getGameState().getMap().getRightBorderWidth()) return;
        x += DEFAULT_PLAYER_SPEED + giftPlayerSpeed;
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
        g.fillRect((int)x, (int)y, (int)(width), (int)height);
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int lives){this.lives=lives;}

    private void checkGameOver(){
        if(lives < 0){
            StateManager.getInstance().setCurrentState(game.getGameOverState());
            GameOverState obj =(GameOverState) StateManager.getInstance().getCurrentState();
            obj.init(GameOverState.OUT_OF_LIVES);
        }
    }

    public void decreaseLives(){
        if(lives >= 0){
            lives--;
        }
        checkGameOver();
    }

    public void increaseLives(){
        if(lives < MAX_LIVES){
            lives++;
        }
    }

    public void addToScore(int amt){
        score += amt;
    }

    public long getScore() {
        return score;
    }

    public void setGiftPlayerSpeed(int giftPlayerSpeed) {
        this.giftPlayerSpeed = giftPlayerSpeed;
    }

    public void setGiftPlayerWidth(float extraPlayerWidth){
        setWidth(PLAYER_WIDTH + extraPlayerWidth);
    }

}
