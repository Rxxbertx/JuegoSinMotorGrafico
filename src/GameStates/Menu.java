package GameStates;

import StartPoint.Game;
import ui.MenuButton;
import utilidades.Constantes;
import utilidades.SaveLoad;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static utilidades.Constantes.PlayerConst.*;

public class Menu extends State implements StateMethods {

    // Variables para el seguimiento de la posición de las imágenes
    int[] imagePositions = {Game.GAME_WIDTH, 0, Game.GAME_WIDTH}; // Inicialmente, todas las imágenes comienzan en la posición 0
    int[] imageVelocities = {-1, 1, 1}; // Velocidades de movimiento de las imágenes (puedes ajustarlas según tus necesidades)

    private MenuButton[] buttons;
    private BufferedImage background;

    private BufferedImage[] nubes;

    private BufferedImage cielo;
    private BufferedImage mar;
    private BufferedImage[] reflejoMar;
    private BufferedImage[] tierra;
    private BufferedImage palmeraRecta[];

    private final float speed = 1.0f * Game.SCALE;

    private int menuX, menuY, menuWidth, menuHeight;

    private Random random = new Random();
    private int animacion;
    private final int VELOCIDAD_animacionReflejoMar = 30;
    private int aniTick, aniTickPersonaje, aniSpeedPersonaje = 25, aniPersonaje;
    private BufferedImage[][] personaje;
    private float posXpersonaje = 50;
    private int accion = Constantes.PlayerConst.IDLE;
    private boolean personajeDerecha;
    private boolean personajeIzquierda;


    public Menu(Game game) {
        super(game);
        init();
    }

    @Override
    public void update() {

        for (MenuButton button : buttons) {
            button.update();
        }

        movimientoNubes();
        movimientoMar();
        movimientoPersonaje();


    }


