package util;

import java.util.Optional;

/**
 * LEGIT
 */
public class FrameRateTimer
{
    private static final long NANOSECONDS_PER_SECOND = 1000000000L;

    private long totalNanoseconds;

    private long intervalInNanoseconds;

    private int targetFrameRate;

    public FrameRateTimer(int targetFramesPerSecond)
    {
        this.setTargetFrameRate(targetFramesPerSecond);
    }

    private static long convertFramesPerSecondToTimeInterval(int frameRate)
    {
        return NANOSECONDS_PER_SECOND / frameRate;
    }

    public int getTargetFrameRate()
    {
        return this.targetFrameRate;
    }

    public void setTargetFrameRate(int targetFramesPerSecond)
    {
        if ( targetFramesPerSecond <= 0 )
        {
            throw new IllegalArgumentException();
        }

        this.targetFrameRate = targetFramesPerSecond;
        this.intervalInNanoseconds = convertFramesPerSecondToTimeInterval(targetFramesPerSecond);
    }

    public Optional<Long> update(long elapsedNanoseconds)
    {
        this.totalNanoseconds += elapsedNanoseconds;

        if ( this.totalNanoseconds >= this.intervalInNanoseconds)
        {
            var result = Optional.of(this.totalNanoseconds);
            this.totalNanoseconds = 0;
            return result;
        }
        else
        {
            return Optional.empty();
        }
    }
}
