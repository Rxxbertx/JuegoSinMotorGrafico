package utilidades;

import StartPoint.Game;
import com.sun.source.tree.BreakTree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static utilidades.Constantes.PlayerConst.*;
import static utilidades.Constantes.PlayerConst.ATTACK_1;

public class SaveLoad {

    public static final String LEVEL_ATLAS = "/Treasure Hunters/Palm Tree Island/Sprites/Terrain/Terrain (32x32).png";
    public static final String LEVEL_ONE_DATA = "/Levels/level1.png";

    /**
     * Este metodo lo que hace es devolver todos los sprites relacionados con el jugador
     * es decir por cada pack de animaciones que puede tener, las devuelve en un bi array
     * para que luego se puedan recorrer facilmente
     *
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

    /**
     * Este metodo lo que hace es devolver todos los sprites relacionados con el atlas
     *
     * @param pathname ruta de la imagen
     * @return devuelve la imagen
     */
    public static BufferedImage getSpriteAtlas(String pathname) {

        BufferedImage spriteAtlas = null;

        try {
            spriteAtlas = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(pathname)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return spriteAtlas;

    }


    public static int[][] getLevelData() {
        // Crea una matriz bidimensional para almacenar los datos del nivel
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

        // Obtén la imagen del atlas de sprites correspondiente al nivel
        BufferedImage levelImg = getSpriteAtlas(LEVEL_ONE_DATA);

        // Itera sobre los píxeles de la imagen del nivel
        for (int i = 0; i < levelImg.getHeight(); i++) {
            for (int j = 0; j < levelImg.getWidth(); j++) {
                // Obtiene el color del píxel en la posición (j, i)
                Color color = new Color(levelImg.getRGB(j, i));

                // Obtiene el valor del componente rojo del color (rango de 0 a 255)
                int colorValue = color.getRed();

                // Ajusta el valor del color para que esté en el rango deseado (0 o 48)
                if (colorValue >= 48) colorValue = 0;

                // Asigna el valor del color a la posición correspondiente en la matriz del nivel
                levelData[i][j] = colorValue;
            }
        }

        // Devuelve la matriz con los datos del nivel
        return levelData;
    }


}
