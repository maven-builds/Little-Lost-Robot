package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectCorn extends SuperObject{

    public ObjectCorn()
    {
        name = "Corn";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/corn.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
