package StartPoint;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class VentanaGame extends JFrame {

    public VentanaGame(PanelGame panel) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre de la ventana al cerrarla
        getContentPane().add(panel); // Agrega el panel de juego al contenido de la ventana
        pack(); // Ajusta el tamaño de la ventana según el contenido
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        //setResizable(false); // Evita que la ventana sea redimensionable
        setVisible(true); // Hace visible la ventana

        // Agrega un WindowFocusListener para detectar cambios de enfoque en la ventana
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Acciones a realizar cuando la ventana gana el enfoque (no se realiza ninguna acción aquí)
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Acciones a realizar cuando la ventana pierde el enfoque
                panel.getGame().windowFocusLost(); // Llama al método windowFocusLost() del juego asociado al panel
            }
        });
    }

}
