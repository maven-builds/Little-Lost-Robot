package object;

import main.GamePanel;
import main.ToolBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0,0, 48, 48); // Creating a rectangle for our solidArea variables, fitting the game window

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    ToolBox  toolBox = new ToolBox();

    public void draw(Graphics2D g2, GamePanel gp)
    {
        // This allows us to draw objects onto the screen based on the player's position

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // This is the player's X-coordinate on the screen
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // This is the player's Y-coordinate on the screen

        // Only render tiles displayed

        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
