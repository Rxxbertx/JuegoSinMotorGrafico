package utilidades;

import StartPoint.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, int[][] lvlData, float width, float height) {


        if (!IsSolid(x, y, lvlData)) if (!IsSolid(x + width, y + height, lvlData))
            if (!IsSolid(x + width, y, lvlData)) if (!IsSolid(x, y + height, lvlData)) return true;

        return false;


    }


    private static boolean IsSolid(float x, float y, int[][] lvlData) {

        if (x < 0 || x >= Game.GAME_WIDTH) return true;
        if (y < 0 || y >= Game.GAME_HEIGHT) return true;


        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }
        return false;


    }


    public static float getEntityPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {


        int xIndex = (int) (hitbox.x / Game.TILES_SIZE);

        if (xSpeed > 0) {
            int x = xIndex * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return x + xOffset - 1;
        } else {
            return xIndex * Game.TILES_SIZE;
        }


    }


    public static float getEntityPosUnderOrAbove(Rectangle2D.Float hitbox, float ySpeed) {

        int yIndex = (int) (hitbox.y / Game.TILES_SIZE);

        if (ySpeed > 0) {
            int y = yIndex * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return y + yOffset - 1;
        } else {
            return yIndex * Game.TILES_SIZE;
        }


    }
    public static boolean IsEntityOnTheGround(Rectangle2D.Float hitBox, int[][] levelData) {

        //check pixel bottom left and bottom right

        if (!IsSolid(hitBox.x, hitBox.y + hitBox.height+1, levelData)) {
            if (!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height+1, levelData)) {
                return false;
            }
        }
        return true;

    }


}
