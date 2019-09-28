import java.awt.*;
import javax.swing.JFrame;

/**
 * Displays the fish tank.
 */
public class FishFrame extends JFrame {

    /** My Serializable id.  */
    private static final long serialVersionUID = 1409191926708912242L;

    /**
     * Paints this fish tank.
     *
     * @param  g  the graphics context to use for painting.
     */
    public void paint(Graphics g) {

        // Get my width and height.
        int w = getBounds().width;
        int h = getBounds().height;

        // Paint the window white.
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);


        // Tell all the fishtank items to draw themselves.

        for (int a = 0; a < FishTank.myLittleFishies.size(); a++) {
            if (FishTank.myLittleFishies.get(a) != null)
                if (FishTank.myLittleFishies.get(a) instanceof Fish) {
                    ((Fish) FishTank.myLittleFishies.get(a)).draw(g);
                }else if (FishTank.myLittleFishies.get(a) instanceof Seaweed) {
                    ((Seaweed) FishTank.myLittleFishies.get(a)).draw(g);
                }else if (FishTank.myLittleFishies.get(a) instanceof HungryFish) {
                    ((HungryFish) FishTank.myLittleFishies.get(a)).draw(g);
                }if (FishTank.myLittleFishies.get(a) instanceof Bubble) {
                ((Bubble) FishTank.myLittleFishies.get(a)).draw(g);
            }
        }

    }


}
