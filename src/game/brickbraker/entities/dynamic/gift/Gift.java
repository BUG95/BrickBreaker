package game.brickbraker.entities.dynamic.gift;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.Ball;
import game.brickbraker.entities.dynamic.DynamicEntity;
import game.brickbraker.gfx.Assets;

import java.awt.*;

public class Gift extends DynamicEntity implements Runnable{

    public static final int AVAILABLE_GIFTS = 4;
    public static boolean limitedGift = false;
    private final int DEFAULT_ACTIVE_SECONDS = 10;
    private String name;
    private static Thread thread;
    private static boolean activeGift = false;

    public Gift(Game game, float x, float y, float width, float height, boolean isActive, int level) {
        super(game, x, y, width, height, isActive, level);
    }

    public synchronized void start(){
        if(!activeGift){
            activeGift = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public static synchronized void stop(){
        if(activeGift){
            activeGift = false;
            limitedGift = false;
            thread.stop();
        }
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
            game.getGameState().getMap().getBall().setLevel(Ball.SPEEDBALL_ID);
            limitedGift = true;
        } else if(level == 4){
            name = "Extra Width";
            game.getGameState().getMap().getPlayer().setGiftPlayerWidth(18);
            limitedGift = true;
        }
        if(limitedGift){
           start();
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
        final int ONE_SECOND = 1000;
        game.getGameState().getMap().setGiftName(name);

        for(int i = 0; i < DEFAULT_ACTIVE_SECONDS; i++){
            game.getGameState().getMap().setGiftActiveSeconds(String.valueOf(10 - i));
            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        limitedGift = false;

        if(level == 2){
            game.getGameState().getMap().getBall().setCancelDirections(false);
        } else if(level == 3){
            game.getGameState().getMap().getPlayer().setGiftPlayerSpeed(0);
            game.getGameState().getMap().getBall().setGiftBallSpeed(0);
        } else if(level == 4){
            game.getGameState().getMap().getPlayer().setGiftPlayerWidth(0);
        }

        game.getGameState().getMap().getBall().setLevel(Ball.REGULARBALL_ID);
        game.getGameState().getMap().setGiftName("");
        game.getGameState().getMap().setGiftActiveSeconds("");
    }

    public boolean isActiveGift() {
        return activeGift;
    }
}
