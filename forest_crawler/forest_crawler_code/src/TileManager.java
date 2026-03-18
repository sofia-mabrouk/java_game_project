import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    RenderEngine re;
    public Tile[] tile;
    //public int mapTileNum[][];
    public int mapTopNum[][];
    public int mapBottomNum[][];

    public TileManager(RenderEngine re){
        this.re = re;
        tile = new Tile[100];
        mapTopNum = new int[re.maxWorldCol][re.maxWorldRow];
        mapBottomNum = new int[re.maxWorldCol][re.maxWorldRow];
        getTileImage();
        loadMap("/maps/house/house_bottom.txt", mapBottomNum);
        loadMap("/maps/house/house_top.txt", mapTopNum);
    }

    public void tileSetup(int index, String filePath, boolean collision, boolean isOverhead) {
        System.out.println("Attempting to load: " + filePath);
        try {

            if (getClass().getResourceAsStream(filePath) == null) {
                System.out.println("File not found: " + filePath);
            }
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(filePath));
            tile[index].collision = collision;
            tile[index].isOverhead = isOverhead;
            tile[index].width = tile[index].image.getWidth();
            tile[index].height = tile[index].image.getHeight();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        /*Sprites du chemin*/
        // LIGNES DROITES
        tileSetup(0, "/background/Forest/chemin/Horiz.png", false, false);
        tileSetup(1, "/background/Forest/chemin/Vertical.png", false, false);
        // COINS / VIRAGES SIMPLES
        tileSetup(2, "/background/Forest/chemin/VertG.png", false, false);
        tileSetup(3, "/background/Forest/chemin/VertD.png", false, false);
        tileSetup(4, "/background/Forest/chemin/BasG.png", false, false);
        tileSetup(5, "/background/Forest/chemin/BasD.png", false, false);
        // INTERSECTIONS EN T
        tileSetup(6, "/background/Forest/chemin/HautGD.png", false, false);
        tileSetup(7, "/background/Forest/chemin/BasGD.png", false, false);
        tileSetup(8, "/background/Forest/chemin/GaucheHB.png", false, false);
        tileSetup(9, "/background/Forest/chemin/DroiteHB.png", false, false);

        /*Sprites du long chemin*/
        tileSetup(56, "/background/Forest/chemin_long/CoinBD.png", false, false);
        tileSetup(57, "/background/Forest/chemin_long/CoinBG.png", false, false);
        tileSetup(58, "/background/Forest/chemin_long/CoinHD.png", false, false);
        tileSetup(59, "/background/Forest/chemin_long/CoinHG.png", false, false);
        tileSetup(60, "/background/Forest/chemin_long/DB.png", false, false);
        tileSetup(61, "/background/Forest/chemin_long/DH.png", false, false);
        tileSetup(62, "/background/Forest/chemin_long/GB.png", false, false);
        tileSetup(63, "/background/Forest/chemin_long/GH.png", false, false);
        tileSetup(64, "/background/Forest/chemin_long/Horizontal B.png", false, false);
        tileSetup(65, "/background/Forest/chemin_long/Horizontal H.png", false, false);
        tileSetup(66, "/background/Forest/chemin_long/Vertical D.png", false, false);
        tileSetup(67, "/background/Forest/chemin_long/Vertical G.png", false, false);

        /*Sprite de l'herbe*/
        tileSetup(10, "/background/Forest/herbe/herbe.png", false, false);

        /*Sprite de l'eau*/
        // BORDS DROITS (Eau contre Terre)
        tileSetup(11, "/background/Forest/water/water_H.png", true, false);
        tileSetup(12, "/background/Forest/water/water_B.png", true, false);
        tileSetup(13, "/background/Forest/water/water_G.png", true, false);
        tileSetup(14, "/background/Forest/water/water_D.png", true, false);
        // COINS D'EAU
        tileSetup(15, "/background/Forest/water/water_BG_corner.png", true, false);
        tileSetup(16, "/background/Forest/water/water_BD_corner.png", true, false);
        tileSetup(17, "/background/Forest/water/water_HG_corner.png", true, false);
        tileSetup(18, "/background/Forest/water/water_HD_corner.png", true, false);
        tileSetup(52, "/background/Forest/water/BDcorner.png", true, false);
        tileSetup(53, "/background/Forest/water/BGcorner.png", true, false);
        tileSetup(54, "/background/Forest/water/HDcorner.png", true, false);
        tileSetup(55, "/background/Forest/water/HGcorner.png", true, false);
        // IlÔTS D'HERBE
        tileSetup(19, "/background/Forest/water/grass_HG_corner.png", false, false);
        tileSetup(20, "/background/Forest/water/grass_HD_corner.png", false, false);
        tileSetup(21, "/background/Forest/water/grass_BG_corner.png", false, false);
        tileSetup(22, "/background/Forest/water/grass_BD_corner.png", false, false);
        // EAU MILIEU
        tileSetup(23, "/background/Forest/water/water_mid.png", true, false);

        /*Sprite de l'arbre et buisson*/
        tileSetup(49, "/background/Forest/buissons/Buisson.png", true, false);
        tileSetup(24, "/background/Forest/arbre/souche.png", true, true);
        tileSetup(25, "/background/Forest/arbre/tree_bas.png", true, false);
        tileSetup(26, "/background/Forest/arbre/tree_haut.png", false, true);
        tileSetup(50, "/background/Forest/arbre/tree_haut.png", true, true);

        /*Sprites maison*/
        // MAISON NAIN
        tileSetup(27, "/background/Maisons/Maison_nain/maison_nain/TileR1C1n.png", false, true);
        tileSetup(28, "/background/Maisons/Maison_nain/maison_nain/TileR1C2n.png", false, true);
        tileSetup(29, "/background/Maisons/Maison_nain/maison_nain/TileR1C3n.png", false, true);
        tileSetup(30, "/background/Maisons/Maison_nain/maison_nain/TileR1C4n.png", false, true);
        tileSetup(31, "/background/Maisons/Maison_nain/maison_nain/TileR2C1n.png", true, false);
        tileSetup(32, "/background/Maisons/Maison_nain/maison_nain/TileR2C2n.png", true, false);
        tileSetup(33, "/background/Maisons/Maison_nain/maison_nain/TileR2C3n.png", true, false);
        tileSetup(34, "/background/Maisons/Maison_nain/maison_nain/TileR2C4n.png", true, false);
        tileSetup(35, "/background/Maisons/Maison_nain/maison_nain/TileR3C1n.png", true, false);
        tileSetup(36, "/background/Maisons/Maison_nain/maison_nain/TileR3C2n.png", true, false);
        tileSetup(37, "/background/Maisons/Maison_nain/maison_nain/TileR3C3n.png", true, false);
        tileSetup(38, "/background/Maisons/Maison_nain/maison_nain/TileR3C4n.png", true, false);

        // MAISON FLEUR

        tileSetup(39, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR1C1.png", false, true);
        tileSetup(40, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR1C2.png", false, true);
        tileSetup(41, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR1C3.png", false, true);
        tileSetup(42, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR2C1.png", true, false);
        tileSetup(43, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR2C2.png", true, false);
        tileSetup(44, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR2C3.png", true, false);
        tileSetup(45, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR3C1.png", true, false);
        tileSetup(46, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR3C2.png", true, false);
        tileSetup(47, "/background/Maisons/Maison_fleur/maison_fleur_tiles/Tiles/TileR3C3.png", true, false);
        tileSetup(48, "/background/Maisons/Maison_fleur/table_the.png", true, false);

        // CARRÉ NOIR & CARRÉ TRANSPARENT
        tileSetup(51, "/background/black_sprite.png", false, false);
        tileSetup(52, "/background/black_sprite.png", true, false);
        tileSetup(99, "/background/transparent.png", false, false);

        // INTÉRIEUR MAISON NAIN
        tileSetup(68, "/background/Maisons/Maison_nain/sol/sol_basique.png", false, false);
        tileSetup(69, "/background/Maisons/Maison_nain/sol/sol_sortie.png", false, false);
        tileSetup(70, "/background/Maisons/Maison_nain/mur/mur_bas.png", true, false);
        tileSetup(71, "/background/Maisons/Maison_nain/mur/mur_mid.png", true, false);
        tileSetup(72, "/background/Maisons/Maison_nain/mur/mur_haut.png", true, false);
        tileSetup(73, "/background/Maisons/Maison_nain/cheminee/cheminee_bas.png", true, false);
        tileSetup(74, "/background/Maisons/Maison_nain/cheminee/cheminee_haut.png", true, false);
        tileSetup(75, "/background/Maisons/Maison_nain/lits/lit_rouge.png", true, false);
        tileSetup(76, "/background/Maisons/Maison_nain/lits/lit_jaune.png", true, false);
        tileSetup(77, "/background/Maisons/Maison_nain/tabouret.png", false, false);
        tileSetup(78, "/background/Maisons/Maison_nain/table.png", true, false);
        tileSetup(79, "/background/Maisons/Maison_nain/tonneau.png", true, false);
        tileSetup(80, "/background/Maisons/Maison_nain/hache.png", true, false);
    }

    public void loadMap(String mapPath, int mapNum[][]){

        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < re.maxWorldCol && row < re.maxWorldRow){
                String line = br.readLine();
                while(col < re.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapNum[col][row] = num;
                    col++;
                }
                if(col == re.maxWorldCol){
                    col = 0;
                    row ++;
                }
            }
            br.close();
        } catch (Exception e) {
        }

    }

    public void drawLayer(Graphics2D g2, int mapNum[][], boolean not32, boolean isOverhead) {
        int worldCol = 0;
        int worldRow = 0;

        try {
            while (worldCol < re.maxWorldCol && worldRow < re.maxWorldRow) {
                int tileNum = mapNum[worldCol][worldRow];

                if(tile[tileNum] == null){
                    System.out.println("Missing tile: " + tileNum +
                            " at col=" + worldCol + " row=" + worldRow);}

                if(tileNum != 99) {

                    if (tile[tileNum].isOverhead == isOverhead) {

                        boolean isLarge = (tile[tileNum].image.getWidth(null) > 32 ||
                                tile[tileNum].image.getHeight(null) > 32);

                        if (isLarge == not32) {
                            int Width = tile[tileNum].image.getWidth(null);
                            int Height = tile[tileNum].image.getHeight(null);
                            int scaledWidth = Width * re.scale;
                            int scaledHeight = Height * re.scale;
                            int worldX = worldCol * re.tileSize;
                            int worldY = worldRow * re.tileSize;
                            // int corWorldY = worldY - (scaledHeight - re.tileSize); /* Pour que le nombre sur map.txt corresponde au bas à gauche de la sprite*/

                            /*Camera*/
                            int screenX = worldX - re.player.worldX + re.player.screenX;
                            int screenY = worldY - re.player.worldY + re.player.screenY;

                            /*Drawing only sprites in the screen*/
                            if (worldX > re.player.worldX - re.player.screenX - re.tileSize && worldX < re.player.worldX + re.player.screenX + re.tileSize
                                    && worldY > re.player.worldY - re.player.screenY - re.tileSize && worldY < re.player.worldY + re.player.screenY + re.tileSize) {
                                g2.drawImage(tile[tileNum].image, screenX, screenY, scaledWidth, scaledHeight, null);
                            }
                        }
                    }
                }
                worldCol++;

                if (worldCol == re.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2, boolean isOverhead){
        /*Code offered by the video tutorial, however it didn't work for sprites that weren't 32x32, it shrunk them or they were overdrawn by other sprites,
        which iswhy I wrote drawLayer method*/
        /*int x = 0;
        int y = 0;

        while(col < re.maxScreenCol && row < re.maxScreenRow){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, re.tileSize, re.tileSize, null);
            col++;
            x += re.tileSize;

            if(col == re.maxScreenCol){
                col = 0;
                x =0;
                row++;
                y += re.tileSize;
            }*/

        drawLayer(g2, mapBottomNum,false, isOverhead); /* Draws 32x32 background sprites first */
        drawLayer(g2, mapBottomNum,true, isOverhead); /* Draws sprites of other dimensions */
        drawLayer(g2, mapTopNum,false, isOverhead); /* Draws 32x32 background sprites first */
        drawLayer(g2, mapTopNum,true, isOverhead); /* Draws sprites of other dimensions */
    }
}
