package entidades;

/**
 * Clase abstracta que cuenta con variables de coordenadas, por ahora
 */
public abstract class Personaje {

    protected float x,y;

    /**
     * Creacion del personaje con sus coords
     * @param x coord x
     * @param y coord y
     */
    public Personaje( float x, float y) {

        this.x=x;
        this.y=y;



    }
}
