package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectBoots extends SuperObject{

    public ObjectBoots()
    {
        name = "Boots";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/boots.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
