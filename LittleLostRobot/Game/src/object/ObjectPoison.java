package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectPoison extends SuperObject{

    GamePanel gp;

    public ObjectPoison(GamePanel gp)
    {
        name = "Poison";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/poison.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
