package GameStates;

import StartPoint.Game;
import entidades.Jugador;
import levels.LevelManager;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    private LevelManager levelManager;
    private Jugador jugador;


    private boolean paused = false;
    private PauseOverlay pauseOverlay;


    public Playing(Game game) {
        super(game);
        init();
    }

    @Override
    public void update() {

        if (!paused) {
            jugador.update();
            levelManager.update();
        } else {
            pauseOverlay.update();
        }

    }

    @Override
    public void render(Graphics g) {


        jugador.render(g);
        levelManager.draw(g);

        if (paused) {

            pauseOverlay.render(g);
        }


    }

    @Override
    public void init() {

        pauseOverlay = new PauseOverlay(this);
        levelManager = new LevelManager(game);
        jugador = new Jugador(100 * Game.SCALE, Game.GAME_HEIGHT / 2, 64 * Game.SCALE, 40 * Game.SCALE);
        jugador.loadLevelData(levelManager.getCurrentLevel().getLvlData());

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                getJugador().setLeft(true);

                break;
            case KeyEvent.VK_D:
                getJugador().setRight(true);

                break;
            case KeyEvent.VK_W:
                //getJugador().setUp(true);

                break;
            case KeyEvent.VK_S:

                // getJugador().setDown(true);


                break;
            case KeyEvent.VK_SPACE:

                getJugador().setJump(true);
                getJugador().setAttacking(false);


                break;

            case KeyEvent.VK_ESCAPE:

                paused = !paused;

                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                getJugador().setLeft(false);

                break;
            case KeyEvent.VK_D:
                getJugador().setRight(false);

                break;
            case KeyEvent.VK_W:
                // getJugador().setUp(false);

                break;
            case KeyEvent.VK_S:

                //getJugador().setDown(false);

                break;
            case KeyEvent.VK_SPACE:

                getJugador().setJump(false);


                break;

        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && !paused) {

            getJugador().setAttacking(true);


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (paused) pauseOverlay.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (paused) pauseOverlay.mouseMoved(e);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (paused) pauseOverlay.mouseDragged(e);
    }

    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if (paused) pauseOverlay.mousePressed(e);

    }


    /**
     * Devuelve el jugador que se ha inicializado en esta clase
     *
     * @return jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Esto es para cuando la ventana pierda el focus, entonces llama al metodo respectivo en jugador, el cual hace que
     * no se ejecute su movimiento y se quede parado
     */
    public void windowFocusLost() {

        getJugador().turnOffActions();

    }


    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


}
