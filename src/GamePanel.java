import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

public class GamePanel extends JPanel implements ActionListener {


    MouseInput mi = new MouseInput();
    KeyboardInput ki = new KeyboardInput(this, mi);
    Timer timer ;

    Rectangle movingRectangle = new Rectangle(400,290);

    int points;
    int timp;
    int playerHealth = 3;
    int liveEnemies = 2;
    int totalEnemies = 1;
    final private ArrayList<Bullet> bullets;
    final private ArrayList<Enemy> enemies;


    public GamePanel(){

        addKeyListener(ki);
        addMouseListener(mi);
       // addMouseMotionListener(mi);
        points = 0;
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        Random random = new Random();
        Set<Integer> xPositions = new HashSet<>();

        while (xPositions.size() < totalEnemies) {
            int rand_x = random.nextInt(1,250) + 1;
            int rand_x2 = random.nextInt(400,800)+1;
            xPositions.add(rand_x);
            xPositions.add(rand_x2);
        }

        for (int rand_x : xPositions) {
            int rand_pos = random.nextInt(2);
            enemies.add(new Enemy(rand_x, 290, rand_pos));
        }
        //enemies.add(new Enemy(10,290, 0));
        timer = new Timer(16, this);
        timer.start();

    }
    public void createBullet(int x, int y, int velocity, double dirX, double dirY){
        bullets.add(new Bullet(x, y, velocity,dirX,dirY));

    }
    public void paintComponent(Graphics g) {
        if (playerHealth != 0 && liveEnemies != 0) {
            Color custom = new Color(1, 50, 32);
            Color custom2 = new Color(150, 75, 0);
            Color custom3 = new Color(173, 216, 230);
            super.paintComponent(g);

            g.setColor(custom3);
            g.fillRect(0, 0, 800, 400);

            g.setColor(Color.BLACK);
            g.fillRect(0, 350, 800, 20);

            g.setColor(custom2);
            g.fillRect(0, 330, 800, 20);

            g.setColor(custom);
            g.fillRect(0, 310, 800, 20);


        /*if(ki.keyPressed() == null ){
            g.setColor(Color.black);
            g.fillRect(movingRectangle.x, movingRectangle.getY(), 20,20);
        }
        else if(text == "jump"){
            g.setColor(Color.white);
            g.fillRect(movingRectangle.y, movingRectangle.getY(),20,20);
        }*/
            g.setColor(Color.GRAY);
            g.fillRect(movingRectangle.x, movingRectangle.y, 20, 20);
            for (Bullet bullet : bullets) {
                if (bullet.y > 0) {
                    g.setColor(bullet.color);
                    g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
                }
            }
            for (Enemy enemy : enemies) {
                g.setColor(Color.RED);
                g.fillRect(enemy.x, enemy.y, 20, 20);
            }
            g.setColor(Color.BLUE);
            g.setFont(new Font("Comic Sans Ms", Font.BOLD, 24));
            g.drawString("Points:" + points, 50, 50);
            if ((System.currentTimeMillis() % 1000) / 100 == 0) {
                timp++;
            }
            g.drawString("Timer:" + (timp)/3, 200, 50);

            g.drawString("Health:" + playerHealth, 350, 50);

        }else if(playerHealth == 0){
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 400);

            g.setColor(Color.RED);
            g.setFont(new Font("Comic Sans Ms", Font.PLAIN, 24));
            g.drawString("YOU LOSE",300,200);

        }else if(playerHealth != 0 && liveEnemies == 0){
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 800, 400);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Comic Sans Ms", Font.PLAIN, 24));
            g.drawString("YOU WIN",300,200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(playerHealth != 0) {
            if (movingRectangle.isJumping) {
                movingRectangle.y -= 10;
                if (movingRectangle.y <= 250) {
                    movingRectangle.isJumping = false;
                }

            } else {
                if (movingRectangle.y < 290) {
                    movingRectangle.y += 10;
                }
            }

            for (Bullet bullet : bullets) {
                bullet.move();
            }
            for (Enemy enemy : enemies) {
                enemy.updateCoords();
            }
            Iterator<Bullet> bulletIterator = bullets.iterator();

            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                Iterator<Enemy> enemyIterator = enemies.iterator();
                while (enemyIterator.hasNext()) {
                    Enemy enemy = enemyIterator.next();
                    if ((enemy.x < bullet.x + 5 && enemy.x > bullet.x - 5) && (enemy.y < bullet.y + 10 && enemy.y > bullet.y - 10)) {
                        points++;
                        bulletIterator.remove();
                        enemyIterator.remove();
                        liveEnemies--;
                        break;
                    }
                }

            }
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (enemy.x == movingRectangle.x && enemy.y == movingRectangle.y) {
                    playerHealth--;
                }

            }
            repaint();
        }
    }


    public class Rectangle{

        private int x;
        private int y;
        boolean isJumping;

        Rectangle(int x, int y){
            this.x = x;
            this.y = y;
            this.isJumping = false;
        }

       public int getY() {
           return y;
       }

       public int getX() {
           return x;
       }

       public void updateCoords(int x, int y) {
           this.x = x;
           this.y = y;
       }
       public void setJumping(boolean isJumping){
            this.isJumping = isJumping;
       }


   }
   public class Bullet{
     private int x;
     private int y;

     private double dirX;
     private double dirY;
     private int height = 5;
     private int width = 5;
     private int velocity;
     private Color color = Color.DARK_GRAY;
     public Bullet(int x, int y, int velocity, double dirX, double dirY){
         this.x = x;
         this.y = y;
         this.dirX = dirX;
         this.dirY = dirY;
         this.velocity = velocity;
     }
     public void move(){
         if(this.y > 0) {
             this.x += (int) (velocity * dirX);
             this.y += (int) (velocity * dirY);
             // System.out.println(x+" "+y);;
         }
     }
     public ArrayList<Integer> getBullet(){
         ArrayList<Integer> cord = new ArrayList<>();
         cord.add(x);
         cord.add(y);
         return cord;
     }

    }

    public class Enemy{
        private int x;
        private int y;
        private int velocity = 2;

        private int health;
        enum pos {
            RIGHT,
            LEFT
        }
        pos position;

        int poz;

        public Enemy(int x, int y, int poz){
            this.x = x;
            this.y = y;
            this.poz = poz;

        }
        public void updateCoords() {
                if (this.poz == 0)
                    this.x += velocity;
                else {
                    this.x -= velocity;
                }

        }


    }


}
