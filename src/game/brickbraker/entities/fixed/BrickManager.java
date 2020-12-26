package game.brickbraker.entities.fixed;

import game.brickbraker.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BrickManager {

    private Game game;
    private ArrayList<Brick> bricks;

    public BrickManager(Game game){
        this.game = game;
        bricks = new ArrayList<>();
    }

    public void addBrick(Brick brick){
        bricks.add(brick);
    }

    public void remove(Brick brick){
        bricks.remove(brick);
    }

    public void tick(){
        Iterator<Brick> it = bricks.iterator();
        while(it.hasNext()){
            Brick brick = it.next();
            brick.tick();
            if(brick.getLevel() <= 0){
                it.remove();
            }
        }
    }

    public void render(Graphics g){
        for(Brick brick : bricks){
            brick.render(g);
        }
    }

    public Game getGame(){
        return game;
    }

    public ArrayList<Brick> getBricks(){
        return bricks;
    }

    public int getAvailableBricks(){
        return bricks.size();
    }
}
