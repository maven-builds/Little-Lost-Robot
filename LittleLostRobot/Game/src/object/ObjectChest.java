package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject{

    GamePanel gp;

    public ObjectChest(GamePanel gp)
    {
        name = "Chest";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/chest.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
