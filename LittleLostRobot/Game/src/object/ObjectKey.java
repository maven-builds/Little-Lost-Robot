package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{

    public ObjectKey()
    {
        name = "Key";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/key.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        solidArea.x = 5;
    }
}
