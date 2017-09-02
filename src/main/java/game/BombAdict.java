package game;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import game.battle.scene.Battlefield;

import java.awt.*;
/**
 * Created by sergio on 27/07/17.
 */
public class BombAdict extends Game {

    //Main method
    public static void main(String[] args) {
        new DesktopGameLauncher(new BombAdict()).launch();
    }

    @Override
    protected void initializeResources() {}

    @Override
    public void setUpScenes() {
        setCurrentScene(new Battlefield());
    }

    @Override
    public Dimension getDisplaySize() {
        return new Dimension(795,598);
    }

    @Override
    public String getTitle() {
        return "Let the explosions begins";
    }
}
