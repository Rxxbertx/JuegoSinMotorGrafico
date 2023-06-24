package GameStates;

import StartPoint.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {


    protected Game game;

    public State(Game game) {
        this.game = game;
    }


    public boolean isMouseIn(MouseEvent e, MenuButton button) {

        return button.getBounds().contains(e.getX(), e.getY());

    }

    public Game getGame() {
        return game;
    }
}

