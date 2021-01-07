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

    public GameOverState(Game game) {
        super(game);
        uiManager = new UIManager(game);
    }

    public void init(){
        game.getMouseManager().setUiManager(uiManager);
        addOptions();
    }

    private void addOptions(){
        FontMetrics fm = Text.getInstance().getFontMetricsOf(Assets.getInstance().getFont26());
        float x = (game.getDisplay().getCanvas().getWidth() - fm.stringWidth("RESTART")) / 2.0f;
        float y = (game.getDisplay().getCanvas().getHeight() - fm.getHeight()) / 2.0f + fm.getAscent() + fm.getHeight();

        int width = fm.stringWidth("RESTART");
        int height = fm.getAscent() - fm.getDescent() / 2;

        uiManager.addObject(new UIText((int) x, (int) y, width, height, "RESTART",
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
        Text.getInstance().drawText(g, "GAME OVER", (game.getDisplay().getCanvas().getWidth() - fm.stringWidth("GAME OVER")) / 2,0, game.getDisplay().getCanvas().getHeight(), Color.RED, Assets.getInstance().getFont90());
        uiManager.render(g);
    }

}

