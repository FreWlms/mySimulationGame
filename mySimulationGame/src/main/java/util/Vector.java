package util;

public class Vector
{

    private int x;
    private int y;


    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns this Vector's x coordinate.
     */
    public int getX() {
    	return x;
    }

    /**
     * Returns this Vector's y coordinate.
     */
    public int getY() {
    	return y;
    }
    
    /**
     * mult. both components by fac
     */
    public Vector scaleWith(int fac) {
    	int newX = this.getX()*fac;
    	int newY = this.getY()*fac;
    	return new Vector(newX, newY);
    }
    
    /**
     * sum of this vector and other
     */
    public Vector plus(Vector other) {
    	int newX = this.getX() + other.getX();
    	int newY = this.getY() + other.getY();
    	return new Vector(newX, newY);
    }


    @Override
    /**
     * LEGIT
     */
    public boolean equals(Object other)
    {
        if (other instanceof Vector v)
        {
            return this.x == v.x && this.y == v.y;
        } else
        {
            return false;
        }
    }

    @Override
    /**
     * LEGIT
     */
    public String toString()
    {
        return String.format("Vector(%d, %d)", this.x, this.y);
    }
}
