package game.brickbraker;

import game.brickbraker.display.Display;
import game.brickbraker.input.KeyManager;
import game.brickbraker.input.MouseManager;
import game.brickbraker.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private String title;
    private int width, height;
    private Display display;
    private Thread thread;
    private boolean isRunning = false;
    private BufferStrategy bs;
    private Graphics g;
    // key manager
    private KeyManager keyManager;
    // mouse manager
    private MouseManager mouseManager;
    // states
    private State gameState;
    private State menuState;
    private State gameOver;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        displayInit();
        gameState = new GameState(this);
        menuState = new MenuState(this);
        gameOver = new GameOverState(this);
        StateManager.getInstance().setCurrentState(menuState);
    }

    private void displayInit(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
    }

    public void tick(){
        if(StateManager.getInstance().getCurrentState() == null) return;
        StateManager.getInstance().getCurrentState().tick();
        keyManager.tick();
    }


    public void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,width, height);
        // draw
        if(StateManager.getInstance().getCurrentState() == null) return;
        StateManager.getInstance().getCurrentState().render(g);
        // end draw
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        int fps = 60;
        double timePerTick = 1000000000 / (double)fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(isRunning){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                //System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        thread.interrupt();
    }

    public Display getDisplay(){
        return display;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameState getGameState(){
        return (GameState) gameState;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameOverState getGameOverState(){
        return (GameOverState) gameOver;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public State getMenuState() {
        return menuState;
    }
}
