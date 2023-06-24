package ui;

import utilidades.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilidades.Constantes.UI.PauseButtons.*;

public class PauseSoundButton extends PauseButton {

    private BufferedImage[][] imgs;
    private int index;
    private boolean soundOn;

    private boolean mouseOver;


    private boolean mousePressed;
    private int rowIndex;


    public PauseSoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        init();
    }

    public void init() {
        BufferedImage temp = SaveLoad.getSpriteAtlas(SaveLoad.SOUND_BUTTONS);
        imgs = new BufferedImage[2][3];

        for (int i = 0; i < imgs.length; i++) {
            for (int j = 0; j < imgs[i].length; j++) {
                imgs[i][j] = temp.getSubimage(j * SOUND_BUTTON_SIZE_DEFAULT, i * SOUND_BUTTON_SIZE_DEFAULT, SOUND_BUTTON_SIZE_DEFAULT, SOUND_BUTTON_SIZE_DEFAULT);
            }
        }

    }

    public void render(Graphics g) {

        g.drawImage(imgs[rowIndex][index], x, y, width, height, null);

    }

    public void update() {

        index = 0;

        if (soundOn) {
            rowIndex = 0;
        } else {
            rowIndex = 1;
        }

        if (mouseOver) {
            index = 1;
        }

        if (mousePressed) {
            index = 2;
        }

    }


    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }


    void reset() {
        mouseOver = false;
        mousePressed = false;
    }
}
