package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{

    GamePanel gp;

    public ObjectKey(GamePanel gp)
    {
        name = "Key";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/key.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
