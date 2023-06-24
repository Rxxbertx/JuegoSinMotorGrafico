package ui;

import GameStates.GameState;
import GameStates.Playing;
import GameStates.StateMethods;
import StartPoint.Game;
import utilidades.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilidades.Constantes.UI.PauseButtons.*;

public class PauseOverlay implements StateMethods {


    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;
    private PauseSoundButton soundButton, musicButton;
    private PauseUrmButton homeButton, restartButton, resumeButton;
    private Playing playing;

    private PauseVolumeButton volumeButton;

    public PauseOverlay(Playing playing) {

        this.playing = playing;
        init();


    }


    /**
     *
     */
    @Override
    public void update() {


        soundButton.update();
        musicButton.update();
        homeButton.update();
        restartButton.update();
        resumeButton.update();
        volumeButton.update();

    }

    public void render(Graphics g) {


        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);
        soundButton.render(g);
        musicButton.render(g);
        homeButton.render(g);
        restartButton.render(g);
        resumeButton.render(g);
        volumeButton.render(g);


    }


    public void init() {

        background = SaveLoad.getSpriteAtlas(SaveLoad.PAUSE_MENU);
        menuWidth = (int) (background.getWidth() * Game.SCALE);
        menuHeight = (int) (background.getHeight() * Game.SCALE);
        menuX = (Game.GAME_WIDTH - menuWidth) / 2;
        menuY = (int) (25 * Game.SCALE);

        musicButton = new PauseSoundButton((int) (450 * Game.SCALE), (int) (140 * Game.SCALE), SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);
        soundButton = new PauseSoundButton((int) (450 * Game.SCALE), (int) (186 * Game.SCALE), SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);

        //

        homeButton = new PauseUrmButton((int) (313 * Game.SCALE), (int) (325 * Game.SCALE), URM_BUTTON_SIZE, URM_BUTTON_SIZE, PauseUrmButton.HOME);
        resumeButton = new PauseUrmButton((int) (462 * Game.SCALE), (int) (325 * Game.SCALE), URM_BUTTON_SIZE, URM_BUTTON_SIZE, PauseUrmButton.RESUME);
        restartButton = new PauseUrmButton((int) (387 * Game.SCALE), (int) (325 * Game.SCALE), URM_BUTTON_SIZE, URM_BUTTON_SIZE, PauseUrmButton.RESTART);

        //

        volumeButton = new PauseVolumeButton((int) (309 * Game.SCALE), (int) (278 * Game.SCALE), SLIDER_WIDTH, VOLUME_HEIGHT);

    }

    public boolean isMouseIn(MouseEvent e, PauseButton button) {

        return button.getBounds().contains(e.getX(), e.getY());

    }


    /**
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void mouseClicked(MouseEvent e) {


    }


    public void mouseReleased(MouseEvent e) {

        if (isMouseIn(e, soundButton))

            if (soundButton.isMousePressed())

                soundButton.setSoundOn(!soundButton.isSoundOn());


        if (isMouseIn(e, musicButton))

            if (musicButton.isMousePressed())

                musicButton.setSoundOn(!musicButton.isSoundOn());


        if (isMouseIn(e, homeButton))

            if (homeButton.isMousePressed()) {
                GameState.state = GameState.MENU;
                playing.setPaused(false);
            }


        if (isMouseIn(e, resumeButton))

            if (resumeButton.isMousePressed())

                playing.setPaused(false);

        if (isMouseIn(e, restartButton))

            if (restartButton.isMousePressed())

                GameState.state = GameState.PLAYING;


        homeButton.reset();
        resumeButton.reset();
        restartButton.reset();
        soundButton.reset();
        musicButton.reset();
        volumeButton.reset();

    }


    public void mouseMoved(MouseEvent e) {

        soundButton.setMouseOver(false);
        musicButton.setMouseOver(false);
        homeButton.setMouseOver(false);
        resumeButton.setMouseOver(false);
        restartButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isMouseIn(e, soundButton)) soundButton.setMouseOver(true);

        if (isMouseIn(e, musicButton)) musicButton.setMouseOver(true);

        if (isMouseIn(e, homeButton)) homeButton.setMouseOver(true);

        if (isMouseIn(e, resumeButton)) resumeButton.setMouseOver(true);

        if (isMouseIn(e, restartButton)) restartButton.setMouseOver(true);

        if (isMouseIn(e, volumeButton)) volumeButton.setMouseOver(true);


    }


    public void mouseDragged(MouseEvent e) {

        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }

    }

    /**
     * @param e
     */

    public void mousePressed(MouseEvent e) {

        if (isMouseIn(e, soundButton)) {
            soundButton.setMousePressed(true);
        }
        if (isMouseIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        }
        if (isMouseIn(e, homeButton)) {
            homeButton.setMousePressed(true);
        }
        if (isMouseIn(e, resumeButton)) {
            resumeButton.setMousePressed(true);
        }
        if (isMouseIn(e, restartButton)) {
            restartButton.setMousePressed(true);
        }
        if (isMouseIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }

    }


}
