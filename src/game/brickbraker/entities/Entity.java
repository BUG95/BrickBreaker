package game.brickbraker.entities;

import game.brickbraker.Game;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected float width, height;
    protected Game game;

    public Entity(Game game, float x, float y, float width, float height){
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
}
