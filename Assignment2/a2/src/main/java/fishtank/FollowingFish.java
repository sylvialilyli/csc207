package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

    /** How this fish appears on the screen. */
    public String appearance;

    /** Indicates whether this fish is moving right. */
    private boolean goingRight;

    /** This fish's first coordinate. */
    private int r;
    /** This fish's second coordinate. */
    private int c;
    /** The colour of this fish. */
    private final Color colour;

    /** The entity that our fish is following */
    private Fish de;


    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
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
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private void reverseAppearance() {
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
    }


    /**
     * Turns this fish to fc
     */
    private void turnToFace() {
        if(de.getX() < this.getX() && goingRight) {
            goingRight = false;
            reverseAppearance();
        } else if(de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        }
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
    // reverse x, y position to make it show correct thing on the screen
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
        turnToFace();

        // Move one spot to the right or left.
        /*if (goingRight) {
            if (!checkCollision(c+1, r
            )) {
                c += 1;
            }
        } else {
            if (!checkCollision(c-1, r)) {
                c -= 1;
            }
        }

        if(Math.abs(de.getY() - r) > 2) {
            if(de.getY() < r && !checkCollision(c, r-1)) {
                r -= 1;
            } else if (de.getY() > r && !checkCollision(c, r+1)){
                r += 1;
            }
        }*/

        //Not change location of following fish it satisfies specification.
        /*if (!(Math.abs(de.getY()-r) + Math.abs(de.getX() - c) == 2)) {

            // Move from left to right.
            // Set boundaries on horizontal movement.
            if (de.getY() == r  &&  c == 0) {
                c = de.getX() + 2;
            } else if (de.getY() == r && c == 105) {
                c = de.getX() - 2;
            } else {
                regularChangeOfC();
            }

            //Fish moves farther away from following fish.
            if(Math.abs(de.getY() - r) > 2) {
                if (de.getY() < r) {
                    r -= 1;
                } else {
                    r += 1;
                }
            } else if (Math.abs(de.getY() - r) < 2){
                if(de.getY() < r) {
                    if (r < 47) {
                        r += 1;
                    } else {
                        regularChangeOfC();
                    }
                } else {
                    if (r >0 ) {
                        r -= 1;
                    } else {
                        regularChangeOfC();
                    }
                }
            }

        }*/

        int oldR = r;
        int oldC = c;

        //Not change location of following fish it satisfies specification.
        if (wrongDistance()) {

            // Move from left to right.
            // Set boundaries on horizontal movement.
            if (de.getY() == r) {
                if (c == 0) {
                    verticalCornerMove();
                    /*if (r == oldR && !checkCollision(de.getX()+2, r)) {
                        c = de.getX() + 2;
                    }*/
                } else if (c == 105) {
                    verticalCornerMove();
                    /*if (r == oldR && !checkCollision(de.getX()-2, r)) {
                        c = de.getX() - 2;
                    }*/
                } else {
                    if ((this.goingRight != de.goingRight) && (Math.abs(de.getX() - c) < 2)) {
                        regularChangeOfCReverse();
                    } else {
                        regularChangeOfC();
                    }
                    if (c==oldC) {
                        verticalCornerMove();
                        if (r!=oldR && wrongDistance()) {
                            regularChangeOfC();
                        }
                    }
                }
            } else{
                if (!(de.getX() == c)) {
                    regularChangeOfC();
                }
                if (wrongDistance()) {
                    regularChangeOfR();
                    if (Math.abs(de.getY() - r) > 2 && r == oldR) {
                        diagonalMoveCloser();
                    } else if (Math.abs(de.getY() - r) < 2 && r == oldR) {
                        diagonalMoveFarther();
                    }
                    if (r == oldR && c == oldC) {
                        verticalCloser();
                    }
                }
            }
        } else {
            if (Math.abs(de.getX() - c)==1 && Math.abs(de.getY() - r)==1) {
                diagonalMoveFarther();
            } else if (Math.abs(de.getX()-c)==2 && Math.abs(de.getY() - r)==0) {
                verticalCornerMove();
                regularChangeOfC();
                if (!(Math.abs(de.getX() - c)==1 && Math.abs(de.getY() - r)==1)) {
                    c = oldC;
                    r = oldR;
                }
            }
        }
    }

    private void diagonalMoveCloser() {
        if (de.getY() > r) {
            if (goingRight && c<105 && r<47) {
                if (!checkCollision(c+1, r+1)) {
                    c+=1;
                    r+=1;
                }
            } else if (!goingRight && c>0 && r<47) {
                if (!checkCollision(c-1, r+1)) {
                    c-=1;
                    r+=1;
                }
            }
        } else if (de.getY() < r) {
            if (goingRight && c<105 && r>0) {
                if (!checkCollision(c+1, r-1)) {
                    c+=1;
                    r-=1;
                }
            } else if (!goingRight && c>0 && r>0) {
                if (!checkCollision(c-1, r-1)) {
                    c-=1;
                    r-=1;
                }
            }
        }
    }

    private void diagonalMoveFarther() {
        if (de.getY() > r) {
            if (goingRight && c<105 && r>0) {
                if (!checkCollision(c+1, r-1)) {
                    c+=1;
                    r-=1;
                }
            } else if (!goingRight && c>0 && r>0) {
                if (!checkCollision(c-1, r-1)) {
                    c-=1;
                    r-=1;
                }
            }
        } else if (de.getY() < r) {
            if (goingRight && c<105 && r<47) {
                if (!checkCollision(c+1, r+1)) {
                    c+=1;
                    r+=1;
                }
            } else if (!goingRight && c>0 && r<47) {
                if (!checkCollision(c-1, r+1)) {
                    c-=1;
                    r+=1;
                }
            }
        }
    }

    private void regularChangeOfR() {
        //Fish moves farther away from following fish.
        if(Math.abs(de.getY() - r) > 2) {
            verticalCloser();
        } // Fish moves closer to following fish
        else if (Math.abs(de.getY() - r) < 2){
            if(de.getY() < r) {
                if (r<47) {
                    if (!checkCollision(c, r+1)) {
                        r += 1;
                    }
                }
                else {
                    regularHorizontalMove();
                }
            } else if (de.getY() > r){
                if (r > 0) {
                    if (!checkCollision(c, r - 1)) {
                        r -= 1;
                    }
                } else {
                    regularHorizontalMove();
                }
            }
        }
    }

    private void verticalCloser() {
        if (de.getY() < r && r>0) {
            if (!checkCollision(c, r-1)) {
                r -= 1;
            }
        } else if (de.getY() > r && r<47) {
            if (!checkCollision(c, r+1)) {
                r += 1;
            }
        }
    }

    private boolean wrongDistance() {
        return (Math.abs(de.getX() - c) + Math.abs(de.getY() - r) != 2);
    }

    private void regularHorizontalMove() {
        int recordC = c;
        regularChangeOfC();
        if (c == recordC) {
            regularChangeOfCReverse();
        }
    }

    private void regularChangeOfC() {
        if (goingRight && c < 105 && !checkCollision(c+1, r)) {
            c += 1;
        } else if (!goingRight && c > 0 && !checkCollision(c-1, r)){
            c -= 1;
        }
    }

    private void regularChangeOfCReverse() {
        if (goingRight && c > 0 && !checkCollision(c-1, r)) {
            goingRight = false;
            reverseAppearance();
            c -= 1;
        } else if (!goingRight && c < 105 && !checkCollision(c+1, r)){
            goingRight = true;
            reverseAppearance();
            c += 1;
        }
    }

    private void verticalCornerMove() {
        if (r < 47) {
            if (!checkCollision(c, r+1)) {
                r+=1;
            } else if (r > 0) {
            if (!checkCollision(c, r-1)) {
                r-=1;
            }
        } else {
                if (!checkCollision(c, r-1)) {
                    r-=1;
                }}
        }
    }
}
