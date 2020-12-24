package game.brickbraker;

public class Launcher {
    public static void main(String[] args) {
        Game brickBracker = new Game("Brick Breaker", 600, 600);
        brickBracker.start();
    }
}
