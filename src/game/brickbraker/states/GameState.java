package game.brickbraker.states;

import game.brickbraker.Game;
import game.brickbraker.map.Map;

import java.awt.*;

public class GameState extends State {
    private Map map;
    public GameState(Game game) {
        super(game);
        map = new Map(game, "res/maps/map1.txt");
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

}
