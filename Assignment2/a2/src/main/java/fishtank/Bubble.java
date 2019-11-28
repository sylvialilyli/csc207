package fishtank;
import java.awt.*;

/**
 * A bubble.
 */
public class Bubble extends FishTankEntity {

    /** How this bubble appears on the screen. */
    private String appearance;

    /** The font used to draw instances of this class. */
    private static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);

    /** My colour. Ah, the vagaries of British vs. US spelling. */
    private final Color colour;

    /** Use for random movement left and right.  */
    public double d;

    /** This bubble's first coordinate. */
    private int x;
    /** This bubble's second coordinate. */
    private int y;

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     */
    public Bubble() {
        // Get a nice-looking grey for the bubble
         colour = Color.gray.darker().darker().darker();
         // start off with . as the appearance
        appearance = ".";
    }

    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
        // set x to a
      x = a;
        // set y to b
      y = b;
    }

    @Override
    int getX() {
        return x;
    }

    @Override
    int getY() {
        return y;
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    private void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x*fm.charWidth('W'), y*fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }




    /**
     * Causes this item to take its turn in the fish-tank simulation, moving straight up.
     */
    public void floatStraightUp() {

        // Move upwards.
        y--;

        // Figure out whether to grow, if at all.
        d = Math.random();
          // Occasionally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals("."))appearance="o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o"))appearance="O";
        }
    }    /**
     * Causes this item to take its turn in the fish-tank simulation, moving up and left.
     */
    public void floatLeftUp() {

        // Move upwards.
        y--;
         x -= 1; //left

        // Figure out whether to grow, if at all.
          d = Math.random();
          // Occasionally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals(".")) appearance = "o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o")) appearance = "O";
        }
    }
    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void floatRightUp() {

        // Move upwards.
        y--;
x += 1;// right
        // Figure out whether to grow, if at all.
        d = Math.random();
          // Occasionally change a . to a o or a o to a O
        if (d < 0.05) {
            // If the appearance is a ., change it to an o
            if (appearance.equals("."))appearance="o";
            // If the appearance is an o, change it to a O
            else if (appearance.equals("o"))appearance="O";
        }
    }

    public void update() {

        //Floating bubbles do not cover items.
        //Set boundaries on bubble, delete it when it cross the boundaries.
        d = Math.random();
        if (d < 0.33) {
            if (y-1<0) {
                this.delete();
            } else if (!(checkCollision(x,y-1))) {
                floatStraightUp();
            }
        } else if (d < 0.66) {
            if (y-1<0 || x+1>105) {
                this.delete();
            }else if (!(checkCollision(x+1, y-1))) {
                floatRightUp();
            }
        } else  /*d >= 0.66*/  {
            if (y-1<0 || x-1<0) {
                this.delete();
            } else if (!(checkCollision(x-1,y-1))) {
                floatLeftUp();
            }
        }

    }
}
