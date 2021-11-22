import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ReoMathUtilsTest {

    @Test
    public void arrayVariants() {
        List<double[]> list = ReoMathUtils.arrayVariants(1,1, true,
                1,1,true,
                1,1,false);
        list.forEach(x-> System.out.println(Arrays.toString(x)));
        double[][] expectedArray = new double[][]{
                {-1.0, -1.0, 0.0},
                {-1.0, -1.0, 1.0},
                {-1.0, 0.0, 0.0},
                {-1.0, 0.0, 1.0},
                {-1.0, 1.0, 0.0},
                {-1.0, 1.0, 1.0},
                {0.0, -1.0, 0.0},
                {0.0, -1.0, 1.0},
                {0.0, 0.0, 0.0},
                {0.0, 0.0, 1.0},
                {0.0, 1.0, 0.0},
                {0.0, 1.0, 1.0},
                {1.0, -1.0, 0.0},
                {1.0, -1.0, 1.0},
                {1.0, 0.0, 0.0},
                {1.0, 0.0, 1.0},
                {1.0, 1.0, 0.0},
                {1.0, 1.0, 1.0}};
//        list.forEach(x-> System.out.println(Arrays.toString(x)));
        IntStream.range(0, list.size()).forEach(x-> assertArrayEquals(expectedArray[x], list.get(x), 0.001));
    }
}