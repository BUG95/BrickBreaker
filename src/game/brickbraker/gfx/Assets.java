package game.brickbraker.gfx;

import java.awt.*;
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


    private Font font26, font24, font90;
    private BufferedImage border;
    private BufferedImage heartImg;
    private HashMap<Integer, HashMap<Integer, BufferedImage>> ballByGameLevel;
    private HashMap<Integer, HashMap<Integer, BufferedImage>> bricksByGameLevel;
    private HashMap<Integer, BufferedImage> giftByLevel;


    private void initFont(){
        font26 = FontLoader.getInstance().loadFont("res/fonts/Teko-Light.ttf", 26
        );
        font24 = FontLoader.getInstance().loadFont("res/fonts/Teko-Light.ttf", 24);
        font90 = FontLoader.getInstance().loadFont("res/fonts/Teko-Light.ttf", 90);
    }

    private void initBrick(){
        final int BRICK_WIDTH = 16, BRICK_HEIGHT = 8;
        final int BRICK_SHEET_WIDTH = 20, BRICK_SHEET_HEIGHT = 32; // 20 cols and 32 rows
        bricksByGameLevel = new HashMap<>();

        HashMap<Integer, BufferedImage> bricksForGameLevel1, bricksForGameLevel2,
                bricksForGameLevel3, bricksForGameLevel4, bricksForGameLevel5;

        bricksForGameLevel1 = new HashMap<>();
        bricksForGameLevel2 = new HashMap<>();
        bricksForGameLevel3 = new HashMap<>();
        bricksForGameLevel4 = new HashMap<>();
        bricksForGameLevel5 = new HashMap<>();

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

    private void initBall(){
        // ball by level
        final int BALL_WIDTH = 55, BALL_HEIGHT = 53;
        final int BALL_SHEET_WIDTH = 3, BALL_SHEET_HEIGHT = 5;
        BufferedImage[][] ball = new BufferedImage[BALL_SHEET_WIDTH][BALL_SHEET_HEIGHT];
        ballByGameLevel = new HashMap<>();
        SpriteSheet ballSpriteSheet = new SpriteSheet(ImageLoader.getInstance().loadImage("/textures/ball.png"));


        // only for 5 levels
        for(int i = 0; i < BALL_SHEET_WIDTH; i++){
            for(int j = 0; j < BALL_SHEET_HEIGHT; j++){
                ball[i][j] = ballSpriteSheet.crop(i * BALL_WIDTH, j * BALL_HEIGHT, BALL_WIDTH, BALL_HEIGHT);
            }
        }

        HashMap<Integer, BufferedImage> ballForGameLevel1, ballForGameLevel2, ballForGameLevel3, ballForGameLevel4, ballForGameLevel5;
        ballForGameLevel1 = new HashMap<>();
        ballForGameLevel2 = new HashMap<>();
        ballForGameLevel3 = new HashMap<>();
        ballForGameLevel4 = new HashMap<>();
        ballForGameLevel5 = new HashMap<>();

        //ballForGameLevel1.put(1, ImageLoader.getInstance().loadImage("/textures/fireBall.png"));
        ballForGameLevel1.put(1, ball[0][0]);
        ballForGameLevel1.put(2, ball[1][0]);
        ballForGameLevel1.put(3, ball[2][0]);

        ballForGameLevel2.put(1, ball[0][1]);
        ballForGameLevel2.put(2, ball[1][1]);
        ballForGameLevel2.put(3, ball[2][1]);

        ballForGameLevel3.put(1, ball[0][2]);
        ballForGameLevel3.put(2, ball[1][2]);
        ballForGameLevel3.put(3, ball[2][2]);

        ballForGameLevel4.put(1, ball[0][3]);
        ballForGameLevel4.put(2, ball[1][3]);
        ballForGameLevel4.put(3, ball[2][3]);

        ballForGameLevel5.put(1, ball[0][4]);
        ballForGameLevel5.put(2, ball[1][4]);
        ballForGameLevel5.put(3, ball[2][4]);

        ballByGameLevel.put(1, ballForGameLevel1);
        ballByGameLevel.put(2, ballForGameLevel2);
        ballByGameLevel.put(3, ballForGameLevel3);
        ballByGameLevel.put(4, ballForGameLevel4);
        ballByGameLevel.put(5, ballForGameLevel5);
    }

    private void initBorder(){
        border = ImageLoader.getInstance().loadImage("/textures/gameBorder.png");
    }

    private void initHeart(){
        final int HEART_WIDTH = 153, HEART_HEIGHT = 146;
        final int HEART_START_X = 44, HEART_START_Y = 54;
        SpriteSheet heartSpriteSheet = new SpriteSheet(ImageLoader.getInstance().loadImage("/textures/heart.png"));
        heartImg = heartSpriteSheet.crop(HEART_START_X, HEART_START_Y, HEART_WIDTH, HEART_HEIGHT);
    }

    private void initGift(){
        giftByLevel = new HashMap<>();
        giftByLevel.put(1, heartImg);
        giftByLevel.put(2, ImageLoader.getInstance().loadImage("/textures/fireBall.png"));
        giftByLevel.put(3, ImageLoader.getInstance().loadImage("/textures/lightning.png"));
        giftByLevel.put(4, ImageLoader.getInstance().loadImage("/textures/arrow.png"));

    }

    public void init(){
        initBorder();
        initHeart();
        initFont();
        initBrick();
        initBall();
        initGift();
    }

    public BufferedImage getBrickLevelByGameLevel(int gameLevel, int brickLevel){
        BufferedImage bimg = null;
        try{
            bimg = bricksByGameLevel.get(gameLevel).get(brickLevel);
        } catch (Exception e){
            e.printStackTrace();
        }
        return bimg;
    }

    public BufferedImage getLevelBorder(){
        return border;
    }

    public BufferedImage getHeartImg(){return heartImg;}

    public BufferedImage getBallByGameLevel(int gameLevel, int ballLevel){
        return ballByGameLevel.get(gameLevel).get(ballLevel);
    }

    public BufferedImage getGiftByLevel(int giftLevel){
        return giftByLevel.get(giftLevel);
    }

    public Font getFont26(){
        return font26;
    }
    public Font getFont24(){
        return font24;
    }
    public Font getFont90(){
        return font90;
    }

}
