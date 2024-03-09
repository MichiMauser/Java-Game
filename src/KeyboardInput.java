import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardInput implements KeyListener {

    //String text;
    private GamePanel gamePanel;
    private long lastTimeShoot = 0;
    private long shootInterval = 500;

    private MouseInput mi;

    public KeyboardInput(GamePanel gamePanel, MouseInput mi){
        this.gamePanel = gamePanel;
        this.mi = mi;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("move to left");
            gamePanel.movingRectangle.updateCoords(gamePanel.movingRectangle.getX()-40,gamePanel.movingRectangle.getY());
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("move to right");
            gamePanel.movingRectangle.updateCoords(gamePanel.movingRectangle.getX()+40,gamePanel.movingRectangle.getY());

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE  && !gamePanel.movingRectangle.isJumping){
            System.out.println("jump");
            //gamePanel.movingRectangle.updateCoords(gamePanel.movingRectangle.getX(),gamePanel.movingRectangle.getY()-20);
            gamePanel.movingRectangle.isJumping = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E && System.currentTimeMillis() - lastTimeShoot >= shootInterval){

           // System.out.println(mi.mouse_cords().get(0));
            double dirX = mi.mouse_cords().get(0) - gamePanel.movingRectangle.getX();
            double dirY = mi.mouse_cords().get(1) - gamePanel.movingRectangle.getY();
            double len = Math.sqrt(dirX * dirX + dirY * dirY);
            dirX /= len;
            dirY /= len;

            lastTimeShoot = System.currentTimeMillis();
            gamePanel.createBullet(gamePanel.movingRectangle.getX() ,gamePanel.movingRectangle.getY() ,10, dirX, dirY);

        }
       // gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
