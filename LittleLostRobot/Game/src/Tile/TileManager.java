package Tile;

import main.GamePanel;
import main.ToolBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp; // We bring in the GamePanel class
    public Tile[] tile; // We create an array for our tiles
    public int mapTileNum[][]; // This array will manage tile mapping

    // We set up a TileManager constructor

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10]; // This will create a line of 10 tiles, and if we need more, we can update the array
        mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/GameMap.txt");
    }

    public void getTileImage()
    {

            setUp(0, "grass", false);
            setUp(1, "wall", true);
            setUp(2, "water1", true);
            setUp(3, "earth", false);
            setUp(4, "sand1", false);
            setUp(5, "tree1", true);

            // One way, albeit inefficiently, to scale images

            /* BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType()); // BufferedImage is first instantiated
            Graphics2D g2 = scaledImage.createGraphics(); // This saves whatever g2 draws in scaledImage
            g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null); // This draws the grass (tile[0]) into the scaledImage variable Graphics 2D is now linked to
            tile[0].image = scaledImage; */
    }

    // A method to apply image scaling to each tile

    public void setUp(int index, String imageName, boolean collision)
    {
        ToolBox toolBox = new ToolBox();

        try {
            tile[index] = new Tile(); // This can be applied to any tile
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")); // This can be applied to any image location
            tile[index].image = toolBox.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // A dedicated function for tile mapping

    public void loadMap(String filePath)
    {
        try{

            InputStream is = getClass().getResourceAsStream(filePath); // "Imports" our tile map
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // "Reads" our tile map

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldColumn && row < gp.maxWorldRow)
            {
                String line = br.readLine(); // This reads a single line of text

                while (col < gp.maxWorldColumn)
                {
                    String numbers[] = line.split(" "); // Splits a string around matches of a given expression
                    int num = Integer.parseInt(numbers[col]); // 'Col' is used to index the numbers[] array. This line converts the string to an integer
                    mapTileNum[col][row] = num; // We "extract" the numbers into mapTileNum[][], indexing 'col' and 'row'
                    col++; // We increase col by 1. This ensures that every value in numbers[] is store in mapTileNum[][]
                }

                if (col == gp.maxWorldColumn)
                {
                    col = 0; // Col resets to 0 if it meets the limit of the screen column
                    row++; // Row increases by 1 if the limit is met
                }
            }
            br.close();

        }catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldColumn && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow]; // Map data is stored in mapTileNum[][]

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // This is the player's X-coordinate on the screen
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // This is the player's Y-coordinate on the screen

            // Only render tiles displayed

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null); // This function leaves image scaling to Graphics (2D)
            }

            worldCol++; // Increase the column number by 1

            if (worldCol == gp.maxWorldColumn)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
