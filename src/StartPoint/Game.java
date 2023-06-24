package StartPoint;

import GameStates.GameState;
import GameStates.Menu;
import GameStates.Playing;

import java.awt.*;


public class Game implements Runnable {

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5F;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    private final VentanaGame VENTANA;
    private final PanelGame PANEL;
    private final int FPS = 144;
    private final int UPS = 150;
    private Thread gameThread;

    private Playing playing;
    private Menu menu;


    //todo eso es para calcular el ancho y alto de la ventana.

    public Game() {

        //aqui lo que se hace es la inicializacion del juego

        inicializarTodo();

        PANEL = new PanelGame(this);//creamos el panel del juego
        VENTANA = new VentanaGame(PANEL);//creamos la ventana del juego y le añadimos el panel
        PANEL.requestFocus();// y hacemos que el panel tengo todo el focus

        startLoop(); //y comenzamos a inciar el loop del juego (render y update)


    }

    /**
     * Aqui lo que se hace es inicializar ciertas cosas del juego, en este caso el peronaje
     */
    private void inicializarTodo() {

        playing = new Playing(this);
        menu = new Menu(this);


    }

    /**
     * Este metodo lo que hace es establecer la tarea del loop. en este caso el metodo RUN
     */
    private void startLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * En este metodo lo que hacemos es actualizar la logica del programa, en este caso actualizamos la logica del
     * jugador
     */
    public void update() {

        switch (GameState.state) {
            case MENU -> {
                menu.update();
            }
            case PAUSE -> {
            }
            case DEAD -> {
            }
            case WIN -> {
            }
            case PLAYING -> {
                playing.update();

            }
            case QUIT -> {
                System.exit(0);
            }
            case OPTIONS -> {

            }
        }
    }

    /**
     * En este metodo actualizamos el renderizado, es decir aquello que vemos, este metodo se usa en el panel. para
     * pasarle al GAME los mismos graphics que usa el jpanel, y como el repaint del panel llama al render del juego,
     * entonces se ejecuta constantemente
     *
     * @param g graphics
     */
    public void render(Graphics g) {

        switch (GameState.state) {
            case MENU -> {
                menu.render(g);
            }
            case PAUSE -> {
            }
            case DEAD -> {
            }
            case WIN -> {
            }
            case PLAYING -> {

                playing.render(g);

            }
        }


    }


    @Override
    public void run() {

        //este metodo es super complicado entenderlo bien a la primera


        //tiempo en segundos que representa cada FRAME Y CADA UPDATE

        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;


        //tiempo en nanoSegundos el cual sirve para saber el tiempo mas antiguo

        long previous = System.nanoTime();

        //variables las cuales nos ayudan a saber cuando generar un nuevo frame o un nuevo update

        double deltaU = 0;
        double deltaF = 0;

        //conteo total de frames y updates que tiene el programa

        int frames = 0;
        int updates = 0;

        //esto es para tener guardado el tiempo anterior, y asi podemos comparar
        // esto con el nuevo tiempo, para saber el lapso entre ellos,
        // mas adelante se ve mejor

        long lastCheck = System.currentTimeMillis();

        //bucle infinito

        while (true) {


            //tiempo actual en nanosegundos

            long currentTime = System.nanoTime();


            //para saber el nivel de delta, lo que hacemos es el tiempo actual, menos el tiempo previo
            //dividido entre los segundos para mostrar un frame o update, esto es
            /*
            En estas líneas de código, se actualizan las variables deltaU y deltaF
            para llevar un seguimiento del tiempo acumulado para las actualizaciones
             y los frames, respectivamente.
             Estas variables se incrementan sumando la diferencia entre el tiempo actual
             (currentTime) y el tiempo anterior (previous) dividida por los tiempos objetivo para las actualizaciones
             (timePerUpdate) y los frames (timePerFrame).

             */

            deltaU += (currentTime - previous) / timePerUpdate;
            deltaF += (currentTime - previous) / timePerFrame;
            previous = currentTime;

            /*

            En esta sección, se verifica si el valor de deltaU ha alcanzado o superado 1,
            lo que indica que ha transcurrido suficiente tiempo para realizar una actualización.
            Si es así, se ejecuta el método update(),
            que generalmente se utiliza para actualizar la lógica del juego (movimientos, interacciones, etc.).
            Luego, se incrementa el contador de actualizaciones (updates)
            y se reduce deltaU en 1 para reflejar que se ha realizado una actualización.


             */

            if (deltaU >= 1) {

                update();
                updates++;
                deltaU--;

            }

            /*

            En este bloque, se verifica si el valor de deltaF ha alcanzado o superado 1,
            lo que indica que ha transcurrido suficiente tiempo para dibujar un nuevo frame.
            Si es así, se llama al método repaint() del panel,
            que generalmente se utiliza para solicitar que se vuelva a dibujar la interfaz gráfica del juego.
            Después, se incrementa el contador de frames (frames) y
            se reduce deltaF en 1 para reflejar que se ha dibujado un frame.

            En general, estas secciones de código aseguran que las actualizaciones y los frames
            se realicen a una frecuencia determinada por los valores de timePerUpdate y timePerFrame.
            Si el tiempo acumulado (deltaU o deltaF) supera el valor de 1,
            se ejecutan múltiples actualizaciones o se dibujan múltiples frames para mantener la sincronización deseada.
            Estos mecanismos ayudan a controlar el ritmo y la fluidez del juego,
            incluso si el tiempo transcurrido entre iteraciones del bucle varía debido a fluctuaciones de rendimiento.

             */


            if (deltaF >= 1) {

                PANEL.repaint();
                frames++;
                deltaF--;

            }


           /* if (now - lastFrame >= timePerFrame) {
                panel.repaint();
                lastFrame = now;
                frames++;
            }*/


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                // Ha pasado un segundo desde la última vez que se imprimieron los FPS y UPS
                lastCheck = System.currentTimeMillis(); // Actualiza el tiempo de la última comprobación

                // Imprime los valores de FPS y UPS en la consola
                System.out.println("FPS: " + frames + " UPS: " + updates);

                // Reinicia los contadores de frames y actualizaciones
                frames = 0;
                updates = 0;
            }

        }

    }

    /**
     * Devuelve el estado playing que se ha inicializado en esta clase
     *
     * @return playing
     */
    public Playing getPlaying() {
        return playing;
    }


    /**
     * Devuelve el estado menu que se ha inicializado en esta clase
     *
     * @return menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Esto es para cuando la ventana pierda el focus, entonces llama al metodo respectivo en jugador, el cual hace que
     * no se ejecute su movimiento y se quede parado
     */
    public void windowFocusLost() {


        if (GameState.state == GameState.PLAYING) {
            playing.windowFocusLost();
        }

    }
}
