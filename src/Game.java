public class Game {
    GamePanel gamePanel;
    Window gameWindow;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new Window(gamePanel);
        gamePanel.requestFocus();
    }
}
