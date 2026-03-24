import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*Game window configuration*/
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Forest Crawler");

        RenderEngine renderEngine = snew RenderEngine();
        window.add(renderEngine);
        window.pack(); /*Necessary line to see the window*/

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        renderEngine.setupGame();
        renderEngine.startGameThread();
    }
}