public class CollisionCheck {

    RenderEngine re;

    public CollisionCheck(RenderEngine re){
        this.re = re;
    }

    public void checkTile(Sprite sprite){
        int spriteLeftWorldX = sprite.worldX + sprite.solidArea.x;
        int spriteRightWorldX = sprite.worldX + sprite.solidArea.x + sprite.solidArea.width;
        int spriteTopWorldY = sprite.worldY + sprite.solidArea.y;
        int spriteBottomWorldY = sprite.worldY + sprite.solidArea.y + sprite.solidArea.height;

        int spriteLeftCol = spriteLeftWorldX/re.tileSize;
        int spriteRightCol = spriteRightWorldX/re.tileSize;
        int spriteTopRow = spriteTopWorldY/re.tileSize;
        int spriteBottomRow = spriteBottomWorldY/re.tileSize;

        int tileNum1, tileNum2;

        switch(sprite.direction){
            case "up":
                spriteTopRow = (spriteTopWorldY - sprite.speed) / re.tileSize;
                tileNum1 =  re.tileM.mapTileNum[spriteLeftCol][spriteTopRow];
                tileNum2 =  re.tileM.mapTileNum[spriteRightCol][spriteTopRow];

                if(re.tileM.tile[tileNum1].collision == true || re.tileM.tile[tileNum2].collision == true) {
                    sprite.collisionOn = true;
                }
                break;
            case "down":
                spriteBottomRow = (spriteBottomWorldY + sprite.speed) / re.tileSize;
                tileNum1 =  re.tileM.mapTileNum[spriteLeftCol][spriteBottomRow];
                tileNum2 =  re.tileM.mapTileNum[spriteRightCol][spriteBottomRow];

                if(re.tileM.tile[tileNum1].collision == true || re.tileM.tile[tileNum2].collision == true) {
                    sprite.collisionOn = true;
                }
                break;
            case "left":
                spriteLeftCol = (spriteLeftWorldX - sprite.speed) / re.tileSize;
                tileNum1 = re.tileM.mapTileNum[spriteLeftCol][spriteTopRow];
                tileNum2 = re.tileM.mapTileNum[spriteLeftCol][spriteBottomRow];
                if(re.tileM.tile[tileNum1].collision || re.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "right":
                spriteRightCol = (spriteRightWorldX + sprite.speed) / re.tileSize;
                tileNum1 = re.tileM.mapTileNum[spriteRightCol][spriteTopRow];
                tileNum2 = re.tileM.mapTileNum[spriteRightCol][spriteBottomRow];
                if(re.tileM.tile[tileNum1].collision || re.tileM.tile[tileNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
        }
    }

}
