package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectFruit extends SuperObject{

    public ObjectFruit()
    {
        name = "Violet Berry";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ_Sprites/violetberry.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
