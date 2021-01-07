package game.brickbraker.entities.fixed;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.gift.Gift;
import game.brickbraker.gfx.Assets;
import game.brickbraker.utils.Utils;

import java.awt.*;

public class Brick extends FixedEntity {
    public static final float BRICK_WIDTH = 30, BRICK_HEIGHT = 15;
    private boolean isHit = false;

    public Brick(Game game, float x, float y, int level) {
        super(game, x, y, BRICK_WIDTH, BRICK_HEIGHT, level);
    }

    @Override
    public void tick() {
        if(isHit){
            level--;
            isHit = false;
            if(level == 0){
                checkForGift();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().getBrickLevelByGameLevel(game.getGameState().getLevel(), level), (int)x, (int)y, (int)BRICK_WIDTH, (int)BRICK_HEIGHT, null);
    }

    private void checkForGift(){
        boolean hasActiveGift = game.getGameState().getMap().getGiftManager().hasActiveGift();
        boolean hasLimitedGift = Gift.limitedGift;
        if(game.getGameState().getMap().getPlayer().getScore() % 2 == 0 && !hasActiveGift && !hasLimitedGift){
            int randomGift = Utils.getRandomNumberInRange(Gift.AVAILABLE_GIFTS);
            game.getGameState().getMap().getGiftManager().addGift(new Gift(game, x, y, 20, 20, true, randomGift));
        }
    }

    public boolean getHit(){
        return isHit;
    }

    public void setHit(boolean hit){
        isHit = hit;
    }

    public int getLevel(){
        return level;
    }
}
