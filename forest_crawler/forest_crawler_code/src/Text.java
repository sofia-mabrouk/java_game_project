import java.awt.*;

public class Text{
    RenderEngine re;

    public String text = "";
    public boolean showText = false;
    int textCounter = 0;
    public int commandNum = 0;

    Font titleFont;
    Font pxFont;
    public Text(RenderEngine re){
        this.re = re;

        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/commonwealth/commonwealth.ttf"));
            titleFont = titleFont.deriveFont(96f);

            pxFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/pixel_intv/Pixel Intv.otf"));
            pxFont = pxFont.deriveFont(24f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showText(String message){
        text = message;
        showText = true;
        textCounter = 0;
    }

//    public void showTitleScreen(){
//        g2.setFont(pxFont);
//    }

    public void update(){
        if(showText){
            textCounter++;
            if(textCounter > 120){
                showText = false;
            }
        }
    }

    public void draw(Graphics2D g2){
//        if(showText){
//            g2.setFont(pxFont);
//            g2.setColor(Color.WHITE);
//            g2.drawString(text, 50, 50);
//        }

        if(re.gameState == re.titleState){
//            showTitleScreen();
            int boxX = re.tileSize * 4;
            int boxY = re.screenHeight - re.tileSize * 2;
            int boxWidth = re.tileSize * 8; // full width minus margin
            int boxHeight = re.tileSize; // height for 1-2 lines

            g2.setColor(new Color(30, 5, 50, 200));
            g2.fillRect(0, 0, re.screenWidth, re.screenHeight);

            g2.setFont(titleFont);
            String text = "Forest Crawler";

            FontMetrics fm = g2.getFontMetrics();
            int x = (re.screenWidth - fm.stringWidth(text)) / 2;
            int y = re.tileSize*3;

            /* Title */
            g2.setColor(new Color(0));
            g2.drawString(text, x+10, y+10 -32);
            g2.setColor(new Color(255, 248, 130));
            g2.drawString(text, x, y -32);

            /* Gnome portrait */
            g2.drawImage(re.player.portrait,re.tileSize*7,re.tileSize*3,re.tileSize*2,re.tileSize*2,null);

            /* Menu */
            g2.setFont(pxFont.deriveFont(48f));
            text = "NEW GAME";
            g2.drawString(text,re.tileSize*6,re.tileSize*7);
            if(commandNum == 0){
                g2.drawString(">",re.tileSize*5,re.tileSize*7);
            }

            text = "EXIT";
            g2.drawString(text,re.tileSize*7,re.tileSize*9);
            if(commandNum == 1){
                g2.drawString(">",re.tileSize*6,re.tileSize*9);
            }
        }


        if(re.gameState == re.playState) {
            if (showText) {
                // Rectangle background
                int boxX = re.tileSize * 4;
                int boxY = re.screenHeight - re.tileSize * 2;
                int boxWidth = re.tileSize * 8; // full width minus margin
                int boxHeight = re.tileSize; // height for 1-2 lines

                g2.setColor(new Color(255, 255, 255, 200));
                g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15);

                // Text
                g2.setFont(pxFont);
                g2.setColor(new Color(0, 120, 0));
                g2.drawString(text, boxX + re.tileSize / 2, boxY + boxHeight - 24);
            }

        }
    }
}