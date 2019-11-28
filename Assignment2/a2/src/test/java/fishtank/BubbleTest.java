package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;

public class BubbleTest {

    private Bubble bubble;
    private Bubble bubble1;
    private Fish fish;
    private Seaweed seaweed;

    @Before
    public void setUp() {
        bubble = new Bubble();
        bubble1 = new Bubble();
        fish = mock(Fish.class);
        seaweed = mock(Seaweed.class);
    }

    @Test
    public void testBubbleFloatingCollision() {
        boolean NoCollision = false;
        bubble.setLocation(5,10);
        FishTank.addEntity(5,10,bubble);
        FishTank.addEntity(4,10,fish);
        FishTank.addEntity(6,10,seaweed);
        bubble1.setLocation(5,11);
        bubble1.update();
        int x = bubble1.getX();
        int y = bubble1.getY();
        if (x == 5 && y == 11) {
            NoCollision = true;
        }
        assertTrue(NoCollision);
    }

    @Test
    public void testBubbleFloatingAlwaysUp() {
        boolean alwaysUp = false;
        int count = 0;
        for (int i=0; i<200; i++) {
            bubble.setLocation(5,10);
            bubble.update();
            int y = bubble.getY();
            if (y == 9) {
                count ++;
            }
        }
        if (count == 200) {
            alwaysUp = true;
        }
        assertTrue(alwaysUp);
    }

    @Test
    public void testBubbleFloatingRightPercentage(){
        boolean rightPer = false;
        int count = 0;
        for (int i=0; i<10000; i++) {
            bubble.setLocation(5, 10);
            bubble.update();
            int x = bubble.getX();
            int y = bubble.getY();
            if (x == 6 && y == 9) {
                count ++;
            }
        }
        if (count >= 2800 && count <= 3800) {
            rightPer = true;
        }
        assertTrue(rightPer);
    }


    @Test
    public void testBubbleFloatingLeftPercentage(){
        boolean leftPer = false;
        int count = 0;
        for (int i=0; i<10000; i++) {
            bubble.setLocation(5, 10);
            bubble.update();
            int x = bubble.getX();
            int y = bubble.getY();
            if (x == 4 && y == 9) {
                count ++;
            }
        }
        if (count >= 2800 && count <= 3800) {
            leftPer = true;
        }
        assertTrue(leftPer);
    }

    @Test
    public void testBubbleDestroyed() {

        bubble.setLocation(0,0);
        bubble.update();
        assertFalse(bubble.exists());
    }


}
