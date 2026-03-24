import java.awt.*;

public class Text {
    RenderEngine re;

    public String text = "";
    public boolean showText = false;
    int textCounter = 0;

    Font titleFont;
    Font pxFont;
    public Text(RenderEngine re){
        this.re = re;

        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/commonwealth/commonwealth3d.ttf"));
            titleFont = titleFont.deriveFont(48f);

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
        if(showText) {
            // Rectangle background
            int boxX = re.tileSize*4;
            int boxY = re.screenHeight - re.tileSize * 2;
            int boxWidth = re.tileSize * 8; // full width minus margin
            int boxHeight = re.tileSize; // height for 1-2 lines

            g2.setColor(new Color(255, 255, 255, 200));
            g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 15, 15);

            // Text
            g2.setFont(pxFont);
            g2.setColor(new Color(0,120,0));
            g2.drawString(text, boxX + re.tileSize/2, boxY + boxHeight - 24);
        }
    }
}