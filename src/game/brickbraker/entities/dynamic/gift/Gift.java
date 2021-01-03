package game.brickbraker.entities.dynamic.gift;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.Ball;
import game.brickbraker.entities.dynamic.DynamicEntity;
import game.brickbraker.gfx.Assets;

import java.awt.*;

public class Gift extends DynamicEntity implements Runnable{

    public static final int AVAILABLE_GIFTS = 4;
    public static boolean limitedGift = false;
    private final int DEFAULT_SECONDS_ACTIVE = 10;
    private String name;
    private Thread thread;

    public Gift(Game game, float x, float y, float width, float height, boolean isActive, int level) {
        super(game, x, y, width, height, isActive, level);
    }

    public void giveTheGift(){
        isActive = false;
        // give a live
        if(level == 1){
            name = "Life";
            game.getGameState().getMap().getPlayer().increaseLives();
            // give fireball
        } else if(level == 2){
            name = "Fireball";
            game.getGameState().getMap().getBall().setCancelDirections(true);
            game.getGameState().getMap().getBall().setLevel(Ball.FIREBALL_ID);
            limitedGift = true;
            // give player speed
        } else if(level == 3){
            name = "Speed";
            game.getGameState().getMap().getPlayer().setGiftPlayerSpeed(3);
            game.getGameState().getMap().getBall().setGiftBallSpeed(3);
            limitedGift = true;
        } else if(level == 4){
            name = "Extra Width";
            game.getGameState().getMap().getPlayer().setGiftPlayerWidth(18);
            limitedGift = true;
        }
        if(limitedGift){
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void move() {
        y += 1;
        checkCollision();
        checkBoundExceed();
    }

    private void checkCollision(){
        if(checkCollisionWith(game.getGameState().getMap().getPlayer())){
            giveTheGift();
        }
    }

    private void checkBoundExceed(){
        if(y >= game.getDisplay().getCanvas().getWidth()){
            isActive = false;
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().getGiftByLevel(level), (int)x, (int)y, 20, 20, null);
    }

    public boolean isActive(){
        return isActive;
    }


    @Override
    public void run() {
        game.getGameState().getMap().setGiftName(name);

        for(int i = 0; i < DEFAULT_SECONDS_ACTIVE; i++){
            game.getGameState().getMap().setGiftActiveSeconds(String.valueOf(10 - i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(level == 2){
            game.getGameState().getMap().getBall().setLevel(Ball.REGULARBALL_ID);
            game.getGameState().getMap().getBall().setCancelDirections(false);
        } else if(level == 3){
            game.getGameState().getMap().getPlayer().setGiftPlayerSpeed(0);
            game.getGameState().getMap().getBall().setGiftBallSpeed(0);
        } else if(level == 4){
            game.getGameState().getMap().getPlayer().setGiftPlayerWidth(0);
        }
        limitedGift = false;
        game.getGameState().getMap().setGiftName("");
        game.getGameState().getMap().setGiftActiveSeconds("");
    }

}
