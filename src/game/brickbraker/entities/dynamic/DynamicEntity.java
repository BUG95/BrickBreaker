package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.Entity;

public abstract class DynamicEntity extends Entity {

    protected boolean isActive;

    public DynamicEntity(Game game, float x, float y, float width, float height, boolean isActive) {
        super(game, x, y, width, height);
        this.isActive = isActive;
    }

    public abstract void move();
}
