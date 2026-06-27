package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectBoots extends SuperObject{

    GamePanel gp;

    public ObjectBoots(GamePanel gp)
    {
        name = "Boots";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/boots.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
