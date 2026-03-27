import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends Sprite{

    RenderEngine re;
    GameEngine keyH;
    Player player;
    public String perso;
    public int stepCounter = 0;

    public int screenX;
    public int screenY;

    public Enemy(RenderEngine re, GameEngine keyH, Player player, String personnage){
        this.re = re;
        this.keyH = keyH;
        this.player = player;
        this.perso = personnage;

        screenX= re.screenWidth/2 - re.tileSize/2;
        screenY= re.screenWidth/2 - re.tileSize/2;

        solidArea = new Rectangle(16, 30, 32, 32);

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues(){
        this.worldX = re.tileSize * 27;
        this.worldY = re.tileSize * 25;
        this.speed=4;
        direction="down";
    }

    public void getEnemyImage() {
        try {
            if (perso == "sardine") {
                up1 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_qz_walk2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/characters/sardine/walk/sardine_ds_walk2.png"));
            }
            if (perso == "fleur") {
                up1 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_z_walk1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_z_walk2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_qs_walk1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_qs_walk2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_d_walk1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_d_walk2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_qs_walk1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/characters/fleur/walk/flower_qs_walk2.png"));
            }
            if (perso == "pingouin") {
                up1 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_z_walk1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_z_walk3.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_s_walk1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_s_walk3.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_d_walk1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_d_walk2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_q_walk1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/characters/pingouin/walk/pingouin_q_walk2.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if (player.collisionOn == false) {
                switch (player.direction) {
                    case "up":
                        this.screenY += speed;
                        switch (direction) {
                            case "up":
                                this.screenY -= player.speed;
                                stepCounter++;
                                break;
                            case "down":
                                this.screenY += player.speed;
                                stepCounter++;
                                break;
                            case "left":
                                this.screenX -= player.speed;
                                stepCounter++;
                                break;
                            case "right":
                                this.screenX += player.speed;
                                stepCounter++;
                                break;
                        }
                        break;
                    case "down":
                        this.screenY -= speed;
                        switch (direction) {
                            case "up":
                                this.screenY -= player.speed;
                                stepCounter++;
                                break;
                            case "down":
                                this.screenY += player.speed;
                                stepCounter++;
                                break;
                            case "left":
                                this.screenX -= player.speed;
                                stepCounter++;
                                break;
                            case "right":
                                this.screenX += player.speed;
                                stepCounter++;
                                break;
                        }
                        break;
                    case "left":
                        this.screenX += speed;
                        switch (direction) {
                            case "up":
                                this.screenY -= player.speed;
                                stepCounter++;
                                break;
                            case "down":
                                this.screenY += player.speed;
                                stepCounter++;
                                break;
                            case "left":
                                this.screenX -= player.speed;
                                stepCounter++;
                                break;
                            case "right":
                                this.screenX += player.speed;
                                stepCounter++;
                                break;
                        }
                        break;
                    case "right":
                        this.screenX -= speed;
                        switch (direction) {
                            case "up":
                                this.screenY -= player.speed;
                                stepCounter++;
                                break;
                            case "down":
                                this.screenY += player.speed;
                                stepCounter++;
                                break;
                            case "left":
                                this.screenX -= player.speed;
                                stepCounter++;
                                break;
                            case "right":
                                this.screenX += player.speed;
                                stepCounter++;
                                break;
                        }
                        break;
                }

                if (stepCounter > 25){
                    stepCounter = 0;
                    switch (direction) {
                        case "up":
                            direction = "left";
                            break;
                        case "down":
                            direction = "right";
                            break;
                        case "left":
                            direction = "down";
                            break;
                        case "right":
                            direction = "up";
                            break;
                    }
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
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
            switch (direction) {
                case "up":
                    this.screenY -= speed;
                    stepCounter++;
                    break;
                case "down":
                    this.screenY += speed;
                    stepCounter++;
                    break;
                case "left":
                    this.screenX -= speed;
                    stepCounter++;
                    break;
                case "right":
                    this.screenX += speed;
                    stepCounter++;
                    break;
            }
                if (stepCounter > 25){
                    stepCounter = 0;
                    switch (direction) {
                        case "up":
                            direction = "left";
                            break;
                        case "down":
                            direction = "right";
                            break;
                        case "left":
                            direction = "down";
                            break;
                        case "right":
                            direction = "up";
                            break;
                    }
                }
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
