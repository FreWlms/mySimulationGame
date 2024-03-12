package util;


import java.util.List;
import java.util.Random;

/**
 * LEGIT
 */
public class RandomUtil
{
	
	private RandomUtil() {}
	
    private static final Random random = new Random();

    public static void seed(long seed)
    {
        random.setSeed(seed);
    }

    public static int integer()
    {
        return random.nextInt();
    }

    public static int integer(int max)
    {
        return random.nextInt(max);
    }

    public static int integer(int min, int max)
    {
        return random.nextInt(min, max);
    }

    public static boolean bool() { return random.nextBoolean(); }

//    public static <T> T pick(T[] array)
//    {
//        var index = random.nextInt(array.length);
//
//        return array[index];
//    }
//
    public static <T> T pick(List<T> list)
    {
        var index = random.nextInt(list.size());

        return list.get(index);
    }

//    public static <T> void shuffle(List<T> list)
//    {
//        Collections.shuffle(list, random);
//    }
}
