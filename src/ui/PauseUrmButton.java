package ui;

import utilidades.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilidades.Constantes.UI.PauseButtons.*;

public class PauseUrmButton extends PauseButton {

    public static final int RESUME = 0;
    public static final int RESTART = 1;
    public static final int HOME = 2;

    private BufferedImage[] imgs;
    private int type;

    private int index;
    private boolean mouseOver, mousePressed;


    public PauseUrmButton(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        init();
    }

    public void init() {


        BufferedImage temp = SaveLoad.getSpriteAtlas(SaveLoad.URM_BUTTONS);
        imgs = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            imgs[i] = temp.getSubimage(i * URM_BUTTON_SIZE_DEFAULT, type * URM_BUTTON_SIZE_DEFAULT, URM_BUTTON_SIZE_DEFAULT, URM_BUTTON_SIZE_DEFAULT);
        }


    }

    public void render(Graphics g) {

        g.drawImage(imgs[index], x, y, width, height, null);

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
