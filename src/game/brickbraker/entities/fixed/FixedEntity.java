package game.brickbraker.entities.fixed;

import game.brickbraker.Game;
import game.brickbraker.entities.Entity;

public abstract class FixedEntity extends Entity {
    public FixedEntity(Game game, float x, float y, float width, float height, int level) {
        super(game, x, y, width, height, level);
    }
}
