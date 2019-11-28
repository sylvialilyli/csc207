package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class HungryFish extends FishTankEntity {

    /** How this fish appears on the screen. */
    public String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    private int r;
    /** This fish's second coordinate. */
    private int c;
    /** The colour of this fish. */
    private final Color colour;


    /**
     * Constructs a new hungry fish.
     */
    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
        goingRight = true;
    }


    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
      c = a;
      r = b;
    }

    int getX() {
        return c;
    }

    int getY() {
        return r;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    private void blowBubble() {
        Bubble b = new Bubble();
        int newC;
        if (c - 1 < 0) {
            newC = c + 1;
            if (FishTank.getEntity(newC, r) != null) {
                int i  = 1;
                newC = newC + i;
                while (FishTank.getEntity(newC, r) != null) {
                    newC += i;
                }
            }
        } else {
            newC = c - 1;
            if (FishTank.getEntity(newC, r) != null) {
                int i  = 1;
                newC = newC - i;
                while (newC > 0 && (FishTank.getEntity(newC, r) != null)) {
                    newC -= i;
                }
                if (newC == -1) {
                    newC += 3;
                }
                if (FishTank.getEntity(newC, r) != null) {
                    int j = 1;
                    newC = c + j;
                    while (FishTank.getEntity(newC, r) != null) {
                        newC += j;
                    }
                }
            }
        }

        b.setLocation(newC, r);
        System.out.println(r + " " + newC);
        FishTank.addEntity(newC, r, b);
    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
      System.out.println("Turning around" + this.appearance);
        StringBuilder reverse = new StringBuilder();
        for (int i=appearance.length()-1; i>=0; i--) {
            switch (appearance.charAt(i)) {
            case ')': reverse.append('('); break;
            case '(': reverse.append(')'); break;
            case '>': reverse.append('<'); break;
            case '<': reverse.append('>'); break;
            case '}': reverse.append('{'); break;
            case '{': reverse.append('}'); break;
            case '[': reverse.append(']'); break;
            case ']': reverse.append('['); break;
            default: reverse.append(appearance.charAt(i)); break;
            }
        }
        System.out.println("Turned around" + this.appearance);
        appearance = reverse.toString();
        return reverse.toString();
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    private void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /** The font used to draw instances of this class. */
    private static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
        g.drawString(s, y*fm.charWidth('W'), x*fm.getAscent());
    }



    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
/*

        // Move one spot to left or right.
        // Set boundaries to prevent hungryFish from swimming too far away
        if (c+1 <= 105 && c-1>=0) {
            boolean checkRight = checkCollision(c+1, r);
            boolean checkLeft = checkCollision(c-1, r);
            if (checkRight && !checkLeft) {
                if (goingRight) {
                    turnAround();
                }
                move();
            } else if (checkLeft && !checkRight) {
                if (!goingRight) {
                    turnAround();
                }
                move();
            } else if (!checkLeft) {
                move();
            }
        } else if (c == 0 && goingRight) {
            boolean checkRight = checkCollision(c+1, r);
            if (!checkRight){
                move();
            }
        } else if (c==105 && !goingRight) {
            boolean checkLeft = checkCollision(c-1, r);
            if (!checkLeft) {
                move();
            }
        }

        // Figure out whether I blow a bubble.
        double d = Math.random();
        if (d < 0.1) { blowBubble(); }

        // Figure out whether I turn around.
        d = Math.random();
        if (d < 0.1) { turnAround(); }

        // Set boundaries to prevent fish from swimming too far away
        if (c == 0 && (!goingRight))  {
            turnAround();
        } else if (c == 105 && goingRight) {
            turnAround();
        }

        // Figure out whether to move up or down, or neither.
        // Add boundary to the vertical movement of fish to prevent
        d = Math.random();
        if (d < 0.1 && r < 46) {
            if (!checkCollision(c, r+1)) {
                r += 1;
            }
        } else if (d < 0.2 && r > 0) {
            if (!checkCollision(c, r-1)) {
                r -= 1;
            }
        }

        // HungryFish eat seaweed when it passes by
        for (int i=47; i>r; i--) {
            if (FishTank.getEntity(c, i) instanceof Seaweed) {
                ((Seaweed) FishTank.getEntity(c,i)).eaten(r);
                break;
            }
        }
*/

    }

    //Helper function to move fish one spot to right or left
    private void move() {
        if (goingRight) {
            c += 1;
        } else {
            c -= 1;
        }
    }
}