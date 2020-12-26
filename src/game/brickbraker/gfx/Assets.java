package game.brickbraker.gfx;

import java.awt.image.BufferedImage;

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

    public void init(){
        border = ImageLoader.getInstance().loadImage("/textures/border1.png");
    }

    public BufferedImage getLevelBorder(){
        return border;
    }

}
