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

        int tileBottomNum1, tileBottomNum2, tileTopNum1, tileTopNum2;

        switch(sprite.direction){
            case "up":
                spriteTopRow = (spriteTopWorldY - sprite.speed) / re.tileSize;
                tileBottomNum1 =  re.tileM.mapBottomNum[spriteLeftCol][spriteTopRow];
                tileBottomNum2 =  re.tileM.mapBottomNum[spriteRightCol][spriteTopRow];
                tileTopNum1 =  re.tileM.mapTopNum[spriteLeftCol][spriteTopRow];
                tileTopNum2 =  re.tileM.mapTopNum[spriteRightCol][spriteTopRow];

                if(re.tileM.tile[tileBottomNum1].collision == true || re.tileM.tile[tileBottomNum2].collision == true ||
                        re.tileM.tile[tileTopNum1].collision == true || re.tileM.tile[tileTopNum2].collision == true) {
                    sprite.collisionOn = true;
                }
                break;
            case "down":
                spriteBottomRow = (spriteBottomWorldY + sprite.speed) / re.tileSize;
                tileBottomNum1 =  re.tileM.mapBottomNum[spriteLeftCol][spriteBottomRow];
                tileBottomNum2 =  re.tileM.mapBottomNum[spriteRightCol][spriteBottomRow];
                tileTopNum1 =  re.tileM.mapTopNum[spriteLeftCol][spriteBottomRow];
                tileTopNum2 =  re.tileM.mapTopNum[spriteRightCol][spriteBottomRow];

                if(re.tileM.tile[tileBottomNum1].collision == true || re.tileM.tile[tileBottomNum2].collision == true ||
                        re.tileM.tile[tileTopNum1].collision == true || re.tileM.tile[tileTopNum2].collision == true) {
                    sprite.collisionOn = true;
                }
                break;
            case "left":
                spriteLeftCol = (spriteLeftWorldX - sprite.speed) / re.tileSize;
                tileBottomNum1 = re.tileM.mapBottomNum[spriteLeftCol][spriteTopRow];
                tileBottomNum2 = re.tileM.mapBottomNum[spriteLeftCol][spriteBottomRow];
                tileTopNum1 = re.tileM.mapTopNum[spriteLeftCol][spriteTopRow];
                tileTopNum2 = re.tileM.mapTopNum[spriteLeftCol][spriteBottomRow];

                if(re.tileM.tile[tileBottomNum1].collision || re.tileM.tile[tileBottomNum2].collision ||
                        re.tileM.tile[tileTopNum1].collision || re.tileM.tile[tileTopNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
            case "right":
                spriteRightCol = (spriteRightWorldX + sprite.speed) / re.tileSize;
                tileBottomNum1 = re.tileM.mapBottomNum[spriteRightCol][spriteTopRow];
                tileBottomNum2 = re.tileM.mapBottomNum[spriteRightCol][spriteBottomRow];
                tileTopNum1 = re.tileM.mapTopNum[spriteRightCol][spriteTopRow];
                tileTopNum2 = re.tileM.mapTopNum[spriteRightCol][spriteBottomRow];

                if(re.tileM.tile[tileBottomNum1].collision || re.tileM.tile[tileBottomNum2].collision ||
                        re.tileM.tile[tileTopNum1].collision || re.tileM.tile[tileTopNum2].collision) {
                    sprite.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Sprite sprite, boolean player){
        int index = 999;

        for(int i = 0; i<re.obj.length; i++){
            if(re.obj[i] != null){
                //Get entity's solid area position
                sprite.solidArea.x = sprite.worldX + sprite.solidArea.x;
                sprite.solidArea.y = sprite.worldY + sprite.solidArea.y;

                //Get the object's solid area position
                re.obj[i].solidArea.x = re.obj[i].worldX + re.obj[i].solidArea.x;
                re.obj[i].solidArea.y = re.obj[i].worldY + re.obj[i].solidArea.y;

                switch(sprite.direction){
                    case "up":
                        sprite.solidArea.y -= sprite.speed;
                        if(sprite.solidArea.intersects(re.obj[i].solidArea)){
                            if(re.obj[i].collision == true){
                                sprite.collisionOn=true;
                            }
                            if(player == true){
                                 index = i;
                            }
                        }
                        break;
                    case "down":
                        sprite.solidArea.y += sprite.speed;
                        if(sprite.solidArea.intersects(re.obj[i].solidArea)){
                            if(re.obj[i].collision == true){
                                sprite.collisionOn=true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        sprite.solidArea.x -= sprite.speed;
                        if(sprite.solidArea.intersects(re.obj[i].solidArea)){
                            if(re.obj[i].collision == true){
                                sprite.collisionOn=true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        sprite.solidArea.x += sprite.speed;
                        if(sprite.solidArea.intersects(re.obj[i].solidArea)){
                            if(re.obj[i].collision == true){
                                sprite.collisionOn=true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                sprite.solidArea.x = sprite.solidAreaDefaultX;
                sprite.solidArea.y = sprite.solidAreaDefaultY;
                re.obj[i].solidArea.x = re.obj[i].solidAreaDefaultX;
                re.obj[i].solidArea.y = re.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }

}
