package util;


public class Point
{
	
    private int x;

    private int y;
	
	
    /**
     * Gives random point x,y such that 0 <= x < maxX
     * and 0 <= y < maxY.
     */
    public static Point createRandom(int maxX, int maxY)
    {
    	int x = RandomUtil.integer(maxX);
    	int y = RandomUtil.integer(maxY);
    	
    	return new Point(x, y);
    }



    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X-coordinate of the Point.
     */
    public int getX(){return this.x;}

    /**
     * Returns the X-coordinate of the Point.
     */
    public int getY(){return this.y;}

    /**
     * @pre | displacement != null
     * Returns this point, plus `displacement` 
     */
    public Point move(Vector displacement) {
    	int newX = this.x + displacement.getX();
    	int newY = this.y + displacement.getY();
    	
        return new Point(newX, newY);
    }

    /**
     * LEGIT 
     * @pre | other != null
     * @post | result == (this.getX() - other.getX()) * (this.getX() - other.getX()) + (this.getY() - other.getY()) * (this.getY() - other.getY())
     */
    public int distanceSquared(Point other)
    {
        if ( other == null )
        {
            throw new IllegalArgumentException();
        }

        var dx = other.getX() - this.getX();
        var dy = other.getY() - this.getY();

        return dx * dx + dy * dy;
    }



    @Override
    /**
     * LEGIT
     */
    public boolean equals(Object other)
    {
        if (other instanceof Point p)
        {
            return this.x == p.x && this.y == p.y;
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
        return String.format("(%d, %d)", this.x, this.y);
    }
    
    /**
     * Determines whether `pos` is a point belonging to a rectangle with sides width, height.
     * I.e. determines if pos has an x component s.t. 0 <= x < width and analogously for y.
     */
    public static boolean isWithin(Point pos, int width, int height) {
    	int x = pos.getX();
    	int y = pos.getY();
    	
    	if (x <= 0 || x >= width || y <= 0 || y >= height)
    			return false;
    	else
    		return true;}  
}
