package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends SuperObject{

    public ObjectDoor()
    {
        name = "Door";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/door.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        collision = true;
    }
}
