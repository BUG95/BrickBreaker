package game.brickbraker.states;

import game.brickbraker.Game;
import game.brickbraker.gfx.Assets;
import game.brickbraker.gfx.Text;
import game.brickbraker.ui.UIManager;
import game.brickbraker.ui.UIText;
import game.brickbraker.ui.about.About;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Game game) {
        super(game);
        uiManager = new UIManager(game);
        game.getMouseManager().setUiManager(uiManager);
        addOptions();
    }

    private void addOptions(){
        FontMetrics fm = Text.getInstance().getFontMetricsOf(Assets.getInstance().getFont90());
        float x, y = 100;

        int width = fm.stringWidth("START");
        int height = fm.getAscent() - fm.getDescent() / 2;
        x = (game.getDisplay().getCanvas().getWidth() - width) / 2.0f;

        addStartOption(x, y, width, height);

        y += height;
        width = fm.stringWidth("HELP");
        x = (game.getDisplay().getCanvas().getWidth() - width) / 2.0f;

        addHelpOption(x, y, width, height);

        y += height;
        width = fm.stringWidth("ABOUT");
        x = (game.getDisplay().getCanvas().getWidth() - width) / 2.0f;

        addAboutOption(x, y, width, height);

        y += height;
        width = fm.stringWidth("QUIT");
        x = (game.getDisplay().getCanvas().getWidth() - width) / 2.0f;

        addQuitOption(x, y, width, height);
    }

    private void addStartOption(float x, float y, int width, int height){
        uiManager.addObject(new UIText((int) x, (int) y, width, height, "START",
                Assets.getInstance().getFont90(), new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUiManager(null);
                StateManager.getInstance().setCurrentState(game.getGameState());
            }
        }));
    }

    private void addHelpOption(float x, float y, int width, int height){
        uiManager.addObject(new UIText((int) x, (int) y, width, height, "HELP",
                Assets.getInstance().getFont90(), new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("HELP");
            }
        }));
    }

    private void addAboutOption(float x, float y, int width, int height){
        uiManager.addObject(new UIText((int) x, (int) y, width, height, "ABOUT",
                Assets.getInstance().getFont90(), new ClickListener() {
            @Override
            public void onClick() {
                new About();
            }
        }));
    }

    private void addQuitOption(float x, float y, int width, int height){
        uiManager.addObject(new UIText((int) x, (int) y, width, height, "QUIT",
                Assets.getInstance().getFont90(), new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(3, 190, 252));
        g.fillRect(0, 0, game.getDisplay().getCanvas().getWidth(), game.getDisplay().getCanvas().getHeight());
        uiManager.render(g);
    }
}
