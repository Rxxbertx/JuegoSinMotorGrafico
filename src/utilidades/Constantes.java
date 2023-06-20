package utilidades;

public class Constantes {


    /**
     * Es una clase la cual tiene varias variables estaticas,
     * relacionadas con la direccion del movimiento del personaje, ahora mismo no lo uso
     */
    public static class Directions{

        public static final int LEFT =0;
        public static final int RIGHT =1;
        public static final int UP =2;
        public static final int DOWN =3;

    }


    /**
     * Esta clase lo que tiene son referencias para saber que animaciones escoger
     * es decir si selecciona Running, se ira a la fila 1 del array de animaciones
     */
    public static class PlayerConst{

        public static final int RUNNING = 1;
        public static final int IDLE = 0;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;


        /**
         * Esto es un metodo muy guay, que dependiendo de la animacion
         * escogida, retorna el numero de sprites asociados con esa animacion
         * @param player_action la accion del jugador
         * @return cantidad de sprites por accion
         */
        public static int getSpritesAmount(int player_action){

            switch (player_action){

                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                    return 3;
                case FALLING:
                    return 1;
                case GROUND:
                    return 2;
                case ATTACK_1:
                    return 3;
                case ATTACK_JUMP_1:
                    return 3;
                case ATTACK_JUMP_2:
                    return 3;
                default: return 0;


            }

        }



    }


}
