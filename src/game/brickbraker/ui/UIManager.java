package game.brickbraker.ui;

import game.brickbraker.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private ArrayList<UIObject> objects;
    private Game game;

    public UIManager(Game game){
        this.game = game;
        objects = new ArrayList<>();
    }

    public void addObject(UIObject object){
        objects.add(object);
    }

    public void tick(){
        for(UIObject o : objects){
            o.tick();
        }
    }

    public void render(Graphics g){
        for(UIObject o : objects){
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e){
        for(UIObject o : objects) o.onMouseMove(e);
    }

    public void onMouseReleased(MouseEvent e){
        for(UIObject o : objects) o.onMouseReleased(e);
    }
}
