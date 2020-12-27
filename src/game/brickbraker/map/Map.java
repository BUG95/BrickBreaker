package game.brickbraker.map;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.Ball;
import game.brickbraker.entities.dynamic.Player;
import game.brickbraker.entities.fixed.Brick;
import game.brickbraker.entities.fixed.BrickManager;
import game.brickbraker.gfx.Assets;
import game.brickbraker.utils.Utils;

import java.awt.*;

public class Map {
    private int width, height;
    private int topBorderHeight, rightBorderWidth, bottomBorderHeight, leftBorderWidth;
    private int topBorderInfoPanelHeight, rightBorderInfoPanelWidth, bottomBorderInfoPanelHeight, leftBorderInfoPanelWidth;
    private int heartWidth, heartHeight;
    private Game game;
    private BrickManager brickManager;
    private Player player;
    private Ball ball;

    public Map(Game game, String path){
        this.game = game;
        brickManager = new BrickManager(game);
        player = new Player(game, (float)game.getDisplay().getCanvas().getWidth() / 2 - Player.PLAYER_WIDTH / 2, game.getDisplay().getCanvas().getHeight() - Player.PLAYER_Y_OFFSET);
        ball = new Ball(game,-1,-1);
        loadMap(path);
    }

    private void loadMap(String path){
        String file = Utils.loadFile(path);
        String[] tokens = file.split("\\s+");

        Assets.getInstance().init();
        topBorderHeight = Utils.parseInt(tokens[0]);
        rightBorderWidth = Utils.parseInt(tokens[1]);
        bottomBorderHeight = Utils.parseInt(tokens[2]);
        leftBorderWidth = Utils.parseInt(tokens[3]);

        topBorderInfoPanelHeight = Utils.parseInt(tokens[4]);
        rightBorderInfoPanelWidth = Utils.parseInt(tokens[5]);
        bottomBorderInfoPanelHeight = Utils.parseInt(tokens[6]);
        leftBorderInfoPanelWidth = Utils.parseInt(tokens[7]);

        heartWidth = Utils.parseInt(tokens[8]);
        heartHeight = Utils.parseInt(tokens[9]);

        width = Utils.parseInt(tokens[10]);
        height = Utils.parseInt(tokens[11]);

        int xStartPos = 0, yStartPos = topBorderHeight + heartHeight + 10, xOffset, yOffset;
        xOffset = (int)Brick.BRICK_WIDTH + 5;
        yOffset = (int)Brick.BRICK_HEIGHT + 5;

        xStartPos += xOffset;

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                brickManager.addBrick(new Brick(game, xStartPos, yStartPos, Utils.parseInt(tokens[(y + x * height) + 12])));
                xStartPos += xOffset;
            }
            yStartPos += yOffset;
            xStartPos = xOffset;
        }
    }

    public void tick(){
        brickManager.tick();
        player.tick();
        ball.tick();
    }

    public void render(Graphics g){
        g.drawImage(Assets.getInstance().getLevelBorder(), 0,0, null);
        for(int i = 1; i <= player.getLives(); i++){
            g.drawImage(Assets.getInstance().getHeartImg(), game.getDisplay().getCanvas().getWidth()  - rightBorderInfoPanelWidth - i * heartWidth, (topBorderInfoPanelHeight + bottomBorderInfoPanelHeight) / 2 - heartHeight / 2, heartWidth, heartHeight,null);
        }
        brickManager.render(g);
        player.render(g);
        ball.render(g);
    }

    public BrickManager getBrickManager(){
        return brickManager;
    }
    public Player getPlayer(){return player;}
    public Ball getBall(){return ball;}

    public int getTopBorderHeight() {
        return topBorderHeight;
    }

    public int getRightBorderWidth() {
        return rightBorderWidth;
    }

    public int getBottomBorderHeight() {
        return bottomBorderHeight;
    }

    public int getLeftBorderWidth() {
        return leftBorderWidth;
    }
}
