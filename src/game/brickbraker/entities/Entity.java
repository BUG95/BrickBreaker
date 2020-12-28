package game.brickbraker.entities;

import game.brickbraker.Game;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected float width, height;
    protected Game game;
    private Rectangle bounds;
    protected int level;

    public Entity(Game game, float x, float y, float width, float height, int level){
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
        bounds = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    private void setBounds(float x, float y, float width, float height){
        bounds.x = (int)x;
        bounds.y = (int)y;
        bounds.width = (int)width;
        bounds.height = (int)height;
    }

    protected boolean checkCollisionWith(Entity e){
        setBounds(x , y, width, height);
        e.setBounds(e.x, e.y, e.width, e.height);
        return bounds.intersects(e.bounds);
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void increaseLevel(){
        level ++;
    }

    public void decreaseLevel(){
        if(level > 0){
            level--;
        }
    }

    public int getLevel(){
        return level;
    }
}
