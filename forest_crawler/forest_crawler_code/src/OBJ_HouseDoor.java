import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HouseDoor extends SuperObject{

    public OBJ_HouseDoor(RenderEngine re){
        super(re);

        name = "HouseDoor";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/background/Maisons/Maison_nain/maison_nain/TileR3C2n.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }


}