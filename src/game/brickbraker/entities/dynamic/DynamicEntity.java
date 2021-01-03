package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.Entity;

public abstract class DynamicEntity extends Entity {

    protected boolean isActive;

    public DynamicEntity(Game game, float x, float y, float width, float height, boolean isActive, int level) {
        super(game, x, y, width, height, level);
        this.isActive = isActive;
    }

    public abstract void move();
    public void setWidth(float width){this.width = width;}
    public float getWidth(){return width;}
}
