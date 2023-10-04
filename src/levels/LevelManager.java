package levels;

import StartPoint.Game;
import utilidades.SaveLoad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private Level currentLevel;
    private Level[] levels;
    private BufferedImage[] levelSprite;

    private Level level1;

    public LevelManager(Game game) {


        this.game = game;
        importImgs();
        level1 = new Level(SaveLoad.getLevelData());

    }

    public void importImgs() {

        levelSprite = new BufferedImage[48];
        BufferedImage img = SaveLoad.getSpriteAtlas(SaveLoad.LEVEL_ATLAS);
        int componentCount = 0; // Contador de componentes no vacíos

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 17; j++) {
                BufferedImage componentImage = img.getSubimage(j * 32, i * 32, 32, 32);


                if (!isComponentEmpty(componentImage)) {
                    // El componente no está vacío
                    levelSprite[componentCount] = componentImage;
                    componentCount++; // Incrementar el contador de componentes no vacíos
                } else if (componentCount == 11) {
                    levelSprite[componentCount] = componentImage;
                    componentCount++;
                }


            }
        }


    }


    private static boolean isComponentEmpty(BufferedImage componentImage) {
        int transparentPixel = 0x00000000;

        // Iterar sobre los píxeles de la subimagen del componente
        for (int y = 0; y < componentImage.getHeight(); y++) {
            for (int x = 0; x < componentImage.getWidth(); x++) {
                int pixel = componentImage.getRGB(x, y);
                if (pixel != transparentPixel) {
                    // Se encontró un píxel no vacío, el componente no está vacío
                    return false;
                }
            }
        }

        // Todos los píxeles son transparentes, el componente está vacío
        return true;
    }


    public void draw(Graphics g, int lvlOffest) {


        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < level1.getLvlData()[0].length; j++) {
                int index = level1.getSpriteIndex(j, i);
                g.drawImage(levelSprite[index], j * Game.TILES_SIZE - lvlOffest, i * Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);

            }
        }


    }

    public void update() {

    }


    public Level getCurrentLevel() {

        return level1;

    }
}
