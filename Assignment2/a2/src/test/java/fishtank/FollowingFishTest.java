package fishtank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish followee;
  private FollowingFish follower;
  private Fish fish;
  private Bubble bubble;
  private Seaweed seaweed;
  private HungryFish hungryFish;

    @Before
  public void setUp() {
    followee = mock(Fish.class);
    //note: this is also why we use getters and setters so much in Java,
    //we wouldn't be able to mock the field itself if it were used instead
    //of the getter.
    when(followee.getX()).thenReturn(5);
    //This syntax is introduced by a library called mockito.
    //You can use it however you want, and it will be installed when we
    //run the grader.
    //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
    when(followee.getY()).thenReturn(10);

    follower = new FollowingFish(followee);
    follower.setLocation(20, 20);
    fish = mock(Fish.class);
    bubble = mock(Bubble.class);
    seaweed = mock(Seaweed.class);
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
  public void testApproachesFromBottomRight() {
    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void testApproachesFromBottomLeft() {
    //it should take exactly 15 updates to get from
    //(0, 27) to (5, 10)
    follower.setLocation(0, 27);
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void testApproachesFromTopLeft() {
    //it should take exactly 10 updates to get from
    //(0, 0) to (5, 10)
    follower.setLocation(0, 0);
    for(int i = 0; i < 10; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void testApproachesFromTopRight() {
    //it should take exactly 15 updates to get from
    //(20, 0) to (5, 10)
    follower.setLocation(20, 0);
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void testFollowingStayInBoundary() {
    int count = 0;

    // Left Boundary
    when(followee.getX()).thenReturn(1);
    when(followee.getY()).thenReturn(10);
    follower.setLocation(0, 10);
    follower.update();
    if (follower.getX() >= 0) {
      count ++;
    }

    // Right Boundary
    when(followee.getX()).thenReturn(104);
    when(followee.getY()).thenReturn(10);
    follower.setLocation(105, 10);
    follower.update();
    if (follower.getX() <= 105) {
      count ++;
    }

    //Top Boundary
    when(followee.getX()).thenReturn(10);
    when(followee.getY()).thenReturn(1);
    follower.setLocation(10, 0);
    follower.update();
    if (follower.getY() >= 0) {
      count ++;
    }

    // Bottom Boundary
    when(followee.getX()).thenReturn(46);
    when(followee.getY()).thenReturn(10);
    follower.setLocation(47, 10);
    follower.update();
    if (follower.getY() <= 47) {
      count ++;
    }

    assertEquals(count, 4);
  }

  @Test
  public void testFollowingFishNoCollision(){
    int count  = 0;

    // Going from bottom right.
    FishTank.addEntity(19,20, fish);
    FishTank.addEntity(20, 19, hungryFish);
    FishTank.addEntity(19, 19, bubble);
    follower.update();
    System.out.println(follower.getX());
    System.out.println(follower.getY());
    if (follower.getX() == 20 && follower.getY() == 20) {
      count ++;
    }

    // Going from bottom left.
    follower.setLocation(0, 27);
    FishTank.addEntity(1,27, fish);
    FishTank.addEntity(1, 26, seaweed);
    FishTank.addEntity(0, 26, bubble);
    follower.update();
    System.out.println(follower.getX());
    System.out.println(follower.getY());
    if (follower.getX() == 0 && follower.getY() == 27) {
      count ++;
    }

    // Going from top left.
    follower.setLocation(0, 0);
    FishTank.addEntity(1,0, fish);
    FishTank.addEntity(1, 1, seaweed);
    FishTank.addEntity(0, 1, bubble);
    follower.update();
    System.out.println(follower.getX());
    System.out.println(follower.getY());
    if (follower.getX() == 0 && follower.getY() == 0) {
      count ++;
    }

    // Going from top right
    follower.setLocation(20, 0);
    FishTank.addEntity(20,1, fish);
    FishTank.addEntity(19, 1, hungryFish);
    FishTank.addEntity(19, 0, bubble);
    follower.update();
    System.out.println(follower.getX());
    System.out.println(follower.getY());
    if (follower.getX() == 20 && follower.getY() == 0) {
      count ++;
    }

    assertEquals(count, 4);
  }
}
