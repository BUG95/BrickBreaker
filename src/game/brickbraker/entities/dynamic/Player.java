package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;

import java.awt.*;

public class Player extends DynamicEntity {

    public static final float PLAYER_WIDTH = 80, PLAYER_HEIGHT = 10, PLAYER_Y_OFFSET = 20;
    private static final int PLAYER_SPEED = 6;
    private int lives = 3;
    private long score = 0;

    public Player(Game game, float x, float y) {
        super(game, x, y, PLAYER_WIDTH, PLAYER_HEIGHT, true);
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
        if(x - game.getGameState().getMap().getLeftBorderWidth() - PLAYER_SPEED < 0) return;
        x -= PLAYER_SPEED;
    }

    private void moveRight(){
        if(x >= game.getDisplay().getCanvas().getWidth() - PLAYER_WIDTH - game.getGameState().getMap().getRightBorderWidth()) return;
        x += PLAYER_SPEED;
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

    public int getLives(){
        return lives;
    }

    public void setLives(int lives){this.lives=lives;}

    public void decreaseLives(){
        lives--;
    }

    public void addToScore(int amt){
        score += amt;
    }

    public long getScore() {
        return score;
    }
}
