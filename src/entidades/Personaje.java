package entidades;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que cuenta con variables de coordenadas, por ahora
 */
public abstract class Personaje {

    protected float x, y;
    protected float width, height;
    protected Rectangle2D.Float hitBox;

    /**
     * Creacion del personaje con sus coords
     *
     * @param x coord x
     * @param y coord y
     */
    public Personaje(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    protected void initHitBox(float x, float y, float width, float height) {
        this.hitBox = new Rectangle2D.Float(x, y, (int)width, (int)height);
    }

    /**
     * for debugging purposes
     *
     * @param g graphics
     */
    protected void drawHitBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

   /* protected void updateHitBox() {
        this.hitBox.setBounds((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }*/


    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle2D.Float hitBox) {
        this.hitBox = hitBox;
    }


}
