import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends Sprite{

    RenderEngine re;
    GameEngine keyH;
    Player player;

    public int screenX;
    public int screenY;

    public Enemy(RenderEngine re, GameEngine keyH, Player player){
        this.re = re;
        this.keyH = keyH;
        this.player = player;

        screenX= re.screenWidth/2 - re.tileSize/2;
        screenY= re.screenWidth/2 - re.tileSize/2;

        solidArea = new Rectangle(16, 30, 32, 32);

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues(){
        this.worldX = re.tileSize * 27;
        this.worldY = re.tileSize * 25 ;
        this.speed=4;
        direction="down";
    }

    public void getEnemyImage() {
        try {
            up1 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk1.png"));
            up2 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk2.png"));
            down1 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk1.png"));
            down2 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk2.png"));
            right1 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk1.png"));
            right2 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk2.png"));
            left1 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk1.png"));
            left2 =ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            if (player.collisionOn == false) {
                switch (direction) {
                    case "up":
                        this.screenY += speed;
                        break;
                    case "down":
                        this.screenY -= speed;
                        break;
                    case "left":
                        this.screenX += speed;
                        break;
                    case "right":
                        this.screenX -= speed;
                        break;
                }
            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNumber == 1){
                    image = up1;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1){
                    image = down1;
                }
                if (spriteNumber == 2){
                    image = down2;
                }
                break;
            case "right":
                if (spriteNumber == 1){
                    image = right1;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
                break;
            case "left":
                if (spriteNumber == 1){
                    image = left1;
                }
                if (spriteNumber == 2){
                    image = left2 ;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, re.tileSize, re.tileSize, null);

        // DEBUG: Draw the Hitbox
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

}
