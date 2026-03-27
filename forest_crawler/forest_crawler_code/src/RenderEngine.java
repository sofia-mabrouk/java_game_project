import javax.swing.*;
import java.awt.*;

public class RenderEngine extends JPanel implements Runnable{

    /*Screen settings*/
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol  * tileSize;
    public final int screenHeight = maxScreenRow  * tileSize; /*Screen size is 1024x768*/

    /*World settings*/
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 130;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    /*Setting frame rate to 60  FPS*/
    int FPS = 60;

    /*Instances :*/
    TileManager tileM = new TileManager(this);
    GameEngine keyH = new GameEngine();
    Thread gameThread; /*Starts a timer when program starts running, and stops when program stops running. Threads is why Runnable was implemented*/
    public CollisionCheck cCheck = new CollisionCheck(this);
    public Player player = new Player(this, keyH);
    public Enemy enemy = new Enemy(this, keyH, player, "sardine");

    public RenderEngine(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.decode("#55af32"));
        this.setDoubleBuffered(true); /*All the drawings from this component will be done in an offscreen painting buffer,
        which should improve the game's graphic performances*/
        this.addKeyListener(keyH); /*Engine can recognize key input*/
        this.setFocusable(true );
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                /* Updating of the screen based on player input*/
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println(drawCount + " FPS");
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
        enemy.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //tileM.draw(g2, false, true);
        tileM.draw(g2, false);
        player.draw(g2);
        enemy.draw(g2);
        tileM.draw(g2, true);
        g2.dispose();
    }
}