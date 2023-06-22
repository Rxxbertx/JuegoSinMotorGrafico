package entidades;

import StartPoint.Game;
import utilidades.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilidades.HelpMethods.CanMoveHere;

import static utilidades.Constantes.PlayerConst.*;

public class Jugador extends Personaje {

    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 20;

    private float speed = 1.0f;

    private int playerAction = IDLE;
    private boolean up, down, right, left, moving, attack, jump;

    private int[][] levelData;
    private BufferedImage[][] animaciones;

    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;


    //JUMP Y GRAVITY

    private float gravity = 0.05f * Game.SCALE;
    private float airSpeed = 0.0f;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeed = 0.5f * Game.SCALE;
    private boolean inAir = false;


    public Jugador(float x, float y, float width, float height) {
        super(x, y, width, height);
        importImgs();
        initHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    /**
     * Esto es para actaulziar la logica del jugador, que tendra en cuenta su posicion
     * luego la animacion, y por ultimo la accion del jugador, si cambiamos de accion es cuando luego se actualizara la animacion, no antes
     */
    public void update() {

        updatePosition();
        // updateHitBox();
        updateAnimation();
        updatePlayerAction();


    }

    /**
     * Esto es para renderizar al jugador, es decir mostrara la animacion respectiva
     * cada cuanto se solicite el render.
     *
     * @param g
     */
    public void render(Graphics g) {

        g.drawImage(animaciones[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), (int) width, (int) height, null);
        drawHitBox(g);


    }


    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }


    /**
     * Esto es para cargar los sprites del jugador
     */
    private void importImgs() {


        animaciones = SaveLoad.getPlayerSprites();


    }

    /**
     * El método updateAnimation() es responsable de actualizar la animación del personaje en el juego. A continuación, se detalla cómo funciona:
     * <p>
     * aniTick es una variable que lleva la cuenta del tiempo transcurrido en la animación actual. Se incrementa en 1 en cada llamada al método.
     * <p>
     * aniSpeed es una variable que determina la velocidad de la animación. Indica cada cuántas actualizaciones de la animación se cambia al siguiente frame.
     * <p>
     * Si aniTick alcanza o supera el valor de aniSpeed, significa que ha pasado suficiente tiempo para avanzar al siguiente frame de la animación.
     * <p>
     * Cuando se cumple esta condición, aniTick se restablece a 0 y aniIndex, que representa el índice del frame actual, se incrementa en 1.
     * <p>
     * Luego, se verifica si aniIndex ha alcanzado o superado la cantidad total de sprites o frames para la acción del jugador (playerAction). Si es así, significa que se ha reproducido toda la animación y se debe volver al primer frame.
     * <p>
     * Si se ha completado la animación, se establece la variable attacking en falso. Esto indica que el personaje ya no está atacando, lo que puede tener implicaciones en la lógica del juego.
     * <p>
     * En resumen, el método updateAnimation() controla el avance de la animación del personaje en el juego. Cada vez que se llama al método, se incrementa un contador de tiempo y se comprueba si ha pasado suficiente tiempo para avanzar al siguiente frame de la animación. Si se completa la animación, se realiza alguna acción adicional, como establecer la variable attacking en falso.
     */
    private void updateAnimation() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpritesAmount(playerAction)) {
                aniIndex = 0;
                setAttacking(false);
            }

        }

    }


    /**
     * Saber la accion actual del personaje
     *
     * @return accion
     */

    public int getPlayerAction() {
        return playerAction;
    }

    /**
     * Comprueba la accion del usuario a realizar, si la animacion que tiene , se mantiene
     * entonce no hara falta restablecer las animaciones, en caso contrario si
     * Si el personaje se puede mover correra, si no se quedara quieto, y si ataca atacara
     */
    public void updatePlayerAction() {

        int startAni = playerAction;

        if (isMoving()) {
            this.playerAction = RUNNING;
        } else {
            this.playerAction = IDLE;
        }

        if (isAttacking()) {
            this.playerAction = ATTACK_1;
        }

        if (startAni != playerAction) {
            resetAni();
        }


    }

    /**
     * Este metodo es para resetear las animaciones
     */
    private void resetAni() {

        aniIndex = 0;
        aniTick = 0;

    }


    /**
     * Esto es para saber actualizar la posicion del personaje, dependiendo
     * de si se tiene movimiento en derecha, izquierda, arriba o abajo, y evitar que el
     * perro pulse dos teclas del mismo eje a la vez
     */
    public void updatePosition() {

        moving = false;

        if (!left && !right && !up && !down) return;


        float xSpeed = 0, ySpeed = 0;


        if (left && !right) {

            xSpeed = -speed;

        } else if (right && !left)

            xSpeed = +speed;

        if (up && !down) {
            ySpeed = -speed;

        } else if (down && !up)

            ySpeed = +speed;


       /* if (CanMoveHere(x + xSpeed, y + ySpeed, levelData, (int) width, (int) height)) {

            x += xSpeed;
            y += ySpeed;
            moving = true;

        }*/

        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, levelData, hitBox.width, hitBox.height)) {

            hitBox.x += xSpeed;
            hitBox.y += ySpeed;

            if (xSpeed != 0 || ySpeed != 0) {
                moving = true;
            }


        }


    }


    //todos esos metodos son para la direccion del personaje y moviemientos como ataque o asi

    private boolean isMoving() {
        return moving;
    }

    private boolean isAttacking() {
        return attack;
    }

    public void setAttacking(boolean attack) {
        this.attack = attack;
    }


    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * ! Metodo para que cuando la ventana pierda el focus, el jugador ya no avance
     */
    public void turnOffActions() {

        setLeft(false);
        setRight(false);
        setUp(false);
        setDown(false);
        moving = false;


    }
}
