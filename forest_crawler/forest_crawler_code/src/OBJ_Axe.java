import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Axe extends SuperObject{

    public OBJ_Axe(RenderEngine re){
        super(re);

        name = "Axe";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/background/Maisons/Maison_nain/hache.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

        collision = true;

    }

}
