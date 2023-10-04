package GameStates;

import StartPoint.Game;
import entidades.Jugador;
import levels.LevelManager;
import ui.PauseOverlay;
import utilidades.SaveLoad;

import static utilidades.Constantes.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Playing extends State implements StateMethods {

    private LevelManager levelManager;
    private Jugador jugador;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    private BufferedImage background;

    private BufferedImage[] nubes;

    private int[] nubesPequesY;
    private Random random = new Random();
    //
    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = SaveLoad.getLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;


    public Playing(Game game) {
        super(game);
        init();
    }

    @Override
    public void update() {

        if (!paused) {

            jugador.update();
            checkCloseToBorder();
            levelManager.update();


        } else {
            pauseOverlay.update();
        }

    }


    private void checkCloseToBorder() {
        int playerX = (int) jugador.getHitBox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder) xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder) xLvlOffset += diff - leftBorder;

        if (xLvlOffset > maxLvlOffsetX) xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0) xLvlOffset = 0;

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        renderClouds(g);
        levelManager.draw(g, xLvlOffset);
        jugador.render(g, xLvlOffset);

        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.render(g);
        }


    }

    @Override
    public void init() {


        nubes = SaveLoad.getNubesSprites();
        background = SaveLoad.getSpriteAtlas(SaveLoad.ISLAND_BACKGROUND);
        levelManager = new LevelManager(game);
        jugador = new Jugador(100 * Game.SCALE, Game.GAME_HEIGHT / 2, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        jugador.loadLevelData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay = new PauseOverlay(this);

        nubesPequesY = new int[8];
        for (int i = 0; i < nubesPequesY.length; i++) {

            nubesPequesY[i] = (int) (90 * Game.SCALE + random.nextInt((int) (100 * Game.SCALE)));

        }



    }


    public void renderClouds(Graphics g) {


        for (int i = 0; i < 3; i++) {

            g.drawImage(nubes[3], i * Environment.BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int) (204 * Game.SCALE), Environment.BIG_CLOUD_WIDTH, Environment.BIG_CLOUD_HEIGHT, null);

        }

        for (int i = 0; i < nubesPequesY.length; i++) {

            g.drawImage(nubes[1], Environment.SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), nubesPequesY[i], Environment.SMALL_CLOUD_WIDTH, Environment.SMALL_CLOUD_HEIGHT, null);


        }


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
