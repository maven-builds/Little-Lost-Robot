package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends SuperObject{

    GamePanel gp;

    public ObjectDoor(GamePanel gp)
    {
        name = "Door";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/door.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        collision = true;
    }
}
