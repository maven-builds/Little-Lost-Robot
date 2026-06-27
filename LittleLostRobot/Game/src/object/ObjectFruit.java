package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectFruit extends SuperObject{

    GamePanel gp;

    public ObjectFruit(GamePanel gp)
    {
        name = "Violet Berry";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/violetberry.png"));
            toolBox.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
