package Inputs;

import StartPoint.PanelGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private final PanelGame panelGame;

    public MouseInput(PanelGame panelGame) {

        this.panelGame = panelGame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


        //si he realizado clic, entonces habilito la accion de atacar en el jugador

        if (e.getButton() == MouseEvent.BUTTON1) {
            panelGame.getGame().getJugador().setAttacking(true);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }
}
