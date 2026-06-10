package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends SuperObject{

    public ObjectChest()
    {
        name = "Chest";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/chest.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
