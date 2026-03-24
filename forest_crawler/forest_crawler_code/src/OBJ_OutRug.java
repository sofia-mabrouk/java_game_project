import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_OutRug extends SuperObject{

    public OBJ_OutRug(RenderEngine re){
        super(re);

        name = "OutRug";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/background/Maisons/Maison_nain/sol/sol_sortie.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
