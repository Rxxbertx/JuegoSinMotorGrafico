package StartPoint;

import Inputs.MouseInput;
import Inputs.TecladoInput;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Rectangulo {

    Random random = new Random();
    int x, y, w, h;
    int speedX = 1, speedY = 1;
    Color color;

    public Rectangulo(int x, int y) {

        this.x = x;
        this.y = y;
        w = random.nextInt(50);
        h = random.nextInt(50);

    }


    public void actualizar() {

        this.x += speedX;
        this.y += speedY;


        if ((x + w) > 400 || x < 0) {
            speedX = -speedX;
            this.color = rndColor();
        }
        if ((y + h) > 400 || y < 0) {
            speedY = -speedY;
            this.color = rndColor();
        }


    }

    private Color rndColor() {

        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));


    }

    public void draw(Graphics g) {

        g.setColor(this.color);
        g.fillRect(x, y, w, h);


    }


}
