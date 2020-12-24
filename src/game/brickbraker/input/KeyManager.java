package game.brickbraker.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean[] keys;
    public boolean leftArrow, rightArrow;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        leftArrow = keys[KeyEvent.VK_LEFT];
        rightArrow = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyPressed = keyEvent.getKeyCode();
        if(keyPressed < 0 || keyPressed >= keys.length) return;
        keys[keyPressed] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyPressed = keyEvent.getKeyCode();
        if(keyPressed < 0 || keyPressed >= keys.length) return;;
        keys[keyPressed] = false;
    }
}
