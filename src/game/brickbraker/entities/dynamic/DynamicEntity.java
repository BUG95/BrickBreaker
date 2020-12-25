package game.brickbraker.entities.dynamic;

import game.brickbraker.Game;
import game.brickbraker.entities.Entity;

public abstract class DynamicEntity extends Entity {

    public DynamicEntity(Game game, float x, float y, float width, float height) {
        super(game, x, y, width, height);
    }

    public abstract void move();
}
