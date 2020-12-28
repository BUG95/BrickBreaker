package game.brickbraker.gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {
    private static FontLoader fontLoader = null;
    private FontLoader(){}
    public static FontLoader getInstance(){
        if(fontLoader == null){
            fontLoader = new FontLoader();
        }
        return fontLoader;
    }
    public Font loadFont(String path, float fontSize){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(fontSize);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
