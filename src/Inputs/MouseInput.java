package Inputs;

import GameStates.GameState;
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

        switch (GameState.state) {

            case MENU:

                panelGame.getGame().getMenu().mouseClicked(e);

                break;
            case PLAYING:

                panelGame.getGame().getPlaying().mouseClicked(e);


                break;
            case PAUSE:
                break;
            case DEAD:
                break;
            case WIN:
                break;

        }


    }

    @Override
    public void mousePressed(MouseEvent e) {


        switch (GameState.state) {

            case MENU:

                panelGame.getGame().getMenu().mousePressed(e);

                break;
            case PLAYING:

                panelGame.getGame().getPlaying().mousePressed(e);


                break;
            case PAUSE:
                break;
            case DEAD:
                break;
            case WIN:
                break;

        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (GameState.state) {

            case MENU:

                panelGame.getGame().getMenu().mouseReleased(e);

                break;
            case PLAYING:

                panelGame.getGame().getPlaying().mouseReleased(e);


                break;
            case PAUSE:
                break;
            case DEAD:
                break;
            case WIN:
                break;

        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        switch (GameState.state) {

            case MENU:

                panelGame.getGame().getMenu().mouseDragged(e);

                break;
            case PLAYING:

                panelGame.getGame().getPlaying().mouseDragged(e);


                break;
            case PAUSE:
                break;
            case DEAD:
                break;
            case WIN:
                break;

        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch (GameState.state) {

            case MENU:

                panelGame.getGame().getMenu().mouseMoved(e);

                break;
            case PLAYING:

                panelGame.getGame().getPlaying().mouseMoved(e);


                break;
            case PAUSE:
                break;
            case DEAD:
                break;
            case WIN:
                break;

        }


    }
}
