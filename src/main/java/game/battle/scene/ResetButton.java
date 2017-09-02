package game.battle.scene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.events.constants.Key;


import java.awt.*;

/**
 * Created by Pelotita on 2/9/2017.
 */
public class ResetButton extends GameComponent<Battlefield>{

    public ResetButton(){
//        super(new Label(new Font("verdana",  Font.BOLD, 18), Color.BLUE, "mensaje","Reset", "resultado"), 0, 0);

    }

    @Override
    public void update(DeltaState deltaState) {
        if(deltaState.isKeyPressed(Key.R)) {
            this.getGame().setCurrentScene(new Battlefield());
        }
        super.update(deltaState);
    }
}
