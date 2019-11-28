package fishtank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;

public class FishTest {

    /* Note: FishTest is in the package fishTank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto-grading your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;
    private HungryFish hungryFish;
    private Seaweed seaweed;
    private Bubble bubble;

    @Before
    public void setUp() {
        fish = new Fish();
        seaweed = new Seaweed(6);
        bubble = mock(Bubble.class);
        hungryFish = mock(HungryFish.class);
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
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
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
    public void testFishBlowBubblesNotCoverItem(){
        boolean bubbleCover = true;
        int count = 0;

        // Not cover fish itself, not in fish's last location.
        for (int i=0; i<200; i++) {
            FishTank.addEntity(1, 10, fish);
            fish.goingRight = true;
            fish.update();
            FishTankEntity e = FishTank.getEntity(0, 10);
            if (e instanceof Bubble) {
                FishTank.removeEntity(0, 10);
                FishTank.removeEntity(fish.getX(), fish.getY());
                count ++;
                break;
            }
        }

        //Not cover Bubble, Seaweed, HungryFish
        FishTank.addEntity(0,10, bubble);
        FishTank.addEntity(3, 10, seaweed);
        FishTank.addEntity(4, 10, hungryFish);
        for (int i=0; i<200; i++) {
            FishTank.addEntity(1, 10, fish);
            fish.goingRight=true;
            fish.update();
            FishTank.removeEntity(fish.getX(), fish.getY());
            if (FishTank.getEntity(5, 10) instanceof Bubble) {
                count ++;
                break;
            }
        }

        if (count == 2) { bubbleCover = false; }

        assertFalse(bubbleCover);
    }

    @Test
    public void testFishBlowBubblesPercentage() {

        int count = 0;
        for (int j  = 10; j < 20; j++) {
            for (int i = 0; i < 100; i++) {
                fish.setLocation(5, j);
                fish.goingRight =
                        false;
                fish.update();
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
    public void testFishSetGoingRight() {
        boolean goingRight = false;
        fish.setGoingRight(true);
        fish.setLocation(5,10);
        fish.update();
        if (fish.getX() == 6) {
            goingRight = true;
        }
        assertTrue(goingRight);
    }

    @Test
    public void testFishSetGoingLeft() {
        boolean goingLeft = false;
        fish.setGoingRight(false);
        fish.setLocation(5,10);
        fish.update();
        if (fish.getX() == 4) {
            goingLeft = true;
        }
        assertTrue(goingLeft);
    }

    @Test
    public void testFishMoveUpPercentage() {
        boolean moveUp = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            fish.setLocation(5,10);
            fish.update();
            removeBubble();
            if (fish.getY() == 9) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            moveUp = true;
        }
        assertTrue(moveUp);
    }

    @Test
    public void testFishMoveDownPercentage() {
        boolean moveDown = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            fish.setLocation(5,10);
            fish.update();
            removeBubble();
            if (fish.getY() == 9) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            moveDown = true;
        }
        assertTrue(moveDown);
    }

    @Test
    public void testFishTurnAroundPercentage() {
        boolean turnAround = false;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            fish.setLocation(5,10);
            fish.setGoingRight(true);
            fish.update();
            removeBubble();
            if (!fish.goingRight) {
                count ++;
            }
        }
        if (count >=50 && count <= 150) {
            turnAround = true;
        }
        assertTrue(turnAround);
    }

    @Test
    public void testFishStayInBound() {
        boolean stayIn = false;
        int count = 0;

        // test left boundary
        fish.setLocation(1, 5);
        fish.setGoingRight(false);
        fish.update();
        if (fish.goingRight) {
            count ++;
        }

        // test right boundary
        fish.setLocation(104, 5);
        fish.setGoingRight(true);
        fish.update();
        if (!fish.goingRight) {
            count ++;
        }

        // test top boundary
        for (int i = 0; i < 200; i++) {
            fish.setLocation(3,0);
            fish.update();
            if (fish.getY() == -1) {
                count --;
                break;
            }
        }
        count ++;

        //test bottom boundary
        for (int i = 0; i < 200; i++) {
            fish.setLocation(3, 46);
            fish.update();
            if (fish.getY() == 47) {
                count --;
                break;
            }
        }
        count ++;

        // Check all test results
        if (count == 4) {
            stayIn = true;
        }
        assertTrue(stayIn);
    }

    @Test
    public void testFishCollision() {
        boolean noCollision = false;
        int count = 0;

        // test if fish turn left if obstacle is on the right.
        FishTank.addEntity(6, 10, hungryFish);
        fish.setLocation(5, 10);
        fish.setGoingRight(true);
        fish.update();
        if (fish.getX()==4) {
            count ++;
        }
        removeBubble();
        FishTank.removeEntity(6, 10);


        // test if fish turn right if obstacle is on the left.
        FishTank.addEntity(4, 10, seaweed);
        FishTank.addEntity(5, 10, fish);
        fish.setGoingRight(false);
        fish.update();
        if (fish.getX()==6) {
            count ++;
        }
        removeBubble();
        FishTank.removeEntity(fish.getX(), fish.getY());

        // test if fish stay still in horizontal direction if both sides have obstacles.
        FishTank.addEntity(6, 10, bubble);
        FishTank.addEntity(5, 10, fish);
        fish.update();
        if (fish.getX() == 5) {
            count ++;
        }
        FishTank.removeEntity(fish.getX(), fish.getY());
        removeBubble();

        // test fish does not go up if obstacle is on it's top
        FishTank.addEntity(5, 9, hungryFish);
        for (int i=0; i<200; i++) {
            fish.setLocation(5,10);
            fish.update();
            if (fish.getY() == 9) {
                count --;
                break;
            }
            removeBubble();
        }
        count++;
        FishTank.removeEntity(5,9);


        // test fish does not go down if obstacle is on it's bottom
        FishTank.addEntity(5, 11, hungryFish);
        for (int i=0; i<200; i++) {
            fish.setLocation(5,10);
            fish.update();
            if (fish.getY() == 11) {
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
    public void testFishEatSeaweed() {
        boolean eatSeaweed = false;

        FishTank.addEntity(4, 20, fish);
        FishTank.addEntity(5,23, seaweed);
        fish.setGoingRight(true);
        fish.update();
        if (seaweed.l == (23-fish.getY())) {
            eatSeaweed = true;
        }
        assertTrue(eatSeaweed);
    }

    @Test
    public void testFishNotEatAllSeaweed(){
        boolean eatUp = true;

        FishTank.addEntity(5, 23, seaweed);
        for (int i = 0; i<200; i++) {
            FishTank.addEntity(4, 22, fish);
            fish.setGoingRight(true);
            fish.update();
            FishTank.removeEntity(fish.getX(), fish.getY());
            FishTank.removeEntity(3,22);
        }
        for (int i = 0; i<200; i++) {
            FishTank.addEntity(4, 23, fish);
            fish.setGoingRight(true);
            fish.update();
            FishTank.removeEntity(fish.getX(), fish.getY());
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
