package GameStates;

import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pause {


    private PauseOverlay pauseOverlay;


    public Pause(PauseOverlay pauseOverlay) {
        this.pauseOverlay = pauseOverlay;
    }


    public void render(Graphics g) {

        pauseOverlay.render(g);
    }
}
