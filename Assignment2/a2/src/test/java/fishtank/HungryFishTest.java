package fishtank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HungryFishTest {

    /* Note: FishTest is in the package fishTank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto-grading your tests, so make sure
       to follow this naming convention!
     */
    private HungryFish hungryFish;
    private Fish fish;
    private Seaweed seaweed;
    private Bubble bubble;

    @Before
    public void setUp() {
        seaweed = new Seaweed(6);
        bubble = mock(Bubble.class);
        fish = mock(Fish.class);
        hungryFish = new HungryFish();
    }

    @After
    public void tearDown() {
        for(int i = 0; i < 640/6; i++) {
            for (int j = 0; j < 480/10; j++){
                FishTank.removeEntity(i, j);
            }
        }
    }

    @Test
    public void testHungryFishBubbles() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            hungryFish.setLocation(5, 10);
            hungryFish.goingRight =
                    false; //notice: I can edit package private attributes!
            hungryFish.update();
            //fish should move one tile left and eventually blow a bubble.
            //Modify code to make it fit change of blowBubble in Fish.
            FishTankEntity e = FishTank.getEntity(3, 10);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }

    @Test
    public void testHungryFishBlowBubblesNotCoverItem(){
        boolean bubbleCover = true;
        int count = 0;

        // Not cover fish itself, not in fish's last location.
        for (int i=0; i<200; i++) {
            FishTank.addEntity(1, 10, hungryFish);
            hungryFish.goingRight = true;
            hungryFish.update();
            FishTankEntity e = FishTank.getEntity(0, 10);
            if (e instanceof Bubble) {
                FishTank.removeEntity(0, 10);
                FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());
                count ++;
                break;
            }
        }

        //Not cover Bubble, Seaweed, HungryFish
        FishTank.addEntity(0,10, bubble);
        FishTank.addEntity(3, 10, seaweed);
        FishTank.addEntity(4, 10, fish);
        for (int i=0; i<200; i++) {
            FishTank.addEntity(1, 10, hungryFish);
            hungryFish.goingRight=true;
            hungryFish.update();
            FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());
            if (FishTank.getEntity(5, 10) instanceof Bubble) {
                count ++;
                break;
            }
        }

        if (count == 2) { bubbleCover = false; }

        assertFalse(bubbleCover);
    }

    @Test
    public void testHungryFishBlowBubblesPercentage() {

        int count = 0;
        for (int j  = 10; j < 20; j++) {
            for (int i = 0; i < 100; i++) {
                hungryFish.setLocation(5, j);
                hungryFish.goingRight =
                        false;
                hungryFish.update();
            }
            for (int z = 0; z < 100;z++) {
                FishTankEntity e = FishTank.getEntity(z, j);
                if (e instanceof Bubble) {
                    count ++;
                }
            }
        }
        boolean runTenPercentOfTime = (count>=50 && count<=150);
        assertTrue(runTenPercentOfTime);
    }

    @Test
    public void testHungryFishSetGoingRight() {
        boolean goingRight = false;
        hungryFish.setGoingRight(true);
        hungryFish.setLocation(5,10);
        hungryFish.update();
        if (hungryFish.getX() == 6) {
            goingRight = true;
        }
        assertTrue(goingRight);
    }

    @Test
    public void testHungryFishSetGoingLeft() {
        boolean goingLeft = false;
        hungryFish.setGoingRight(false);
        hungryFish.setLocation(5,10);
        hungryFish.update();
        if (hungryFish.getX() == 4) {
            goingLeft = true;
        }
        assertTrue(goingLeft);
    }

    @Test
    public void testHungryFishMoveUpPercentage() {
        boolean moveUp = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            hungryFish.setLocation(5,10);
            hungryFish.update();
            removeBubble();
            if (hungryFish.getY() == 9) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            moveUp = true;
        }
        assertTrue(moveUp);
    }

    @Test
    public void testHungryFishMoveDownPercentage() {
        boolean moveDown = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            hungryFish.setLocation(5,10);
            hungryFish.update();
            removeBubble();
            if (hungryFish.getY() == 9) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            moveDown = true;
        }
        assertTrue(moveDown);
    }

    @Test
    public void testHungryFishTurnAroundPercentage() {
        boolean turnAround = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            hungryFish.setLocation(5,10);
            hungryFish.setGoingRight(true);
            hungryFish.update();
            removeBubble();
            if (!hungryFish.goingRight) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            turnAround = true;
        }
        assertTrue(turnAround);
    }

    @Test
    public void testHungryFishStayInBound() {
        boolean stayIn = false;
        int count = 0;

        // test left boundary
        hungryFish.setLocation(1, 5);
        hungryFish.setGoingRight(false);
        hungryFish.update();
        if (hungryFish.goingRight) {
            count ++;
        }

        // test right boundary
        hungryFish.setLocation(104, 5);
        hungryFish.setGoingRight(true);
        hungryFish.update();
        if (!hungryFish.goingRight) {
            count ++;
        }

        // test top boundary
        for (int i = 0; i < 200; i++) {
            hungryFish.setLocation(3,0);
            hungryFish.update();
            if (hungryFish.getY() == -1) {
                count --;
                break;
            }
        }
        count ++;

        //test bottom boundary
        for (int i = 0; i < 200; i++) {
            hungryFish.setLocation(3, 46);
            hungryFish.update();
            if (hungryFish.getY() == 47) {
                count --;
                break;
            }
        }
        count ++;

        if (count == 4) {
            stayIn = true;
        }

        assertTrue(stayIn);
    }



    @Test
    public void testHungryFishCollision() {
        boolean noCollision = false;
        int count = 0;

        // test if fish turn left if obstacle is on the right.
        FishTank.addEntity(6, 10, fish);
        hungryFish.setLocation(5, 10);
        hungryFish.setGoingRight(true);
        hungryFish.update();
        if ( hungryFish.getX()==4) {
            count ++;
        }
        removeBubble();
        FishTank.removeEntity(6, 10);

        // test if fish turn right if obstacle is on the left.
        FishTank.addEntity(4, 10, seaweed);
        FishTank.addEntity(5, 10, hungryFish);
        hungryFish.setGoingRight(false);
        hungryFish.update();
        if ( hungryFish.getX()==6) {
            count ++;
        }
        removeBubble();
        FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());

        // test if fish stay still in horizontal direction if both sides have obstacles.
        FishTank.addEntity(6, 10, bubble);
        FishTank.addEntity(5, 10, hungryFish);
        hungryFish.update();
        if (hungryFish.getX() == 5) {
            count ++;
        }
        FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());
        removeBubble();

        // test fish does not go up if obstacle is on it's top
        FishTank.addEntity(5, 9, fish);
        for (int i=0; i<200; i++) {
            hungryFish.setLocation(5,10);
            hungryFish.update();
            if (hungryFish.getY() == 9) {
                count --;
                break;
            }
            removeBubble();
        }
        count++;
        FishTank.removeEntity(5,9);


        // test fish does not go down if obstacle is on it's bottom
        FishTank.addEntity(5, 11, fish);
        for (int i=0; i<200; i++) {
            hungryFish.setLocation(5,10);
            hungryFish.update();
            if (hungryFish.getY() == 11) {
                count --;
                break;
            }
            removeBubble();
        }
        count++;
        FishTank.removeEntity(5,11);

        // Finish the test
        if (count == 5) {
            noCollision = true;
        }
        assertTrue(noCollision);
    }

    @Test
    public void testHungryFishEatSeaweed() {
        boolean eatSeaweed = false;

        FishTank.addEntity(4, 20, hungryFish);
        FishTank.addEntity(5,23, seaweed);
        hungryFish.setGoingRight(true);
        hungryFish.update();
        if (seaweed.l == (23-hungryFish.getY())) {
            eatSeaweed = true;
        }
        assertTrue(eatSeaweed);
    }

    @Test
    public void testHungryFishNotEatAllSeaweed(){
        boolean eatUp = true;

        FishTank.addEntity(5, 23, seaweed);
        for (int i = 0; i<200; i++) {
            FishTank.addEntity(4, 22, hungryFish);
            hungryFish.setGoingRight(true);
            hungryFish.update();
            FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());
            FishTank.removeEntity(3,22);
        }
        for (int i = 0; i<200; i++) {
            FishTank.addEntity(4, 23, hungryFish);
            hungryFish.setGoingRight(true);
            hungryFish.update();
            FishTank.removeEntity(hungryFish.getX(), hungryFish.getY());
            FishTank.removeEntity(2,23);
        }
        if (seaweed.getLength() == 1) {
            eatUp = false;
        }
        assertFalse(eatUp);
    }

    private void removeBubble() {
        if (FishTank.getEntity(3, 10) instanceof Bubble) {
            FishTank.removeEntity(3, 10);
        }
    }

}
