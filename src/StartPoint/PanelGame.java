package StartPoint;

import Inputs.MouseInput;
import Inputs.TecladoInput;

import javax.swing.*;
import java.awt.*;


public class PanelGame extends JPanel {

    private final Game game;

    // Constructor de PanelGame
    public PanelGame(Game game) {

        // Crea una instancia de MouseInput y le pasa esta instancia de PanelGame
        MouseInput mouseInput = new MouseInput(this);
        this.game = game; // Asigna el juego proporcionado al miembro game de la clase
        setPanelSize(); // Establece el tamaño preferido del panel de juego
        addKeyListener(new TecladoInput(this)); // Agrega un TecladoInput para capturar eventos de teclado
        addMouseListener(mouseInput); // Agrega un MouseInput para capturar eventos de ratón
        addMouseMotionListener(mouseInput); // Agrega un MouseInput para capturar eventos de movimiento de ratón
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g); // Llama al método render() del juego para dibujar en el panel
    }

    private void setPanelSize() {
        // Establece el tamaño preferido del panel de juego a las dimensiones del juego
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
    }

    public Game getGame() {
        return game; // Devuelve la instancia del juego
    }

    // Método para spawnear un rectángulo en una posición específica
    // Este método está comentado y no se usa en el código actual
    /*
    public void spawnearRect(int x, int y) {
        rectangulos.add(new Rectangulo(x, y));
    }
    */
}

