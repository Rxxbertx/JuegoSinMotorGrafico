package ui;

import utilidades.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilidades.Constantes.UI.PauseButtons.*;

public class PauseVolumeButton extends PauseButton {

    private BufferedImage[] imgs;

    private BufferedImage slider;
    private int type;

    private int index = 0;
    private boolean mouseOver, mousePressed;

    private int buttonX;
    private int minSliderX, maxSliderX;

    public PauseVolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        getBounds().x -= VOLUME_WIDTH/2;
        this.x = x;
        this.width = width;
        minSliderX = x + VOLUME_WIDTH / 2;
        maxSliderX = x + width - VOLUME_WIDTH / 2;
        init();
    }


    public void init() {

        buttonX = x + width / 2;

        BufferedImage temp = SaveLoad.getSpriteAtlas(SaveLoad.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }

        slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    public void render(Graphics g) {

        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(imgs[index], buttonX-VOLUME_WIDTH/2, y, VOLUME_WIDTH, height, null);

    }

    public void update() {

        index = 0;

        if (mouseOver) {
            index = 1;
        }

        if (mousePressed) {
            index = 2;
        }


    }

    public void changeX(int x) {

        if (x < minSliderX) {
            buttonX = minSliderX;
        } else if (x > maxSliderX) {
            buttonX = maxSliderX;
        } else {
            buttonX = x;
        }

        getBounds().x = buttonX;

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


    protected void reset() {
        mouseOver = false;
        mousePressed = false;
    }

}
