import javax.swing.*;

public class Window {
    JFrame screen = new JFrame();
    JLabel label = new JLabel();
    public Window(GamePanel gamePanel){

        this.label.setText("Test");
        this.label.setBounds(100,100,100,30);
        this.screen.add(gamePanel);


        this.screen.setResizable(false);
        this.screen.setSize(800,400);
        this.screen.setVisible(true);
        this.screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.screen.setLocationRelativeTo(null);
        this.screen.setLayout(null);
        this.screen.add(label);


    }

}
