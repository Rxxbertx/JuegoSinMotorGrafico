package GameStates;

import Inputs.MouseInput;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    void update();

    void render(Graphics g);

    void init();

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);

    void mouseClicked(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseMoved(MouseEvent e);

    void mouseDragged(MouseEvent e);

    void mousePressed(MouseEvent e);

}
