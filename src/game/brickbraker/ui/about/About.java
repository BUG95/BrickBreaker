package game.brickbraker.ui.about;

import javax.swing.*;
import java.awt.*;

public class About extends JDialog{
    public About(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        setSize(315, 160);
        setResizable(false);
        setLocationRelativeTo(null);
        add(new DrawPanel(300, 160));
        setVisible(true);
    }
}

class DrawPanel extends JPanel implements Runnable{

    private int width, height;
    private int xName = 300, yName = 50, yText = 50;
    private String currentLetterFromName = "", finalStringName = "", text = "";
    private Thread thread;
    private boolean isRunning = false;
    private Font font;
    private FontMetrics fm;
    private int nameCenter, textCenter;

    public DrawPanel(int width, int height){
        this.width = width;
        this.height = height;
        font = new Font("SansSerif", Font.PLAIN, 25);
        start();
    }

    private synchronized void start(){
        if(!isRunning){
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        }
    }

    private synchronized void stop(){
        if(isRunning){
            isRunning = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(font);
        fm = g.getFontMetrics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, width, height);
        g.setColor(Color.GREEN);

        g.drawString(text, textCenter, yText);

        g.drawString(finalStringName, nameCenter, yName);
        g.drawString(currentLetterFromName, xName, yName);
    }

    private void drawName(String name){
        long lastTime = System.currentTimeMillis();
        long nowTime, time = 0;
        fm = getFontMetrics(font);
        int startX = (width - fm.stringWidth(name)) / 2;
        nameCenter = (width - fm.stringWidth(name)) / 2;
        for(int i = 0; i < name.length(); i++){
            for(int k = width; k >= startX; k -= 10){
                do{
                    nowTime = System.currentTimeMillis();
                    time += nowTime - lastTime;
                    lastTime = nowTime;
                } while (time <= 10);
                time = 0;
                currentLetterFromName = String.valueOf(name.charAt(i));
                xName = k;
                repaint();
            }
            finalStringName += currentLetterFromName;
            startX += fm.stringWidth(String.valueOf(name.charAt(i)));
        }
        currentLetterFromName = "";
        repaint();
    }

    private void drawBeforeName(String text){
        long lastTime = System.currentTimeMillis();
        long nowTime, time = 0;
        fm = getFontMetrics(font);
        textCenter = (width - fm.stringWidth(text)) / 2;
        for(int i = 0; i < text.length(); i++){
            do{
                nowTime = System.currentTimeMillis();
                time += nowTime - lastTime;
                lastTime = nowTime;
            } while(time <= 200);
            time = 0;
            this.text += String.valueOf(text.charAt(i));
            repaint();
        }
    }

    @Override
    public void run() {
        if(isRunning){
            fm = getFontMetrics(font);
            drawBeforeName("Game Creator:");
            yName += fm.getHeight();
            drawName("Butnaru Gheorghita");
            stop();
        }
    }
}
