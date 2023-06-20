package utilidades;

import com.sun.source.tree.BreakTree;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static utilidades.Constantes.PlayerConst.*;
import static utilidades.Constantes.PlayerConst.ATTACK_1;

public class SaveLoad {


    /**
     * Este metodo lo que hace es devolver todos los sprites relacionados con el jugador
     * es decir por cada pack de animaciones que puede tener, las devuelve en un bi array
     * para que luego se puedan recorrer facilmente
     * @return
     */
    public static BufferedImage[][] getPlayerSprites() {


        final String imageCaptainPath = "/Treasure Hunters/Captain Clown Nose/Sprites/Captain Clown Nose/Captain Clown Nose with Sword/";


        //Captain Idle

        BufferedImage[][] animaciones = new BufferedImage[9][6];


        for (int i = 0; i < 5; i++) {
            try {
                animaciones[IDLE][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "09-Idle Sword/Idle Sword 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        //Captain Run


        for (int i = 0; i < 6; i++) {
            try {
                animaciones[RUNNING][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "10-Run Sword/Run Sword 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        //Captain Jump

        for (int i = 0; i < 3; i++) {
            try {
                animaciones[JUMP][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "11-Jump Sword/Jump Sword 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        //Captain attack

        for (int i = 0; i < 3; i++) {
            try {
                animaciones[ATTACK_1][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "15-Attack 1/Attack 1 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }


        return animaciones;

    }


}
