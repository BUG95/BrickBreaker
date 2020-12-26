package game.brickbraker.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {
    private static Assets assets = null;
    private Assets(){}

    public static Assets getInstance(){
        if(assets == null){
            assets = new Assets();
        }
        return assets;
    }

    private BufferedImage border;
    private final int BRICK_WIDTH = 16, BRICK_HEIGHT = 8;
    private final int BRICK_SHEET_WIDTH = 20, BRICK_SHEET_HEIGHT = 32;
    private HashMap<Integer, HashMap<Integer, BufferedImage>> bricksByGameLevel;

    public void init(){
        bricksByGameLevel = new HashMap<>();

        HashMap<Integer, BufferedImage> bricksForGameLevel1, bricksForGameLevel2,
                                        bricksForGameLevel3, bricksForGameLevel4, bricksForGameLevel5;

        bricksForGameLevel1 = new HashMap<>();
        bricksForGameLevel2 = new HashMap<>();
        bricksForGameLevel3 = new HashMap<>();
        bricksForGameLevel4 = new HashMap<>();
        bricksForGameLevel5 = new HashMap<>();

        border = ImageLoader.getInstance().loadImage("/textures/border1.png");

        SpriteSheet sheet = new SpriteSheet(ImageLoader.getInstance().loadImage("/textures/bricks.png"));
        BufferedImage[][] bricks = new BufferedImage[BRICK_SHEET_WIDTH][BRICK_SHEET_HEIGHT];

        for(int i = 0; i < BRICK_SHEET_WIDTH; i++){
            for(int j = 0; j < BRICK_SHEET_HEIGHT; j++){
                bricks[i][j] = sheet.crop(i * BRICK_WIDTH, j * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
            }
        }

        // bricks for game level 1
        bricksForGameLevel1.put(1, bricks[16][11]);

        // bricks for game level 2
        bricksForGameLevel2.put(1, bricks[1][2]);
        bricksForGameLevel2.put(2, bricks[1][3]);

        // bricks for game level 3
        bricksForGameLevel3.put(1, bricks[1][5]); // brick level 1
        bricksForGameLevel3.put(2, bricks[1][6]); // brick level 2
        bricksForGameLevel3.put(3, bricks[1][7]); // brick level 3

        // bricks for game level 4
        bricksForGameLevel4.put(1, bricks[4][10]);
        bricksForGameLevel4.put(2, bricks[3][9]);
        bricksForGameLevel4.put(3, bricks[5][8]);
        bricksForGameLevel4.put(4, bricks[6][8]);

        // bricks for game level 5
        bricksForGameLevel5.put(1, bricks[9][19]);
        bricksForGameLevel5.put(2, bricks[9][20]);
        bricksForGameLevel5.put(3, bricks[4][22]);
        bricksForGameLevel5.put(4, bricks[5][22]);
        bricksForGameLevel5.put(5, bricks[12][25]);


        bricksByGameLevel.put(1, bricksForGameLevel1); // game level 1
        bricksByGameLevel.put(2, bricksForGameLevel2); // game level 2
        bricksByGameLevel.put(3, bricksForGameLevel3); // game level 3
        bricksByGameLevel.put(4, bricksForGameLevel4); // game level 4
        bricksByGameLevel.put(5, bricksForGameLevel5); // game level 5
    }

    public BufferedImage getBrickLevelByGameLevel(int gameLevel, int brickLevel){
        return bricksByGameLevel.get(gameLevel).get(brickLevel);
    }

    public BufferedImage getLevelBorder(){
        return border;
    }

}
