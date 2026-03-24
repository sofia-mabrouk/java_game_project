import java.awt.*;

public class AssetSetter {

    RenderEngine re;

    public AssetSetter(RenderEngine re){
        this.re = re;
    }

    public void setObject(){

        re.obj[0] = new OBJ_Axe(re);
        re.obj[0].worldY = 27 * re.tileSize;
        re.obj[0].worldX = 20 * re.tileSize;

        re.obj[1] = new OBJ_OutRug(re);
        re.obj[1].worldY = 28 * re.tileSize;
        re.obj[1].worldX = 21 * re.tileSize;
    }

}
