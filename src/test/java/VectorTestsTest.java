import org.junit.Test;

import javax.vecmath.Vector2d;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VectorTestsTest {

    @Test
    public void toNewAxis() {
        System.out.println(VectorTests.toNewAxis(new Vector2d(-4,-2),new Vector2d(-3,4)));
        System.out.println(VectorTests.toNewAxis(new Vector2d(-1,-4),new Vector2d(-3,4)));
        System.out.println(VectorTests.toNewAxis(new Vector2d(3,-3),new Vector2d(-3,4)));
        System.out.println(VectorTests.toNewAxis(new Vector2d(4,-1),new Vector2d(-3,4)));
        System.out.println(VectorTests.toNewAxis(new Vector2d(4,2),new Vector2d(-3,4)));
    }

    @Test
    public void timeCalcTest() {
        long start_time = System.nanoTime();
        List<double[]> res = Stream.iterate(0, n-> n+1).limit(1000000).parallel().
                map(x -> VectorTests.toNewAxis(-x,-2.,-3.,4.)).
                collect(Collectors.toList());
        long end_time = System.nanoTime();

        double difference = (end_time - start_time) / 1e9;
        System.out.println(difference);
        System.out.println(res.size());
    }

    @Test
    public void sphereCenterMoveUpdateTest(){
        List<Vector2d> channelPoints = PatientData.getChannelPoints(PatientData.Names.ARTEM);
        List<Vector2d> channelMoves = VectorTests.sphereCenterMoveUpdate(channelPoints, new Vector2d(-4,-2));
        System.out.println(channelMoves.toString());
    }
}