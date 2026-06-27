package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectCorn extends SuperObject{

    GamePanel gp;

    public ObjectCorn(GamePanel gp)
    {
        name = "Corn";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/corn.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
