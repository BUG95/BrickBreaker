package game.brickbraker.map;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.Ball;
import game.brickbraker.entities.dynamic.Player;
import game.brickbraker.entities.fixed.Brick;
import game.brickbraker.entities.fixed.BrickManager;
import game.brickbraker.utils.Utils;

import java.awt.*;

public class Map {
    private int width, height;
    private Game game;
    private BrickManager brickManager;
    private Player player;
    private Ball ball;

    public Map(Game game, String path){
        this.game = game;
        brickManager = new BrickManager(game);
        player = new Player(game, (float)game.getDisplay().getCanvas().getWidth() / 2 - Player.PLAYER_WIDTH / 2, game.getDisplay().getCanvas().getHeight() - 20);
        ball = new Ball(game,0,0);
        loadMap(path);
    }

    private void loadMap(String path){
        String file = Utils.loadFile(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        int xPos = 0, yPos = 0, xOffset, yOffset;
        xOffset = (int)Brick.BRICK_WIDTH + 5;
        yOffset = (int)Brick.BRICK_HEIGHT + 5;

        xPos += xOffset;

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                brickManager.addBrick(new Brick(game, xPos, yPos, Utils.parseInt(tokens[(y + x * height) + 2])));
                xPos += xOffset;
            }
            yPos += yOffset;
            xPos = xOffset;
        }
    }

    public void tick(){
        brickManager.tick();
        player.tick();
        ball.tick();
    }

    public void render(Graphics g){
        brickManager.render(g);
        player.render(g);
        ball.render(g);
    }

    public BrickManager getBrickManager(){
        return brickManager;
    }
    public Player getPlayer(){return player;}
    public Ball getBall(){return ball;}


}
