package utilidades;

import StartPoint.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static utilidades.Constantes.PlayerConst.*;


public class SaveLoad {

    public static final String LEVEL_ATLAS = "/Treasure Hunters/Palm Tree Island/Sprites/Terrain/Terrain (32x32).png";
   // public static final String LEVEL_ONE_DATA = "/Levels/level1.png";
    public static final String LEVEL_ONE_LONG_DATA = "/Levels/level1long.png";
    public static final String MENU_BUTTONS = "/UI/MENU/button_atlas.png";
    public static final String MENU_BACKGROUND = "/UI/MENU/menu_background.png";

    public static final String ISLAND_BACKGROUND = "/Treasure Hunters/Palm Tree Island/Sprites/Background/BG Image.png";
    public static final String PAUSE_MENU = "/UI/PAUSE/pause_menu.png";
    public static final String SOUND_BUTTONS = "/UI/PAUSE/sound_button.png";
    public static final String URM_BUTTONS = "/UI/PAUSE/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "/UI/PAUSE/volume_buttons.png";
    public static final String MAR = "/Treasure Hunters/Palm Tree Island/Sprites/Background/Additional Water.png";
    public static final String CIELO = "/Treasure Hunters/Palm Tree Island/Sprites/Background/Additional Sky.png";


    public static BufferedImage[] getNubesSprites() {

        BufferedImage[] nubes = new BufferedImage[4];

        for (int i = 0; i < 3; i++) {
            try {
                nubes[i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource("/Treasure Hunters/Palm Tree Island/Sprites/Background/Small Cloud " + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        try {
            nubes[3] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource("/Treasure Hunters/Palm Tree Island/Sprites/Background/Big Clouds.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return nubes;

    }


    /**
     * Este metodo lo que hace es devolver todos los sprites relacionados con el jugador es decir por cada pack de
     * animaciones que puede tener, las devuelve en un bi array para que luego se puedan recorrer facilmente
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


        //Captain attack

        for (int i = 0; i < 3; i++) {
            try {
                animaciones[ATTACK_JUMP_1][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "18-Air Attack 1/Air Attack 1 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }


//Captain fall
        for (int i = 0; i < 1; i++) {
            try {
                animaciones[FALLING][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "12-Fall Sword/Fall Sword 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }


        //Captain GROUND
        for (int i = 0; i < 2; i++) {
            try {
                animaciones[GROUND][i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource(imageCaptainPath + "13-Ground Sword/Ground Sword 0" + (i + 1) + ".png")));
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

        // Obtén la imagen del atlas de sprites correspondiente al nivel
        BufferedImage levelImg = getSpriteAtlas(LEVEL_ONE_LONG_DATA);

// Crea una matriz bidimensional para almacenar los datos del nivel
        int[][] levelData = new int[levelImg.getHeight()][levelImg.getWidth()];

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


    public static BufferedImage[] getReflejoMarGrande() {

        BufferedImage[] reflejoMar = new BufferedImage[4];


        for (int i = 0; i < 4; i++) {
            try {
                reflejoMar[i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource("/Treasure Hunters/Palm Tree Island/Sprites/Background/Water Reflect Big 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        return reflejoMar;
    }


    public static BufferedImage[] getPalmeraRegular() {

        BufferedImage[] temp = new BufferedImage[4];


        for (int i = 0; i < 4; i++) {
            try {
                temp[i] = ImageIO.read(Objects.requireNonNull(SaveLoad.class.getResource("/Treasure Hunters/Palm Tree Island/Sprites/Back Palm Trees/Back Palm Tree Regular 0" + (i + 1) + ".png")));
            } catch (IOException e) {
                // Manejar la excepción
                e.printStackTrace();
            }
        }

        return temp;
    }

}
