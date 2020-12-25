package game.brickbraker;

import game.brickbraker.display.Display;
import game.brickbraker.entities.dynamic.Ball;
import game.brickbraker.entities.dynamic.Player;
import game.brickbraker.entities.fixed.Brick;
import game.brickbraker.entities.fixed.BrickManager;
import game.brickbraker.input.KeyManager;
import game.brickbraker.map.Map;

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
    // player
    private Player player;
    // ball
    private Ball ball;
    // key manager
    private KeyManager keyManager;
    // world
    private Map map;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        map = new Map(this, "res/maps/map1.txt");
        player = new Player(this, (float)width / 2 - Player.PLAYER_WIDTH / 2, height - 20);
        ball = new Ball(this, 0,0);
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
    }

    public void tick(){
        map.tick();
        keyManager.tick();
        player.tick();
        ball.tick();
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
        map.render(g);
        player.render(g);
        ball.render(g);
        // end draw
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

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
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Display getDisplay(){
        return display;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public Player getPlayer(){
        return player;
    }

    public Ball getBall(){
        return ball;
    }

    public Map getMap(){
        return map;
    }

}
