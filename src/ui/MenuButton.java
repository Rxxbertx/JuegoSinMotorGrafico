package ui;

import GameStates.GameState;
import StartPoint.Game;
import utilidades.SaveLoad;

import static utilidades.Constantes.UI.Buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {

    private int xPos;
    private int yPos;
    private int rowIndex;

    private int index;

    private GameState state;

    private BufferedImage[] imgs;
    private int xOffsetCenter = (BUTTON_WIDTH) / 2;

    private boolean mouseOver;
    private boolean mousePressed;


    private Rectangle bounds;


    public MenuButton(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();

        initBounds();


    }

    private void initBounds() {

        bounds = new Rectangle(xPos - xOffsetCenter, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);

    }

    private void loadImgs() {

        imgs = new BufferedImage[3];

        BufferedImage temp = SaveLoad.getSpriteAtlas(SaveLoad.MENU_BUTTONS);

        for (int i = 0; i < imgs.length; i++) {

            imgs[i] = temp.getSubimage(i * BUTTON_WIDTH_DEFAULT, rowIndex * BUTTON_HEIGHT_DEFAULT, BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);

        }


    }


    public void render(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, null);
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


    public void applyGameState() {
        GameState.state = this.state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }


    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }


    public Rectangle getBounds() {
        return bounds;
    }
}
