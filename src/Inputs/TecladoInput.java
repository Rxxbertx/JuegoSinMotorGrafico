package Inputs;

import StartPoint.PanelGame;
import entidades.Jugador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilidades.Constantes.Directions.*;


public class TecladoInput implements KeyListener {

    private final PanelGame panelGame;

    //constructor al cual le pasamos el panel del juego, ya que este panel implementa este input, y admeas nos hace falta para acceder al juego y despues al jugador, todo genial
    public TecladoInput(PanelGame panelGame) {

        this.panelGame = panelGame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //dependiendo de la tecla detectada, habilito el rumbo que tiene que tomar el jugador

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                panelGame.getGame().getJugador().setLeft(true);

                break;
            case KeyEvent.VK_D:
                panelGame.getGame().getJugador().setRight(true);

                break;
            case KeyEvent.VK_W:
                panelGame.getGame().getJugador().setUp(true);

                break;
            case KeyEvent.VK_S:

                panelGame.getGame().getJugador().setDown(true);


                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //dependiendo de la tecla que he dejado de presionar, hago que el personaje deje de avanzar en la direccion especifica

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                panelGame.getGame().getJugador().setLeft(false);

                break;
            case KeyEvent.VK_D:
                panelGame.getGame().getJugador().setRight(false);

                break;
            case KeyEvent.VK_W:
                panelGame.getGame().getJugador().setUp(false);

                break;
            case KeyEvent.VK_S:

                panelGame.getGame().getJugador().setDown(false);

                break;

        }


    }
}
