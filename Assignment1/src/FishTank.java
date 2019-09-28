import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A fish tank simulation.
 */
public class FishTank {

    /** The width of a character. */
    public final static int charWidth = 6;
    /** The height of a character. */
    public final static int charHeight = 10;
    /** (int)(640/6) columns, (int)(480/10) rows. */

    public static ArrayList<Object> myLittleFishies = new ArrayList<>();

    public static void main(String[] args) {

        // The window in which the fish swim.
        FishFrame f = new FishFrame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        int[] rIndex = {23, 6, 17, 15, 16, 16, 13, 24, 32};
        int[] cIndex = {18, 12, 4, 28, 35, 18, 25, 33, 25};

        for (int i = 0; i < 6; i++) {
            myLittleFishies.add(new Fish());
            ((Fish) myLittleFishies.get(i)).setLocation(rIndex[i], cIndex[i]);
        }

        myLittleFishies.add(new HungryFish());
        ((HungryFish) myLittleFishies.get(6)).setLocation(10, 33);

        for (int j = 7; j < (rIndex.length+1); j++) {
            myLittleFishies.add(new Seaweed(j - 2));
            ((Seaweed) myLittleFishies.get(j)).setLocation(rIndex[j-1], cIndex[j-1]);
        }

        // Show it all!
        f.setSize(640, 480);
        f.setLocation(10, 10);
        f.setVisible(true);

        // Every .3 seconds, tell each item in the fishtank to take
        // a turn.
        while (true) {

            for (int a = 0; a < myLittleFishies.size(); a++) {
                if (myLittleFishies.get(a) != null) {
                    if (myLittleFishies.get(a) instanceof Fish) {
                        ((Fish) myLittleFishies.get(a)).move();
                    } else if (myLittleFishies.get(a) instanceof Seaweed) {
                        ((Seaweed) myLittleFishies.get(a)).wave();
                    } else if (myLittleFishies.get(a) instanceof HungryFish) {
                        ((HungryFish) myLittleFishies.get(a)).move();
                    }
                    if (myLittleFishies.get(a) instanceof Bubble) {
                        // Figure out whether to float left or right, if at all.
                        Bubble heybub = (Bubble) myLittleFishies.get(a);
                        if (heybub.y > 0) {   // check if the Bubble is still visible in the window.
                            heybub.d = Math.random();
                            if (heybub.d < 0.33) heybub.floatStraightUp();
                            else if (heybub.d < 0.66) heybub.floatRightUp();
                            else /*heybub.d >= 0.66*/  heybub.floatLeftUp();
                        } else {
                            myLittleFishies.remove(a);  // if the Bubble is not visible anymore, remove it from myLittleFishies
                        }
                    }
                } }


                // Tell the fishtank to redraw itself.
                f.repaint();

                // Wait .3 seconds before redoing the queue.
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
            }


        }

    }
