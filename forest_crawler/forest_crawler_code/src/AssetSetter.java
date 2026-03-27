import java.awt.*;

public class AssetSetter {

    RenderEngine re;

    public AssetSetter(RenderEngine re){
        this.re = re;
    }

    public void setObject(){

        re.obj[0] = new OBJ_Axe(re);
        re.obj[0].worldY = 18 * re.tileSize;
        re.obj[0].worldX = 22 * re.tileSize;

        re.obj[1] = new OBJ_OutRug(re);
        re.obj[1].worldY = 19 * re.tileSize;
        re.obj[1].worldX = 23 * re.tileSize;

        re.obj[2] = new OBJ_HouseDoor(re);
        re.obj[2].worldY = 43 * re.tileSize;
        re.obj[2].worldX = 35 * re.tileSize;
    }

}
