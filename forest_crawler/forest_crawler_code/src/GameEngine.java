import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements KeyListener {

    RenderEngine re;

    public GameEngine(RenderEngine re){
        this.re = re;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if(re.gameState == re.titleState){
            int code = keyEvent.getKeyCode();

            if (code == KeyEvent.VK_Z) {
                re.txt.commandNum--;
                if(re.txt.commandNum < 0){
                  re.txt.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                re.txt.commandNum++;
                if(re.txt.commandNum > 1){
                    re.txt.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_SPACE) {
                if(re.txt.commandNum == 0){
                    re.gameState = re.playState;
                }
                if(re.txt.commandNum == 1){
                    System.exit(0);
                }
            }

        }

        if(re.gameState == re.playState) {
            int code = keyEvent.getKeyCode();

            if (code == KeyEvent.VK_Z) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_Q) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (re.gameState == re.playState) {
            int code = keyEvent.getKeyCode();

            if (code == KeyEvent.VK_Z) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_Q) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = false;
            }
        }
    }
}