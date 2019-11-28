package fishtank;

/**
 * In Java, an "abstract class" is just a class that doesn't implement
 * some of its methods.
 *
 * In CSC148, you've seen things like this before, where every method in a class
 * simply raised a NotImplementedError. Those are also called abstract classes,
 * and fulfill a similar purpose (try replacing a usage of FishTankEntity with
 * Object in FishTank.java and see if you can understand why it doesn't work.)
 */
public abstract class FishTankEntity {

    private boolean exists = true;

    abstract void update();
    abstract void setLocation(int x, int y);

    void delete() {
        exists = false;
    }

    boolean exists() {
        return this.exists;
    }

    abstract int getX();
    abstract int getY();

    //Return true if the next step of entities make collision.
    boolean checkCollision(int x, int y){
        boolean result = false;
        if (FishTank.getEntity(x, y) != null) {
                result = true;
        }
        return result;
    }
}
