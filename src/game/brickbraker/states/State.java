package game.brickbraker.states;

import game.brickbraker.Game;

import java.awt.*;

public abstract class State {

    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
