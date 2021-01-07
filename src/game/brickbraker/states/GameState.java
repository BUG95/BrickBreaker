package game.brickbraker.states;

import game.brickbraker.Game;
import game.brickbraker.map.Map;

import java.awt.*;

public class GameState extends State {
    private Map map;
    private int level = 1;
    private final int MAX_LEVEL = 5;
    public GameState(Game game) {
        super(game);
        loadMap();
    }

    public void levelUp(){
        level++;
        //if(level > MAX_LEVEL); /* switch state to game completed" */
        loadMap();
    }

    private void loadMap(){
        map = new Map(game, "res/maps/map" + level + ".txt");
    }

    @Override
    public void tick() {
        map.tick();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
    }

    public Map getMap(){
        return map;
    }

    public int getLevel(){
        return level;
    }

    public void restart(){
        level = 1;
        loadMap();
    }
}