    @Override
    public void render(Graphics g) {

        pintarFondo(g);
        pintarNubes(g);

        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton button : buttons) {
            button.render(g);
        }


    }


    @Override
    public void init() {

        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);

        background = SaveLoad.getSpriteAtlas(SaveLoad.MENU_BACKGROUND);
        menuWidth = (int) (background.getWidth() * Game.SCALE);
        menuHeight = (int) (background.getHeight() * Game.SCALE);
        menuX = (Game.GAME_WIDTH - menuWidth) / 2;
        menuY = (int) (45 * Game.SCALE);


        //cargar nubes

        cargarNubes();
        cargarFondo();
        cargarPersonaje();


    }


    //fondo


    private void pintarFondo(Graphics g) {

        for (int i = 0; i < Game.TILES_IN_HEIGHT / 2; i++) {


            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                g.drawImage(cielo, j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);

            }

        }
        for (int i = Game.TILES_IN_HEIGHT / 2; i < Game.TILES_IN_HEIGHT / 2 + 3; i++) {


            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {

                g.drawImage(mar, j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }

        }

        for (int i = Game.TILES_IN_HEIGHT / 2 + 3; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {

                if (i == Game.TILES_IN_HEIGHT / 2 + 3)
                    g.drawImage(tierra[0], j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
                else
                    g.drawImage(tierra[1], j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }


        g.drawImage(reflejoMar[animacion], 0, Game.TILES_SIZE * Game.TILES_IN_HEIGHT / 2, Game.GAME_WIDTH / 2, Game.TILES_SIZE, null);
        g.drawImage(palmeraRecta[animacion], Game.GAME_WIDTH - 5 * Game.TILES_SIZE, Game.TILES_SIZE * (Game.TILES_IN_HEIGHT / 2) - Game.TILES_SIZE, Game.TILES_SIZE * 3, Game.TILES_SIZE * 4, null);
        g.drawImage(palmeraRecta[animacion], Game.TILES_SIZE, Game.TILES_SIZE * (Game.TILES_IN_HEIGHT / 2), Game.TILES_SIZE * 3, Game.TILES_SIZE * 3, null);
        g.drawImage(palmeraRecta[animacion], Game.GAME_WIDTH - 5 * Game.TILES_SIZE, Game.TILES_SIZE * (Game.TILES_IN_HEIGHT / 2 + 1), Game.TILES_SIZE * 2, Game.TILES_SIZE * 2, null);

        g.drawImage(personaje[accion][aniPersonaje], (int) posXpersonaje, Game.TILES_SIZE * (Game.TILES_IN_HEIGHT / 2 + 2) - Game.TILES_SIZE / 2, Game.TILES_SIZE * 3, Game.TILES_SIZE * 2, null);



    }

    private void cargarFondo() {

        cielo = SaveLoad.getSpriteAtlas(SaveLoad.CIELO);
        mar = SaveLoad.getSpriteAtlas(SaveLoad.MAR);
        reflejoMar = SaveLoad.getReflejoMarGrande();

        BufferedImage temp = SaveLoad.getSpriteAtlas(SaveLoad.LEVEL_ATLAS);

        tierra = new BufferedImage[2];

        tierra[0] = temp.getSubimage(32, 0, 32, 32);
        tierra[1] = temp.getSubimage(32, 32, 32, 32);

        palmeraRecta = SaveLoad.getPalmeraRegular();


    }

    //mar

    private void movimientoMar() {

        aniTick++;

        if (aniTick >= VELOCIDAD_animacionReflejoMar) {

            aniTick = 0;
            animacion++;

            if (animacion > 3)

                animacion = 0;


        }


    }


    //nubes

    private void cargarNubes() {

        nubes = SaveLoad.getNubesSprites();


    }

    private void pintarNubes(Graphics g) {

        g.drawImage(nubes[3], menuX + 10, (int) (15 * Game.SCALE), menuWidth - 20, nubes[3].getHeight() - 20, null);

        for (int i = 0; i < 3; i++) {
            g.drawImage(nubes[i], imagePositions[i], (int) (10 * Game.SCALE), null);
            g.drawImage(nubes[0], imagePositions[i], (int) (50 * Game.SCALE), null);
        }
    }

    private void movimientoNubes() {

        for (int i = 0; i < 3; i++) {
            // Mueve la imagen actual hacia la derecha
            imagePositions[i] += imageVelocities[i];

            // Si la imagen ha alcanzado el borde derecho de la pantalla, vuelve a la posición inicial
            if (imagePositions[i] > Game.GAME_WIDTH) {
                imagePositions[i] = -400;
            }
            if (imagePositions[i] < -400) {
                imagePositions[i] = Game.GAME_WIDTH;
            }


        }


    }

    //personaje
    private void cargarPersonaje() {


        personaje = SaveLoad.getPlayerSprites();


    }

    private void movimientoPersonaje() {

        updateAnimationPersonaje();
        updatePosicionPersonaje();
        updateAccionPersonaje();


    }

    private void updatePosicionPersonaje() {

        float Xspeed = 0;

        if (personajeDerecha) {
            Xspeed += speed;

        }
        if (personajeIzquierda) {
            Xspeed -= speed;

        }

        posXpersonaje += Xspeed;

    }

    private void updateAccionPersonaje() {

        int startAni = accion;

        if (personajeDerecha || personajeIzquierda) {
            accion = RUNNING;
        } else {
            accion = IDLE;
        }

        if (startAni != accion) {
            aniPersonaje = 0;
            aniTickPersonaje = 0;
        }


    }

    private void updateAnimationPersonaje() {

        aniTickPersonaje++;
        if (aniTickPersonaje >= aniSpeedPersonaje) {
            aniTickPersonaje = 0;
            aniPersonaje++;
            if (aniPersonaje >= getSpritesAmount(accion)) {
                aniPersonaje = 0;

            }

        }

    }


    @Override
    public void keyPressed(KeyEvent e) {


        switch (e.getKeyCode()) {

            case KeyEvent.VK_ENTER:
                GameState.state = GameState.PLAYING;

                break;


            case KeyEvent.VK_A:
                personajeIzquierda = true;

                break;
            case KeyEvent.VK_D:
                personajeDerecha = true;
                break;


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                personajeIzquierda = false;

                break;
            case KeyEvent.VK_D:

                personajeDerecha = false;

                break;

        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

        for (MenuButton button : buttons) {
            if (isMouseIn(e, button)) {

                if (button.isMousePressed()) {
                    button.applyGameState();
                    break;
                }

            }
        }
        resetButtons();


    }

    private void resetButtons() {
        for (MenuButton button : buttons) {
            button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        for (MenuButton button : buttons) {
            if (isMouseIn(e, button)) {
                button.setMouseOver(true);
            } else {
                button.setMouseOver(false);
            }
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        for (MenuButton button : buttons) {
            if (isMouseIn(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }

    }
}
