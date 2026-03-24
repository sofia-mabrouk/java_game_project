import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    RenderEngine re;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public SuperObject(RenderEngine re){
        this.re = re;
    }

    public void draw(Graphics2D g2, RenderEngine re) {
        /*Camera*/
        int screenX = worldX - re.player.worldX + re.player.screenX;
        int screenY = worldY - re.player.worldY + re.player.screenY;

        /*Drawing only sprites in the screen*/
        if (worldX > re.player.worldX - re.player.screenX - re.tileSize &&
                worldX < re.player.worldX + re.player.screenX + re.tileSize &&
                worldY > re.player.worldY - re.player.screenY - re.tileSize &&
                worldY < re.player.worldY + re.player.screenY + re.tileSize) {
            g2.drawImage(image, screenX, screenY, re.tileSize, re.tileSize, null);
        }
    }
}
