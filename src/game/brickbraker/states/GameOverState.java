package game.brickbraker.states;

import game.brickbraker.Game;
import game.brickbraker.entities.dynamic.gift.Gift;
import game.brickbraker.gfx.Assets;
import game.brickbraker.gfx.Text;
import game.brickbraker.ui.UIManager;
import game.brickbraker.ui.UIText;

import java.awt.*;

public class GameOverState extends State {

    private UIManager uiManager;
    private String info = "";
    public static final int OUT_OF_LIVES = 1, GAME_COMPLETED = 2;

    public GameOverState(Game game) {
        super(game);
        uiManager = new UIManager(game);
    }

    public void init(int state){
        game.getMouseManager().setUiManager(uiManager);
        addOptions(state);
    }

    private void addOptions(int state){
        if(state == OUT_OF_LIVES){
            info = "OUT OF LIVES";
        } else if(state == GAME_COMPLETED){
            info = "GAME COMPLETED";
        }

        String restart = "RESTART";

        FontMetrics fm = Text.getInstance().getFontMetricsOf(Assets.getInstance().getFont26());
        float x = (game.getDisplay().getCanvas().getWidth() - fm.stringWidth(restart)) / 2.0f;
        float y = (3 * game.getDisplay().getCanvas().getHeight() / 4.0f - fm.getHeight()) / 2.0f + fm.getAscent() + fm.getHeight();

        int width = fm.stringWidth(restart);
        int height = fm.getAscent() - fm.getDescent() / 2;

        uiManager.addObject(new UIText((int) x, (int) y, width, height, restart,
                Assets.getInstance().getFont26(), new ClickListener() {
            @Override
            public void onClick() {
                game.stop();
                Gift.stop();
                game.getMouseManager().setUiManager(null);
                game.getGameState().restart();
                StateManager.getInstance().setCurrentState(game.getGameState());
                game.start();
            }
        }));

    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, game.getDisplay().getCanvas().getWidth(), game.getDisplay().getCanvas().getHeight());
        FontMetrics fm = Text.getInstance().getFontMetricsOf(Assets.getInstance().getFont90());
        if(!info.equals("")){
            Text.getInstance().drawText(g, info, (game.getDisplay().getCanvas().getWidth() - fm.stringWidth(info)) / 2,0, 3 * game.getDisplay().getCanvas().getHeight() / 4, Color.YELLOW, Assets.getInstance().getFont90());
        }
        uiManager.render(g);
    }

}

