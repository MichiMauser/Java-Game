import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener {
    private int mouse_x;
    private int mouse_y;

    public MouseInput(){

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
        mouse_x = e.getX();
        mouse_y = e.getY();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse moved");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse moved");
    }
    public ArrayList<Integer> mouse_cords(){
        ArrayList<Integer> cords  = new ArrayList<>();
        cords.add(mouse_x);
        cords.add(mouse_y);
        return cords;
    }
}
