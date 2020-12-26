package game.brickbraker.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    private static ImageLoader imageLoader = null;
    private ImageLoader(){}

    public static ImageLoader getInstance(){
        if(imageLoader == null){
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    public BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
       return null;
    }
}
