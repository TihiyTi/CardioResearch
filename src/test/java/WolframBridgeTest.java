import com.wolfram.jlink.MathLinkException;
import org.junit.Test;
import wolframbridge.WolframBridge;
import wolframreo.TimeStampRadialData;

import java.util.Arrays;
import java.util.stream.Stream;

public class WolframBridgeTest {
//    @Ignore
    @Test
    public void sin() throws MathLinkException {
//        wolframbridge.WolframBridge.initKernel();
//        wolframbridge.WolframBridge.testArray();
//        System.out.println(wolframbridge.WolframBridge.sin());
        double[][] res = WolframBridge.w_AtrialPoints("Alex");
        Stream.of(res).forEach(x-> System.out.println(Arrays.toString(x)));

    }
//    @Ignore
    @Test
    public void test() throws MathLinkException {
        double[][] a = WolframBridge.w_ElectrodSystemPoints("Ivan");
        Stream.of(a).forEach(x-> System.out.println(Arrays.toString(x)));
    }
//    @Ignore
    @Test
    public void test2(){
        TimeStampRadialData data = WolframBridge.w_GetRadialPoint("Alex");
        System.out.println(data.toString());
    }
//    @Ignore
    @Test
    public void test3(){
        double[][] data = WolframBridge.w_NewGetParam("Artem");
        Stream.of(data).forEach(x-> System.out.println(Arrays.toString(x)));
    }
}