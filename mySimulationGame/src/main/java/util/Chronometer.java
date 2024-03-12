package util;

/**
 * LEGIT
 */
public class Chronometer
{
    private long lastTick;

    public long elapsedNanoseconds()
    {
        var now = System.nanoTime();
        var elapsed = now - lastTick;
        lastTick = now;
        return elapsed;
    }
}