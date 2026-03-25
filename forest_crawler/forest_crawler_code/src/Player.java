import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Sprite {

    RenderEngine re;
    GameEngine keyH;

    public final int screenX;
    public final int screenY;

    public Player(RenderEngine re, GameEngine keyH) {
         this.re = re;
         this.keyH = keyH;

         screenX = re.screenWidth/2 - re.tileSize/2;
         screenY = re.screenHeight/2 - re.tileSize/2;

         //solidArea = new Rectangle(1/(6 * re.tileSize), 1/(3* re.tileSize), 2/(3 * re.tileSize), 2/(3 * re.tileSize));
        solidArea = new Rectangle(16, 30, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

         setDefaultValues();
         getPlayerImage();
    }

    public void setDefaultValues(){
         worldX=  re.tileSize * 28;
         worldY = re.tileSize * 15 ;
         speed=4;
         direction="down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_z_walk1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_z_walk2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_s_walk1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_s_walk2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_d_walk1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_d_walk2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_q_walk1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/characters/gnome/walk/gnome_q_walk2.png"));
            portrait = ImageIO.read(getClass().getResourceAsStream("/portraits/gnome_p.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {

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
            if (keyH.rightPressed) {
            }

            /* Check tile collision*/
            collisionOn = false;
            re.cCheck.checkTile(this);
            int objIndex = re.cCheck.checkObject(this, true);
            pickUpObject(objIndex);

            if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

                if (collisionOn == false) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
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
    }

    public void pickUpObject(int index){
        if(index != 999){

            String objectName = re.obj[index].name;

            switch(objectName){
                case "Axe":
                    re.txt.showText("Press SPACE to interact");
                    if(keyH.spacePressed) {
                        re.obj[index] = null;
                        re.txt.showText("Gnome axe has been picked up!");
                    }
                    break;
                case "OutRug":
                    if(re.obj[0] == null){
                        if(keyH.spacePressed) {
                            worldX = re.tileSize * 35;
                            worldY = re.tileSize * 44;
                        }
                    }
                    else{
                        re.txt.showText("Pick up your axe!");
                    }
                    break;
                case "HouseDoor":
                    if(keyH.spacePressed) {
                        worldX = re.tileSize * 23;
                        worldY = re.tileSize * 18;
                    }
                    break;
            }

        }
    }

    public void draw(Graphics2D g2 ){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y , re.tileSize, re.tileSize);
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

//        // DEBUG: Draw the Hitbox
//        g2.setColor(Color.RED);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
