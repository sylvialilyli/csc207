package fishtank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SeaweedTest {

    private Seaweed seaweed;

    @Before
    public void setUp() {
        seaweed = new Seaweed(6);
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
    public void testSeaweedGrowSpeed() {
        boolean rightSpeed = false;
        int count = 0;
        int insideCount = 0;

        FishTank.addEntity(24, 33, seaweed);
        // test if seaweed grow 1 segment every 200 updates.
        seaweed.setLength(3);
        for (int i=0; i<199; i++) {
            seaweed.update();
            if (seaweed.getLength() > 3) {
                insideCount ++;
            }
        }
        seaweed.update();
        if (seaweed.getLength() == 4) {
            count ++;
        }

        if (count == 1 && insideCount == 0) {
            rightSpeed = true;
        }
        assertTrue(rightSpeed);
    }

    @Test
    public void testSeaweedNotExceedOriginalLength() {
        boolean notExceedLength = true;

        FishTank.addEntity(24, 33, seaweed);
        seaweed.setLength(5);
        for (int i=0; i<400; i++) {
            seaweed.update();
        }
        if (seaweed.getLength() > 6) {
            notExceedLength  = false;
        }
        assertTrue(notExceedLength);
    }

}
